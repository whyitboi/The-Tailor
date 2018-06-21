package com.example.junior.thetailorv20;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageListAdapter extends ArrayAdapter<uploadImage> {
    private Activity context;
    private  int resources;
    private List<uploadImage> designlistImage;
    private List<uploadImage> samplelistImage;


    public ImageListAdapter(@NonNull Activity context, int resource, @NonNull List<uploadImage> object1) {
        super(context, resource, object1);
        this.context = context;
        this.resources = resource;
        designlistImage = object1;
        //samplelistImage = object2;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();


        View v = inflater.inflate(resources, null);
        TextView tvcomment = v.findViewById(R.id.comment);
        TextView tvcomment2 = v.findViewById(R.id.comment2);
        /*if(designlistImage.get(position).getImgName().charAt(0) == 'd') {*/
            ImageView img = v.findViewById(R.id.designView);
            tvcomment.setText(designlistImage.get(position).getImgName());
            tvcomment2.setText(designlistImage.get(position).getImgUrl());
            Glide.with(context).load(designlistImage.get(position).getImgUrl()).into(img);
        /*}
        else{
        ImageView img = v.findViewById(R.id.sampleView);
        Glide.with(context).load(designlistImage.get(position).getImgUrl()).into(img);
        }*/

        //tvcomment.setText(designlistImage.get(position).getImgName());

       // Glide.with(context).load(samplelistImage.get(position).getImgUrl()).into(img1);
        //Glide.with(context).load(designlistImage.get(position).getImgUrl()).into(img);
        return v;
    }
}
