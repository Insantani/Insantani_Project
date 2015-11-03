package com.example.izhar.actionbarsearch;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

public class SearchResultActivity extends Activity {

    private ListView resultListView;
    private String[] stringArray ;
    private ArrayAdapter resultItemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        // get the action bar
        ActionBar actionBar = getActionBar();

        actionBar.setTitle("");

        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);


        stringArray = new String[20];
        for(int i=0; i < stringArray.length; i++){
            stringArray[i] = "String " + i;

        }
        resultItemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringArray);
        resultListView = (ListView) findViewById(R.id.itemList);
        resultListView.setAdapter(resultItemArrayAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_result, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default

        SearchView.OnQueryTextListener queryTextlistener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Here u can get the value "query" which is entered in the search box.
                searchView.setQuery(query, false);
                resultItemArrayAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered.
                resultItemArrayAdapter.getFilter().filter(newText);
                return false;
            }
        };
        searchView.setOnQueryTextListener(queryTextlistener);

        return super.onCreateOptionsMenu(menu);
    }


}