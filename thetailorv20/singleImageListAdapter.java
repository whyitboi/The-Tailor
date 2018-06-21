package com.example.junior.thetailorv20;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class singleImageListAdapter extends ArrayAdapter<uploadImage> {

    private Activity context;
    private  int resources;
   // private List<uploadImage> designlistImage;
    private List<uploadImage> samplelistImage;

    public singleImageListAdapter(@NonNull Activity context, int resource, @NonNull List<uploadImage> object) {
        super(context, resource, object);
        this.context = context;
        this.resources = resource;
        samplelistImage = object;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();


        View v = inflater.inflate(resources, null);
       // TextView tvcomment = v.findViewById(R.id.comment);
        ImageView img = v.findViewById(R.id.designView);

        Glide.with(context).load(samplelistImage.get(position).getImgUrl()).into(img);
        return v;
    }

}
