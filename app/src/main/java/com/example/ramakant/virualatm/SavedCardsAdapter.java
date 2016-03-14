package com.example.ramakant.virualatm;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/13/2016.
 */
public class SavedCardsAdapter extends SavedCardRecyclerViewCursorAdapter<SavedCardsAdapter.MyViewHolder> {

    Integer rowIdCurrent;
    boolean isTick;

    public SavedCardsAdapter(SavedCardsFragment context, Cursor cursor, boolean isTick) {
        super(context, cursor);
        this.isTick = isTick;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, Cursor cursor) {

        Log.e("in BindViewHolder", cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.CardNumber)));

       CardItemData itemData = CardItemData.getListItem(cursor);
        viewHolder.cardNumber.setText(itemData.getCardNumber());
        viewHolder.toMonth.setText(itemData.getToMonth());
        viewHolder.toYear.setText(itemData.getToYear());
        viewHolder.fromMonth.setText(itemData.getFromMonth());
        viewHolder.fromYear.setText(itemData.getFromYear());
        viewHolder.cvv.setText(itemData.getCvv());
        viewHolder.name.setText(itemData.getName());
        viewHolder.updateCurrentId(itemData.getId());
        if(isTick)
        {

            viewHolder.edit.setText("Select");
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_cards_item, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView cardNumber, toMonth, toYear, fromMonth, fromYear, cvv, name, edit;
        Integer currentRowId;

       void updateCurrentId(Integer currentRowId)
        {
            this.currentRowId = currentRowId;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            cardNumber = (TextView) itemView.findViewById(R.id.edtCardNumber);
            fromMonth = (TextView) itemView.findViewById(R.id.fromMonth);
            fromYear = (TextView) itemView.findViewById(R.id.fromYear);
            toMonth = (TextView) itemView.findViewById(R.id.toMonth);
            toYear = (TextView) itemView.findViewById(R.id.toYear);
            cvv = (TextView) itemView.findViewById(R.id.cvv);
            name = (TextView) itemView.findViewById(R.id.name);
            edit = (TextView) itemView.findViewById(R.id.edit);

            if(isTick)
            {
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       mContext.startATMFragment(currentRowId);

                    }

                });
            }
            else
            {
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startEditFragment(currentRowId);
                    }
                });
            }


        }
    }


}
