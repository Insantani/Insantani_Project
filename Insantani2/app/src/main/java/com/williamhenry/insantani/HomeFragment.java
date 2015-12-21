package com.williamhenry.insantani;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class HomeFragment extends Fragment {

    private FeedTabFragment feedTabFragment;
    private ShopTabFragment shopTabFragment;
    private FarmerActivityTabFragment farmerActivityTabfragment;

    private TabLayout tabLayout;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref= this.getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor= pref.edit();
        checkToken=pref.contains("access_token");
        checkRefreshToken=pref.contains("refresh_token");
        tokenType=pref.contains("token_type");
        user_id=pref.contains("user_id");
//        getActivity().getActionBar().hide();
        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        getActivity().getActionBar().show();
        getActivity().getActionBar().setElevation(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment

        feedTabFragment = new FeedTabFragment();
        shopTabFragment = new ShopTabFragment();
        farmerActivityTabfragment = new FarmerActivityTabFragment();

        TabFragmentPagerAdapter adapter = new TabFragmentPagerAdapter(getChildFragmentManager());
        if(checkToken==false && checkRefreshToken==false && tokenType==false && user_id==false){
            adapter.addFragment(feedTabFragment, "Feed");
            adapter.addFragment(shopTabFragment, "Shop");
        }else{
            adapter.addFragment(feedTabFragment, "Feed");
            adapter.addFragment(shopTabFragment, "Shop");
            adapter.addFragment(farmerActivityTabfragment, "Activity");
        }
//        adapter.addFragment(feedTabFragment, "Feed");
//            adapter.addFragment(shopTabFragment, "Shop");
//            adapter.addFragment(farmerActivityTabfragment, "Activity");



        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }



}
