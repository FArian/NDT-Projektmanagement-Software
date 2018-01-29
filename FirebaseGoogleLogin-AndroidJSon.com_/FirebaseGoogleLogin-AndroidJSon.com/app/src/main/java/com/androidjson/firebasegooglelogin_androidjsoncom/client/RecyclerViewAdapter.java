package com.androidjson.firebasegooglelogin_androidjsoncom.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by F.Arian on 07.01.18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Item> items;
    private LayoutInflater inflater;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;


    //ViewHolder class
    //TextView and ImageView holders are binded with relevant views in item of recyclerview.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewValue, textViewInfo;
        LinearLayout linearLayout;
        public ImageView safetyCoverImage;
        public int position = 0;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    recyclerViewItemClickListener.onItemClick(v, position);
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //When item view is clicked long, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    recyclerViewItemClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
            textViewInfo = (TextView) v.findViewById(R.id.safety_info);
            textViewValue = (TextView) v.findViewById(R.id.safety_value);
            safetyCoverImage = (ImageView) v.findViewById(R.id.safetyImage);
            linearLayout = (LinearLayout) v.findViewById(R.id.line2);
        }
    }

    //Set method of OnItemClickListener object
    public void setOnItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }


    public RecyclerViewAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //Adapter request a new item view
    //Create and return it.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.list_view_items, parent, false);
        return new ViewHolder(v);
    }

    //Last step before item is placed in recyclerview
    //TextViews and ImageView in viewholder which is attached to view is set with datas in model list
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position = position;
        holder.textViewInfo.setText(items.get(position).getInfo());
        holder.textViewValue.setText(items.get(position).getValue());

        setImageViewBackgroundWithADrawable(holder.safetyCoverImage, items.get(position).getSafetyCoverDrawableId());
        //if warning is true , change color of text and set warning pic to item(position)
        if (items.get(position).isWarning()) {
            //holder.linearLayout.setBackgroundColor(R.color.colorYellow);
            items.get(position).setSafetyCoverDrawableId(R.drawable.warning);
            setImageViewBackgroundWithADrawable(holder.safetyCoverImage, items.get(position).getSafetyCoverDrawableId());
            //holder.textViewValue.setTextColor(R.color.colorYellow);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //setBackground method is different for some android versions.
    public void setImageViewBackgroundWithADrawable(ImageView image, int drawable) {
        if (Build.VERSION.SDK_INT >= 22) {
            image.setBackground(context.getResources().getDrawable(drawable, null));
        } else if (Build.VERSION.SDK_INT >= 16) {
            image.setBackground(context.getResources().getDrawable(drawable));
        } else {
            image.setBackgroundDrawable(context.getResources().getDrawable(drawable));
        }
    }


}
