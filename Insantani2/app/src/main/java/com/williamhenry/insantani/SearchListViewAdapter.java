package com.williamhenry.insantani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

<<<<<<< HEAD
public class SearchListViewAdapter extends ArrayAdapter<SearchItem> {
    public SearchListViewAdapter(Context context, List<SearchItem> newsList) {
=======
public class SearchListViewAdapter extends ArrayAdapter<Product> {
    public SearchListViewAdapter(Context context, List<Product> newsList) {
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.search_listview_layout, null);
        TextView titleTextView = (TextView) view.findViewById(R.id.title_text_view);
        TextView contentTextView = (TextView) view.findViewById(R.id.content_text_view);
        ImageView picture = (ImageView) view.findViewById(R.id.icon);

<<<<<<< HEAD
        titleTextView.setText(getItem(position).getTitle());
        contentTextView.setText(getItem(position).getContent());
        picture.setImageResource(getItem(position).getPicture());
=======
        titleTextView.setText(getItem(position).getName());
        contentTextView.setText(getItem(position).getFarmerName());
        picture.setImageBitmap(getItem(position).getThumbnail());
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef

        return view;
    }
}