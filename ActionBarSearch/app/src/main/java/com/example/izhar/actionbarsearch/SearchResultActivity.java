package com.example.izhar.actionbarsearch;

import android.app.ActionBar;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    private ListView resultListView;
    private String[] stringArray ;
    private ArrayAdapter resultItemArrayAdapter;

    private IngredientsTabFragment ingredientsTabFragment;
    private FarmerTabFragment farmerTabFragment;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        getSupportActionBar().setTitle("");

        // Enabling Back navigation on Action Bar icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        ingredientsTabFragment = new IngredientsTabFragment();
        farmerTabFragment = new FarmerTabFragment();

        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ingredientsTabFragment, "Ingredients");
        adapter.addFragment(farmerTabFragment, "Farmer");

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_result, menu);

        // Get the SearchView and set the searchable configuration

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_search));
        searchView.setQueryHint("Search");

        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(true);
        searchView.requestFocusFromTouch();

        final SearchView.OnQueryTextListener queryTextlistener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Here u can get the value "query" which is entered in the search box.
                searchView.setQuery(query, false);

                if (tabLayout.getSelectedTabPosition() == 0) {
                    ingredientsTabFragment.search(query);
                }
                else{
                    farmerTabFragment.search(query);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("SearchResultActivity", "New text: " + newText);
                // This is your adapter that will be filtered.
                if (tabLayout.getSelectedTabPosition() == 0) {
                    ingredientsTabFragment.search(newText);
                }
                else{
                    farmerTabFragment.search(newText);
                }
                return false;
            }
        };

        searchView.setOnQueryTextListener(queryTextlistener);
        searchView.callOnClick();

        // To skip the first search
        searchView.setQuery("1", true);
        searchView.setQuery("", true);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                queryTextlistener.onQueryTextSubmit(searchView.getQuery().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                queryTextlistener.onQueryTextSubmit(searchView.getQuery().toString());
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}