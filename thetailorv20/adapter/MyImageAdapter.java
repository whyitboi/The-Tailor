package com.example.junior.thetailorv20.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junior.thetailorv20.R;
import com.example.junior.thetailorv20.uploadImage;

import java.util.ArrayList;

public class MyImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<uploadImage> designs;
    private ArrayList<uploadImage> samples;
    private ArrayList<String> coments;


    public MyImageAdapter(Context context, ArrayList<uploadImage> designs, ArrayList<uploadImage> samples, ArrayList<String> comments) {
        this.context = context;
        this.designs = designs;
        this.samples = samples;
        this.coments = comments;
    }

    @Override
    public int getCount() {
        return designs.size();
    }

    @Override
    public Object getItem(int position) {
        return designs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){

            convertView = View.inflate(context, R.layout.list_item_design_sample, null);
        }
        ImageView designView = convertView.findViewById(R.id.designView);
        //ImageView sampleView = convertView.findViewById(R.id.sampleView);
        TextView comment = convertView.findViewById(R.id.comment);

        designView.setImageURI(Uri.parse(designs.get(position).getImgUrl()));
        //sampleView.setImageURI(Uri.parse(samples.get(position).getImgUrl())); //Integer.parseInt(samples.get(position))
        comment.setText(coments.get(position));

        return convertView;
    }
}
