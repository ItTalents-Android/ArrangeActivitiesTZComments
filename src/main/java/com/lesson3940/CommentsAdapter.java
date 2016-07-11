package com.lesson3940;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Petkata on 28.2.2016 г..
 */
public class CommentsAdapter extends ArrayAdapter<String>{

    private class MyViewHolder{
        private ImageView img;
        private TextView commentText;

        MyViewHolder(View v){
            img = (ImageView) v.findViewById(R.id.img_post_image);
            commentText = (TextView) v.findViewById(R.id.txt_post_text);
        }
    }

    private Context context;
    private ArrayList<String> strings;
    private ArrayList<ImageView> imgs;

    public CommentsAdapter(Context c, ArrayList<String> stringArray, ArrayList<ImageView> imgs) {
        super(c,R.layout.comment_layout,R.id.txt_post_text,stringArray);
        this.context = c;
        this.strings = stringArray;
        this.imgs = imgs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MyViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.comment_layout,parent,false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
        }
        else {
            holder = (MyViewHolder) row.getTag();
        }

        holder.commentText.setText(strings.get(position));
        holder.img.setImageDrawable(imgs.get(position).getDrawable());
        return row;
    }
}
