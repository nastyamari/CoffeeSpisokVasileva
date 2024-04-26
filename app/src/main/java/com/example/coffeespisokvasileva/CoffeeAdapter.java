package com.example.coffeespisokvasileva;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {
    private final static String PHOTO_URL = "https://images.unsplash.com/";
    private List<Item> mFlowers;
    private Context mContext;

    CoffeeAdapter(List<Item> flowers) {
        this.mFlowers = flowers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item flower = mFlowers.get(position);
        holder.nameTextView.setText(flower.getTitle());
        holder.categoryTV.setText(flower.getDescription());
        Picasso.with(mContext)
                .load(PHOTO_URL + flower.getImage())
                .resize(200, 150)
                .into(holder.flowerImageView);

    }

    @Override
    public int getItemCount() {
        if (mFlowers == null) {
            return 0;
        }
        return mFlowers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView flowerImageView;
        TextView categoryTV;

        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            flowerImageView = (ImageView) itemView.findViewById(R.id.itemImageView);
            categoryTV = (TextView) itemView.findViewById(R.id.categoryTextView);
        }
    }
}
