/*
 * @Chandra Dhakite
 * Recycler view class to show the list of Restaurant menus
 */

package com.chandra.restaurants.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chandra.restaurants.R;
import com.chandra.restaurants.domain.models.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    final static String TAG = "RecycleViewAdapter";

    private List<Menu> menuList = null;
    private ArrayList<Menu> arraylist;
    Context mContext;


    public RecycleViewAdapter(Context mContext) {
        this.mContext = mContext;

    }

    public void setSearchArray(List<Menu> menuList) {
        this.menuList = menuList;
        this.arraylist = new ArrayList<Menu>();
        this.arraylist.addAll(this.menuList);
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(menuList.get(position).getName());
        String id = Integer.toString(menuList.get(position).getId());
        holder.id.setText(id);
        holder.price.setText("$ " + menuList.get(position).getPrice());
        if (menuList.get(position).getIs_available())
            holder.isAvailableIcon.setImageResource(R.drawable.available);
        else
            holder.isAvailableIcon.setImageResource(R.drawable.na);


    }

    @Override
    public int getItemCount() {

        if (menuList != null)
            return menuList.size();
        else return 0;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView id;
        TextView price;
        ImageView isAvailableIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
            price = itemView.findViewById(R.id.price);
            isAvailableIcon = itemView.findViewById(R.id.isAvailableIcon);

        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (menuList != null) {
            menuList.clear();
            if (charText.length() == 0) {
                menuList.addAll(arraylist);
            } else {
                for (Menu wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        menuList.add(wp);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
}