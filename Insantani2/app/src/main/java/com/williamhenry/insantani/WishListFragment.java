package com.williamhenry.insantani;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class WishListFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public WishListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getActionBar().setElevation(11);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wish_list, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Wish> wishes = new ArrayList<Wish>();
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));
        wishes.add(new Wish("Baby Tomatoes", "Izhar Almizan", (float) 5000200, R.mipmap.tomat));


        mAdapter = new WishListAdapter(wishes);

        mRecyclerView.setAdapter(mAdapter);



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

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}