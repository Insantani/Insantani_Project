package com.williamhenry.insantani;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final TabHost tabHost = (TabHost) rootView.findViewById(R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("feed");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Feed", null);
        tabHost.addTab(spec1);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("shop");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Shop", null);
        tabHost.addTab(spec2);
        mRecyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // The number of Columns
        mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new GridAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Article> articles = new ArrayList<Article>();

        for (int i = 0; i < 20 ; i++){
            articles.add(new Article(
                    "Author" + i, "Title" + i, "This is about desc "+ i, R.drawable.sample_4 ));
        }
        mAdapter = new MyAdapter(articles,getContext());
        mRecyclerView.setAdapter(mAdapter);




//        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(), ProductActivity.class);
//            startActivity(intent);
//        }



        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
//    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true
                    ;
        }

        return super.onOptionsItemSelected(item);
    }



}
