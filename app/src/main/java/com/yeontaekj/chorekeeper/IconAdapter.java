package com.yeontaekj.chorekeeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by yeontaekj on 9/20/2017.
 */

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconViewHolder> {

    private Context context;
    private List<Integer> drawableIDList;

    public IconAdapter(Context context, List<Integer> drawableIDList) {
        this.context = context;
        this.drawableIDList = drawableIDList;
    }

    class IconViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;

        public IconViewHolder(ImageView itemView) {
            super(itemView);
            icon = itemView;
        }

        public void bindIcon(Drawable drawable) {
            icon.setImageDrawable(drawable);
        }
    }

    @Override
    public IconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ImageView view = (ImageView) inflater.inflate(R.layout.iconholder, parent, false);

        return new IconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IconViewHolder holder, int position) {
        int resID = drawableIDList.get(position).intValue();
        Drawable drawable = ResourcesCompat.getDrawable(context.getResources(), resID, null);
        holder.bindIcon(drawable);
    }

    @Override
    public int getItemCount() {
        return drawableIDList.size();
    }
}
