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


public class SubscriptionsFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    public SubscriptionsFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_subscriptions, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<Subscription> subscriptions = new ArrayList<Subscription>();
        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, false));
        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));
        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));
        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, false));
        subscriptions.add(new Subscription("Izhar Almizan", R.mipmap.ic_name, true));


        mAdapter = new SubscriptionAdapter(subscriptions);

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