package me.hatice.mqtt;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;


public class BrokerFragment extends Fragment {

    private boolean brokerOn;
    private boolean canClick;
    private int CHECK_INTERNET = 3333;

    public BrokerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_broker, container, false);

        final TextView text = root.findViewById(R.id.text_broker);
        Button start = root.findViewById(R.id.start_broker);

        requestPermission();

        final WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canClick) {
                    if (brokerOn) {
                        getActivity().stopService(new Intent(getActivity(), BrokerService.class));
                        text.setText(R.string.broker_off);
                        brokerOn = false;
                    } else {
                        getActivity().startService(new Intent(getActivity(), BrokerService.class));
                        text.setText(getResources().getString(R.string.broker_on, Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress())));
                        brokerOn = true;
                    }
                }
            }
        });
        return root;
    }

    private void requestPermission () {
        Log.d("broker", "request permission");

        ArrayList<String> permissions = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                    //prompt if want to enable again
                } else {
                    permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
            }

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    //prompt if want to enable again
                } else {
                    permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
                }
            }

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    //prompt if want to enable again
                } else {
                    permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            }

            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    //prompt if want to enable again
                } else {
                    permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                }
            }
            if(permissions.size() > 0) {
                ActivityCompat.requestPermissions(getActivity(), permissions.toArray(new String[permissions.size()]), 1);
            }

            if (permissions.size() > 0) {
                canClick = false;
                Log.d("broker", "request permission first");
            } else {
                canClick = true;
                Log.d("broker", "request permission true");
            }
        } else {
            canClick = false;
            Log.d("broker", "request permission first");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < grantResults.length - 1; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Log.d("broker", "Permissions needed for integrated MQTT broker withheld, please manually change" );
                } else if (permissions[i].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Log.d("broker", "Permissions needed for integrated MQTT broker withheld, please manually change" );
                } else if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Log.d("broker", "Permissions needed for integrated MQTT broker withheld, please manually change" );
                } else if (permissions[i].equals(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    Log.d("broker", "Permissions needed for integrated MQTT broker withheld, please manually change" );
                }
            }
        }


        /*
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        */

        if (requestCode == CHECK_INTERNET) {
            canClick = true;
        }

    }

}
