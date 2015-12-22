package com.williamhenry.insantani;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

//import java.nio.channels.Channel;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import io.fabric.sdk.android.Fabric;
//import com.crashlytics.android.Crashlytics;
//import com.crashlytics.android.ndk.CrashlyticsNdk;
//import io.fabric.sdk.android.Fabric;

//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.ActionBarActivity;
//public class MainActivity extends FragmentActivity
//        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
//
//    /**
//     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
//     */
//    private NavigationDrawerFragment mNavigationDrawerFragment;
//    private SharedPreferences pref;
//    private Editor editor;
//    private boolean checkToken;
//    private boolean checkRefreshToken;
//    private boolean tokenType;
//    private boolean user_id;
//
//    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
//    private static final String TAG = "MainActivity";
//
//    private BroadcastReceiver mRegistrationBroadcastReceiver;
//
//
//
//    /**
//     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
//     */
//    private CharSequence mTitle;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());
//        pref= getSharedPreferences("MyPref", Context.MODE_PRIVATE);
//        editor=pref.edit();
//        checkToken= pref.contains("access_token");
//        checkRefreshToken= pref.contains("refresh_token");
//        tokenType= pref.contains("token_type");
//        user_id=pref.contains("user_id");
//
//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
////                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
//                SharedPreferences sharedPreferences =
//                        PreferenceManager.getDefaultSharedPreferences(context);
//                boolean sentToken = sharedPreferences
//                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
//                if (sentToken) {
////                    mInformationTextView.setText(getString(R.string.gcm_send_message));
//                    Toast.makeText(getApplicationContext(),"sending message",Toast.LENGTH_SHORT).show();
//                } else {
////                    mInformationTextView.setText(getString(R.string.token_error_message));
//                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
//
//        if (checkPlayServices()) {
//            // Start IntentService to register this application with GCM.
//            Intent intent = new Intent(this, RegistrationIntentService.class);
//            startService(intent);
//        }
//
//
//
////        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
//        setContentView(R.layout.activity_main);
//
//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getFragmentManager().findFragmentById(R.id.navigation_drawer);
//        mTitle = getTitle();
//
//        // Set up the drawer.
//        mNavigationDrawerFragment.setUp(
//                R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.drawer_layout));
////        Pusher pusher = new Pusher("772cc2a8096e294b4636");
////        Channel channel= pusher.subscribe("test_channel");
////
////        channel.bind("test_event", new SubscriptionEventListener() {
////            @Override
////            public void onEvent(String channelName, String eventName, final String data) {
////                Log.d("push", data);
////                Log.d("test_push","lala");
////            }
////        });
////
////        pusher.connect();
//        try {
//            Geocoder geocoder = new Geocoder(getApplicationContext());
//            List<Address> addresses;
//            addresses = geocoder.getFromLocationName("yang tersakiti", 1);
//            if (addresses.size() > 0) {
//                double latitude = addresses.get(0).getLatitude();
//                double longitude = addresses.get(0).getLongitude();
//                Log.d("latitude", Double.toString(latitude));
//                Log.d("longitude", Double.toString(longitude));
//
//            }
//        }catch(Exception e){
//            Log.d("error_geocoder",e.toString());
//        }
//
//
//
//    }
//
//    private boolean checkPlayServices() {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
//                        .show();
//            } else {
//                Log.i(TAG, "This device is not supported.");
//                finish();
//            }
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void onNavigationDrawerItemSelected(int position) {
//        // update the main content by replacing fragments
//        Fragment fragment=null;
//
////        FragmentManager fragmentManager = getFragmentManager();
////        fragmentManager.beginTransaction()
////                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
////                .commit();
////        FragmentTransaction ft=fragmentManager.beginTransaction();
//        Log.d("position",Integer.toString(position));
//        if (!checkToken && !checkRefreshToken && !tokenType && !user_id){
//            switch(position) {
//                case 0:
//                    fragment = new HomeFragment();
////                ft.replace(R.id.container,HomeFragment, SyncStateContract.Constants)
//                    break;
//
////                case 1:
////                    fragment= new CartFragment();
////                    break;
////                case 2:
////                    fragment= new SettingsFragment();
////                    break;
//                case 1:
//
//                    fragment = new Login();
//                    break;
//
//            }
//        }else{
//            switch(position) {
//                case 0:
//                    fragment = new HomeFragment();
////                ft.replace(R.id.container,HomeFragment, SyncStateContract.Constants)
//                    break;
//
//                case 1:
//                    fragment= new CartFragment();
//                    break;
//                case 2:
//                    fragment= new WishListFragment();
//                    break;
//                case 3:
//                    fragment= new SubscriptionsFragment();
//                    break;
//                case 4:
//                    fragment= new SettingsFragment();
//                    break;
//
//
////                case 2:
////
////                    fragment = new Login();
////                    break;
//
//            }
//
//        }
//
//
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container,fragment);
//        fragmentTransaction.commit();
//
//
//        // set the toolbar title
////        getSupportActionBar().setTitle(mTitle);
//    }
//
//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//            case 4:
//                mTitle = getString(R.string.title_section4);
//                break;
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        moveTaskToBack(true);
//    }
//
//    public void restoreActionBar() {
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
//        if (!mTitle.equals("Home"))
//            actionBar.setTitle(mTitle);
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        if (!mNavigationDrawerFra bar relevant to this screen
//            // if the drawer is not showing.gment.isDrawerOpen()) {
//            // Only show items in the action Otherwise, let the drawer
//            // decide what to show in the action bar.
//            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
//            return true;
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//            startActivity(new Intent(this, SearchResultActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
////    public void onClickHandler(View v){
////
////
////
////    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//
//        @Override
//        public void onAttach(Activity activity) {
//            super.onAttach(activity);
//            ((MainActivity) activity).onSectionAttached(
//                    getArguments().getInt(ARG_SECTION_NUMBER));
//        }
//    }
//
//}

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

//    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Context c;
    private SharedPreferences pref;
    private Editor editor;
    private boolean checkToken;
    private boolean checkRefreshToken;
    private boolean tokenType;
    private boolean user_id;
    private boolean name;
    private boolean email;
    private NavigationView navigationView;
    private TextView navEmail;
    private TextView navName;
    public static MainActivity mainActivity;
    private Fragment fragment=null;
    private String nameM;
    private String emailM;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref= getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        editor=pref.edit();
        checkToken= pref.contains("access_token");
        checkRefreshToken= pref.contains("refresh_token");
        tokenType= pref.contains("token_type");
        user_id=pref.contains("user_id");
        name= pref.contains("name_user");
        email=pref.contains("email_user");
        mainActivity=this;
        c=this;
//        setRecurringAlarm(this);

//        Intent i= new Intent(this, RefreshTokenManager.class);
//        c.startService(i);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                SharedPreferences sharedPreferences =
                        PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences
                        .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
//                    mInformationTextView.setText(getString(R.string.gcm_send_message));
                    Toast.makeText(getApplicationContext(),"sending message",Toast.LENGTH_SHORT).show();
                } else {
//                    mInformationTextView.setText(getString(R.string.token_error_message));
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new HomeFragment());
        fragmentTransaction.commit();

        View viewLogin= navigationView.getHeaderView(0);
        navName= (TextView)viewLogin.findViewById(R.id.navName);
        navEmail=(TextView)viewLogin.findViewById(R.id.navEmail);
        Log.d("nameM",String.valueOf(name));
        Log.d("emailM",String.valueOf(email));
        Log.d("checkToken",String.valueOf(checkToken));
        Log.d("checkRefreshToken",String.valueOf(checkRefreshToken));
        Log.d("user_id",String.valueOf(user_id));


        if(checkToken && checkRefreshToken && user_id){
//            Log.d("email",pref.getString("email",null));
//            Log.d("name",pref.getString("name",null));
            nameM=pref.getString("name_user",null);
            emailM=pref.getString("email_user",null);
            navigationView.getMenu().findItem(R.id.shoppingCartFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.wishListFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.followingFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.settingsFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.loginFragment).setVisible(false);
            navName.setText(nameM);
            navEmail.setText(emailM);






        }else{

            navigationView.getMenu().findItem(R.id.shoppingCartFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.wishListFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.followingFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.settingsFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.loginFragment).setVisible(true);
            navName.setText("Welcome to Insantani");
            navEmail.setText("");
        }



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            moveTaskToBack(true);
        }
//        moveTaskToBack(true);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            startActivity(new Intent(this, SearchResultActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
//        Fragment fragment=null;
        Log.d("id",String.valueOf(id));

        if (!checkToken && !checkRefreshToken && !tokenType && !user_id) {
            navigationView.getMenu().findItem(R.id.shoppingCartFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.wishListFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.followingFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.settingsFragment).setVisible(false);
            navigationView.getMenu().findItem(R.id.loginFragment).setVisible(true);


            if (id == R.id.homeFragment){
                fragment = new HomeFragment();
                navigationView.getMenu().findItem(R.id.homeFragment).setChecked(true);
                navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
//                break;

            }else if(id==R.id.loginFragment) {

                fragment = new Login();
                navigationView.getMenu().findItem(R.id.homeFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.followingFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.wishListFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.shoppingCartFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
                navigationView.getMenu().findItem(R.id.loginFragment).setChecked(true);
//                break;
            }


        }else{
            navigationView.getMenu().findItem(R.id.shoppingCartFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.wishListFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.followingFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.settingsFragment).setVisible(true);
            navigationView.getMenu().findItem(R.id.loginFragment).setVisible(false);


                if (id==R.id.homeFragment) {
                    fragment = new HomeFragment();
                    navigationView.getMenu().findItem(R.id.homeFragment).setChecked(true);
                    navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
//                ft.replace(R.id.container,HomeFragment, SyncStateContract.Constants)
//                    break;

                }else if(id==R.id.shoppingCartFragment) {
                    fragment = new CartFragment();
                    navigationView.getMenu().findItem(R.id.shoppingCartFragment).setChecked(true);
                    navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
//                    break;
                }else if(id==R.id.wishListFragment) {
                    fragment = new WishListFragment();
                    navigationView.getMenu().findItem(R.id.wishListFragment).setChecked(true);
                    navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
//                    break;
                }else if(id==R.id.followingFragment){
                    fragment= new SubscriptionsFragment();
                    navigationView.getMenu().findItem(R.id.followingFragment).setChecked(true);
                    navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(false);
//                    break;
                }else if(id==R.id.settingsFragment) {

                    fragment = new SettingsFragment();
                    navigationView.getMenu().findItem(R.id.homeFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.followingFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.wishListFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.shoppingCartFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.loginFragment).setChecked(false);
                    navigationView.getMenu().findItem(R.id.settingsFragment).setChecked(true);
//                    break;
            }




        }

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();

//

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void restoreActionBar() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
//        actionBar.setDisplayShowTitleEnabled(true);
////        if (!mTitle.equals("Home"))
////            actionBar.setTitle(mTitle);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (!drawer.isDrawerOpen(GravityCompat.START)) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            getMenuInflater().inflate(R.menu.main, menu);
//            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onResume(){
        super.onResume();
//        Intent i= new Intent(this, RefreshTokenManager.class);
//        c.startService(i);
        if(checkToken && checkRefreshToken && tokenType && user_id){
            Intent intent= new Intent(this, RefreshTokenManager.class);
            c.startService(intent);
        }
        if(fragment!=null) {

            getSupportFragmentManager().beginTransaction().detach(fragment).attach(fragment).commitAllowingStateLoss();
            if (fragment instanceof SettingsFragment) {
                pref=getSharedPreferences("MyPref",Context.MODE_PRIVATE);
                navName.setText(pref.getString("name_user",null));
            }
        }


    }



    private void setRecurringAlarm(Context contexts) {
        Log.d("alarm_set","start");
        // we know mobiletuts updates at right around 1130 GMT.
        // let's grab new stuff at around 11:45 GMT, inexactly
//        Calendar updateTime = Calendar.getInstance();
//        updateTime.setTimeZone(TimeZone.getTimeZone("GMT"));
//        updateTime.set(Calendar.HOUR_OF_DAY, 11);
//        updateTime.set(Calendar.MINUTE, 45);

        Intent downloader = new Intent(contexts, AlarmReciever.class);
        PendingIntent recurringDownload = PendingIntent.getBroadcast(contexts,
                0, downloader, 0);
        AlarmManager alarms = (AlarmManager) getSystemService(
                Context.ALARM_SERVICE);
        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                40*1000*60, recurringDownload);
    }


//    @Override
//    public void onStart(){
//        super.onStart();
//        if(checkToken && checkRefreshToken && tokenType && user_id){
//            Intent intent= new Intent(this, RefreshTokenManager.class);
//            c.startService(intent);
//        }
//
//    }



}

