package com.example.izhar.actionbarsearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class IngredientsTabFragment extends Fragment {

    private ListView resultListView;
    private String[] stringArray ;
    private ArrayAdapter resultItemArrayAdapter;

    public IngredientsTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients_tab, container, false);

        stringArray = new String[20];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;

        }
        resultItemArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, stringArray);
        resultListView = (ListView) view.findViewById(R.id.itemList);
        resultListView.setAdapter(resultItemArrayAdapter);

        return view;
    }

    public void search(String param) {
        resultItemArrayAdapter.getFilter().filter(param);
    }

}
