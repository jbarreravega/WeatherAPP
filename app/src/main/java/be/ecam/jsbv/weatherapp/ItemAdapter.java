package be.ecam.jsbv.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


/**
 * Created by 14309 on 14-02-17.
 */

public class ItemAdapter {
    private String[] mData = null;

    private ItemAdapterOnClickHandler clickHandler;

    public ItemAdapter(ItemAdapterOnClickHandler clickHandler) {
        this.clickHandler = clickHandler;
    }

    public interface ItemAdapterOnClickHandler {
        void onClick(int index);
    }

    public class ItemAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mTextView;
        public ItemAdapterViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.student_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            clickHandler.onClick(adapterPosition);
        }
    }

    @Override
    public ItemAdapterViewHolder onCreateViewHolder
            (ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem,
                viewGroup, shouldAttachToParentImmediately);
        return new ItemAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (ItemAdapterViewHolder itemAdapterViewHolder, int position) {

        String dataForThisItem = mData[position];
        itemAdapterViewHolder.mTextView.setText(dataForThisItem);
    }

    @Override
    public int getItemCount() {
        if (null == mData) return 0;
        return mData.length;
    }

    public void setData(String[] data) {
        mData = data;
        notifyDataSetChanged();
    }
}
