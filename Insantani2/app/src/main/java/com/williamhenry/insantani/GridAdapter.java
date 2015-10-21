package com.williamhenry.insantani;

/**
 * Created by william on 10/17/2015.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.os.Bundle;
//import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.util.Log;
import android.content.Context;

public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<EndangeredItem> mItems;
    private Context context;
    public GridAdapter(Context context) {
        super();
        this.context=context;
        mItems = new ArrayList<EndangeredItem>();
        EndangeredItem species = new EndangeredItem();
        species.setName("sample 0");
        species.setThumbnail(R.drawable.sample_0);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 1");
        species.setThumbnail(R.drawable.sample_1);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 2");
        species.setThumbnail(R.drawable.sample_2);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 3");
        species.setThumbnail(R.drawable.sample_3);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 4");
        species.setThumbnail(R.drawable.sample_4);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 5");
        species.setThumbnail(R.drawable.sample_5);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 6");
        species.setThumbnail(R.drawable.sample_6);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName("sample 7");
        species.setThumbnail(R.drawable.sample_7);
        mItems.add(species);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final EndangeredItem nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
//                Intent intent= new Intent(getAc,ProductActivity.class);
//                startActivtiy(intent);

                Intent intent= new Intent(context,ProductActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",nature.getName());
                bundle.putInt("thumbnail",nature.getThumbnail());
                intent.putExtra("nature",bundle);
//                intent.putExtra("nature_thumbnail",nature.getThumbnail());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvspecies;
//        public final Context context;

        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.tv_species);

        }
//        @Override
//        public void onClick(View v) {
//
//            Intent intent=new Intent(getActivity(),ProductActivity.class);
//            startActivity(intent);
//
////            context.startActivity(new Intent(context,ProductActivity.class));
//        }
    }
}