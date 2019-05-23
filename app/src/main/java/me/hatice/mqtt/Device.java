package me.hatice.mqtt;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.ISwipeable;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.view.IconicsImageView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

public class Device extends RealmObject implements IItem<Device, Device.ViewHolder>, ISwipeable<Device, IItem> {

    public Device() {

    }
    /**
     * Method returns User MAC and corresponding AES Key
     *
     * @return  Concatenated User MAC and AES Key as String
     */
    @Override
    public String toString() {
        return "FriendListDbHandler{";
    }

    // defines if this item is enabled
    @Ignore
    protected boolean mEnabled = true;
    /**
     * set if this item is enabled
     *
     * @param enabled true if this item is enabled
     * @return this
     */
    public Device withEnabled(boolean enabled) {
        this.mEnabled = enabled;
        return this;
    }

    /**
     * @return if this item is enabled
     */
    @Override
    public boolean isEnabled() {
        return mEnabled;
    }

    // defines if the item is selected
    @Ignore
    protected boolean mSelected = false;

    /**
     * set if this item is selected
     *
     * @param selected true if this item is selected
     * @return this
     */
    @Override
    public Device withSetSelected(boolean selected) {
        this.mSelected = selected;
        return this;
    }

    /**
     * @return if this item is selected
     */
    @Override
    public boolean isSelected() {
        return mSelected;
    }

    // defines if this item is selectable
    @Ignore
    protected boolean mSelectable = true;

    /**
     * set if this item is selectable
     *
     * @param selectable true if this item is selectable
     * @return this
     */
    @Override
    public Device withSelectable(boolean selectable) {
        this.mSelectable = selectable;
        return this;
    }

    /**
     * @return if this item is selectable
     */
    @Override
    public boolean isSelectable() {
        return mSelectable;
    }

    @Override
    public int getType() {
        //return R.id.fastadapter_realm_sample_user_item_id;
        return -1;
    }

    /**
     * returns the layout for the given item
     *
     * @return
     */
    @Override
    public int getLayoutRes() {
        return R.layout.item_device;
    }

    @Ignore
    public int swipedDirection;
    @Ignore
    private Runnable swipedAction;
    @Ignore
    public boolean swipeable = true;

    @Override
    public boolean isSwipeable() {
        return swipeable;
    }

    @Override
    public Device withIsSwipeable(boolean swipeable) {
        this.swipeable = swipeable;
        return this;
    }

    public void setSwipedDirection(int swipedDirection) {
        this.swipedDirection = swipedDirection;
    }

    public void setSwipedAction(Runnable action) {
        this.swipedAction = action;
    }
    /**
     * generates a view by the defined LayoutRes
     *
     * @param ctx
     * @return
     */
    @Override
    public View generateView(Context ctx) {
        Device.ViewHolder viewHolder = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), null, false));

        //as we already know the type of our ViewHolder cast it to our type
        bindView(viewHolder, Collections.EMPTY_LIST);

        //return the bound view
        return viewHolder.itemView;
    }

    /**
     * generates a view by the defined LayoutRes and pass the LayoutParams from the parent
     *
     * @param ctx
     * @param parent
     * @return
     */
    @Override
    public View generateView(Context ctx, ViewGroup parent) {
        Device.ViewHolder viewHolder = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), parent, false));

        //as we already know the type of our ViewHolder cast it to our type
        bindView(viewHolder, Collections.EMPTY_LIST);
        //return the bound and generatedView
        return viewHolder.itemView;
    }

    /**
     * Generates a ViewHolder from this Item with the given parent
     *
     * @param parent
     * @return
     */
    @Override
    public Device.ViewHolder getViewHolder(ViewGroup parent) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false));
    }

    /**
     * This method returns the ViewHolder for our item, using the provided View.
     * By default it will try to get the ViewHolder from the ViewHolderFactory. If this one is not implemented it will go over the generic way, wasting ~5ms
     *
     * @param v
     * @return the ViewHolder for this Item
     */
    private Device.ViewHolder getViewHolder(View v) {
        return new Device.ViewHolder(v);
    }

    /**
     * Binds the data of this item to the given holder
     *
     * @param holder
     */
    @Override
    public void bindView(Device.ViewHolder holder, List<Object> payloads) {
        //set the selected state of this item. force this otherwise it may is missed when implementing an item
        holder.itemView.setSelected(isSelected());
        //set the tag of this item to this object (can be used when retrieving the view)
        holder.itemView.setTag(this);

        //set the name
        ///holder.mName.setText(USER_MAC);
        //holder.mName.setText( mName );
        Context ctx = holder.itemView.getContext();

        IconicsDrawable icon = null;

        switch ( (int)(mIdentifier % 4) ) {
            case 0:
                //icon = new IconicsDrawable(ctx, CommunityMaterial.Icon.cmd_presentation).sizeDp(64).color(Color.parseColor("#ffff4444"));
                break;

            case 1:
                //icon = new IconicsDrawable(ctx, CommunityMaterial.Icon.cmd_projector_screen).sizeDp(64).color(Color.parseColor("#ff99cc00"));
                break;

            case 2:
                //icon = new IconicsDrawable(ctx, CommunityMaterial.Icon.cmd_presentation).sizeDp(64).color(Color.parseColor("#ff33b5e5"));
                break;

            case 3:
            default:
                //icon = new IconicsDrawable(ctx, CommunityMaterial.Icon.cmd_projector_screen).sizeDp(64).color(Color.parseColor("#ffffbb33"));
                break;
        }

       // holder.mImageView.setIcon(icon);

       // holder.swipeResultContent.setVisibility(swipedDirection != 0 ? View.VISIBLE : View.GONE);
       // holder.itemContent.setVisibility(swipedDirection != 0 ? View.GONE : View.VISIBLE);

        //IconicsDrawable icon_delete = new  IconicsDrawable(ctx, MaterialDesignIconic.Icon.gmi_delete).sizeDp(28).color(Color.WHITE);
        //holder.mSwipeImageView.setIcon(icon_delete);


        holder.swipedActionRunnable = this.swipedAction;

    }

    @Override
    public void unbindView(Device.ViewHolder holder) {
        //holder.mName.setText(null);
       /// holder.mImageView.setImageDrawable(null);
        holder.mSwipeImageView.setImageDrawable(null);
        holder.swipedActionRunnable = null;
    }

    @Override
    public void attachToWindow(Device.ViewHolder holder) {}

    @Override
    public void detachFromWindow(Device.ViewHolder holder) {}

    @Override
    public boolean failedToRecycle(Device.ViewHolder holder) {
        return false;
    }

    @PrimaryKey
    protected long mIdentifier = -1;

    public Device withIdentifier(long identifier) {
        this.mIdentifier = identifier;
        return this;
    }

    /**
     * @return the identifier of this item
     */
    @Override
    public long getIdentifier() {
        return mIdentifier;
    }

    /**
     * If this item equals to the given identifier
     *
     * @param id identifier
     * @return true if identifier equals id, false otherwise
     */
    @Override
    public boolean equals(int id) {
        return id == mIdentifier;
    }

    /**
     * If this item equals to the given object
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractItem<?, ?> that = (AbstractItem<?, ?>) o;

        return mIdentifier == that.getIdentifier();
    }

    // the tag for this item
    @Ignore
    protected Object mTag;

    /**
     * set the tag of this item
     *
     * @param object
     * @return this
     */
    public Device withTag(Object object) {
        this.mTag = object;
        return this;
    }

    /**
     * @return the tag of this item
     */
    @Override
    public Object getTag() {
        return mTag;
    }
    /**
     * the hashCode implementation
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Long.valueOf(mIdentifier).hashCode();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        protected View view;

        /*
        @BindView(R.id.text_view)
        protected TextView mName;
        @BindView(R.id.image_view)
        protected IconicsImageView mImageView;
        @BindView(R.id.item_text_container)
        View itemContent;
        @BindView(R.id.swipe_result_content)
        View swipeResultContent;
        @BindView(R.id.swiped_action)
        */
        TextView swipedActionView;
///        @BindView(R.id.swipe_image_img)
        protected IconicsImageView mSwipeImageView;

        public Runnable swipedActionRunnable;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;

            ///ButterKnife.bind(this, view);

            swipedActionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (swipedActionRunnable != null) {
                        swipedActionRunnable.run();
                    }
                }
            });

        }
    }

}
