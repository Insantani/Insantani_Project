package com.williamhenry.insantani;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TabHost;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ImageButton imageButton;

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

        imageButton = (ImageButton) rootView.findViewById(R.id.action_search);

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

        mAdapter = new ProductAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        ArrayList<Article> articles = new ArrayList<Article>();
        articles.add(new Article(
                "by Agung Wirayogi", "Chili for your health",
                "Chilies are excellent source of Vitamin, A, B, C and E with minerals like molybdenum, manganese, folate, potassium, thiamin, and copper. Chili contains seven times more vitamin C than orange.\n" +
                        "\n" +
                        "Ever since its introduction to India in 1498, chilies have been included in Ayurvedic medicines and used as " +
                        "tonic to ward off many diseases. Chilies are good for slimming down as it burns the calorie easily. " +
                        "Chilies stimulate the appetite, help to clear the lungs, and stimulate digestive system", R.mipmap.cabe

        ));
        articles.add(new Article(
                "by William Henry", "Cabbage not only grean",
                "According to the USDA National Nutrient Database, one half cup of shredded cabbage" +
                        " (75 grams) contains 17 calories, 4 grams of carbohydrate (including 1 gram " +
                        "of fiber and 2 grams of sugar) and 1 gram of protein. ", R.mipmap.col

        ));
        articles.add(new Article(
                "by Izhar Almizan", "Advantage of carrot",
                "Western culture’s  understanding of carrots being “good for the eyes” is one of the few we got right. " +
                        "Carrots are rich in beta-carotene, which is converted into vitamin A in the liver. Vitamin A is " +
                        "transformed in the retina, to rhodopsin, a purple pigment necessary for night vision.\n" +
                        "\n" +
                        "Read more: http://www.care2.com/greenliving/10-benefits-of-carrots.html#ixzz3pImoFOIR\n", R.mipmap.carrot

        ));
        articles.add(new Article(
                "by Diovi Adzhalia", "Why my friend buy potato",
                "Followers of low-carb diets give regular potatoes a bad rap. They are not exactly good for you when " +
                        "dropped into a deep fryer, but potatoes are packed with powerful nutrients and antioxidants—compounds " +
                        "that fight free radicals. The ORAC value (a measure of the total antioxidants in 100 grams) for a" +
                        " medium baked potato with skin is a healthy 1,680, while that of a baked sweet potato with skin " +
                        "is 766. Compare those with the values for carrots, either cooked (317) or raw (666)"
                , R.mipmap.kentang

        ));
        articles.add(new Article(
                "by Abicantya Sophie", "Tomato is good for you",
                "Whether you refer to a tomato as a fruit or a vegetable, there is no doubt that a tomato " +
                        "is a nutrient-dense, super-food that most people should be eating more of.\n" +
                        "\n" +
                        "The tomato has been referred to as a \"functional food,\" a food that goes beyond" +
                        " providing just basic nutrition, additionally preventing chronic disease and" +
                        " delivering other health benefits, due to beneficial phytochemicals such as lycopene."
                , R.mipmap.tomat

        ));
        articles.add(new Article(
                "by Yohana Kanisia", "Cucumber is cool",
                "Cucumbers belong to the same plant family as squash, pumpkin, and " +
                        "watermelon (the Cucurbitaceae family). Like watermelon, cucumbers " +
                        "are made up of mostly (95 percent) water, which means eating them on" +
                        " a hot summer day can help you stay hydrated."
                , R.mipmap.timun

        ));

//        for (int i = 0; i < 20 ; i++){
//            articles.add(new Article(
//                    "Author" + i, "Title" + i, "This is about desc "+ i, R.drawable.sample_4 ));
//        }
        mAdapter = new ArticleAdapter(articles,getContext());
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
        getActivity().getMenuInflater().inflate(R.menu.activity_main_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            startActivity(new Intent(getContext(), SearchResultActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
