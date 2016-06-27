package com.example.jhona.quizmotos;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jhona on 14/06/2016.
 */


public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            {
        TextView p1,p2,p3,p4;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            p1 = (TextView) itemView.findViewById(R.id.p1);
            p2 = (TextView) itemView.findViewById(R.id.p2);
            p3 = (TextView) itemView.findViewById(R.id.p3);
            p4 = (TextView) itemView.findViewById(R.id.p4);

            Log.i(LOG_TAG, "Adding Listener");
          //  itemView.setOnClickListener(this);
        }

      /*  @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }*/
    }

   /* public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
*/
    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.p1.setText(mDataset.get(position).getmText1());
        holder.p2.setText(mDataset.get(position).getmText1());
        holder.p3.setText(mDataset.get(position).getmText1());
        holder.p4.setText(mDataset.get(position).getmText1());

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}