package com.tyler.nbtest.cards;

import java.util.ArrayList;

import com.tyler.nbtest.R;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CardListAdapter extends ArrayAdapter<Cards>{

	private Activity context;
	

	public CardListAdapter(Context context, int textViewResourceId, ArrayList<Cards> cards) {
		super(context, textViewResourceId,cards);
		
		this.context=(Activity)context;
		// TODO Auto-generated constructor stub
	}

	
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            TextView tv=(TextView)context.findViewById(R.id.list_item_text);
            view = inflater.inflate(R.layout.list_item, null);
            
        }

        Cards item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            TextView itemView = (TextView) view.findViewById(R.id.list_item_text);
            if (itemView != null) {
                // do whatever you want with your string and long
                itemView.setText(item.getCardFront());
                itemView.setBackground(context.getResources().getDrawable(R.drawable.notecard_background));
                itemView.setAlpha((float) .5);
            }
         }

        return view;
    }
}