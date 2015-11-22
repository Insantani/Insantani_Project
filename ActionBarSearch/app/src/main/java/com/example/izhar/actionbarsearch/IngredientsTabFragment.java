package com.example.izhar.actionbarsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class IngredientsTabFragment extends Fragment {

    private ListView resultListView;
    private SearchListViewAdapter searchListViewAdapter;

    public IngredientsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_tab, container, false);

        List<SearchItem> newsList = new ArrayList<>();
        searchListViewAdapter = new SearchListViewAdapter(getActivity(), newsList);
        resultListView = (ListView) view.findViewById(R.id.itemList);
        resultListView.setAdapter(searchListViewAdapter);

        // add items to the list
        searchListViewAdapter.add(new SearchItem("News 1", "This is the content of news 1", 1));
        searchListViewAdapter.add(new SearchItem("News 2", "This is the content of news 2", 2));
        searchListViewAdapter.add(new SearchItem("News 3", "This is the content of news 3", 3));

        // show toast on item click
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),
                        getString(R.string.clicked) + " " + searchListViewAdapter.getItem(position).getTitle().toLowerCase(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void search(String param) {
        searchListViewAdapter.getFilter().filter(param);
    }

}
