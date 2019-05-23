package me.hatice.mqtt;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.itemanimators.AlphaInAnimator;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mContainer;
    //save our Realm instance to close it later
    private Realm mRealm;

    private FastItemAdapter<Device> mFastItemAdapter;

    public ClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientFragment newInstance(String param1, String param2) {
        ClientFragment fragment = new ClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_client, container, false);

        mContainer = root.findViewById(R.id.device_container);
        //create our FastAdapter which will manage everything
        mFastItemAdapter = new FastItemAdapter<>();

        //configure our fastAdapter
        mFastItemAdapter.withOnClickListener(new FastAdapter.OnClickListener<Device>() {
            @Override
            public boolean onClick(View v, IAdapter<Device> adapter, Device item, int position) {
                ///Navigator.DevStatusWithAutoCompat.start( (Activity)v.getContext(), item.getUSER_MAC() );
                return false;
            }
        });

        //get our recyclerView and do basic setup
        mContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mContainer.setItemAnimator(new AlphaInAnimator());
        mContainer.setAdapter(mFastItemAdapter);
        //Get a realm instance for this activity
        mRealm = Realm.getDefaultInstance();

        //set the back arrow in the toolbar
        ///getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ///getSupportActionBar().setHomeButtonEnabled(false);

        //restore selections (this has to be done after the items were added
        mFastItemAdapter.withSavedInstanceState(savedInstanceState);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        //Add a realm on change listener (don´t forget to close this realm instance before adding this listener again)
        mRealm.where(Device.class).findAllAsync().addChangeListener(new RealmChangeListener<RealmResults<Device>>() {
            @Override
            public void onChange(RealmResults<Device> userItems) {
                mFastItemAdapter.setNewList(userItems);
                //iconNoData.setVisibility(userItems.size() > 0 ? View.GONE : View.VISIBLE);
            }
        });
    }

    //Prevent the realm instance from leaking
    @Override
    public void onDestroy() {
        super.onDestroy();
        closeRealm();
    }


    private void closeRealm() {
        mRealm.close();
    }


}
