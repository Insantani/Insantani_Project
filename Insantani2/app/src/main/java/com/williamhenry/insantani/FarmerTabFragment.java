package com.williamhenry.insantani;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class FarmerTabFragment extends android.support.v4.app.Fragment {

    private ListView resultListView;
    private SearchListViewAdapter searchListViewAdapter;

    public FarmerTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_farmer_tab, container, false);

<<<<<<< HEAD
        List<SearchItem> newsList = new ArrayList<>();
=======
        List<Product> newsList = new ArrayList<>();
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
        searchListViewAdapter = new SearchListViewAdapter(getActivity(), newsList);
        resultListView = (ListView) view.findViewById(R.id.itemList1);
        resultListView.setAdapter(searchListViewAdapter);

        // add items to the list
<<<<<<< HEAD
        searchListViewAdapter.add(new SearchItem("News 1", "", 1));
        searchListViewAdapter.add(new SearchItem("News 2", "", 2));
        searchListViewAdapter.add(new SearchItem("News 3", "", 3));
=======
//        searchListViewAdapter.add(new SearchItem("News 1", "", 1));
//        searchListViewAdapter.add(new SearchItem("News 2", "", 2));
//        searchListViewAdapter.add(new SearchItem("News 3", "", 3));
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef

        // show toast on item click
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
<<<<<<< HEAD
                        getString(R.string.clicked) + " " + searchListViewAdapter.getItem(position).getTitle().toLowerCase(),
=======
                        getString(R.string.clicked) + " " + searchListViewAdapter.getItem(position).getName().toLowerCase(),
>>>>>>> 8a2c14d732c9dacf480fd864f1bf563afa6876ef
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void search(String param) {
        searchListViewAdapter.getFilter().filter(param);
    }

}
