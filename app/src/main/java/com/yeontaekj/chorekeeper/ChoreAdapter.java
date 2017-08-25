package com.yeontaekj.chorekeeper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yeontaekj on 8/24/2017.
 */

public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {

    private Chore[] mChoreList;

    public ChoreAdapter(Chore[] choreList) {
        mChoreList = choreList;
    }

    class ChoreViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ChoreViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.iv_chore_icon);
            textView = (TextView) view.findViewById(R.id.tv_chore_name);
        }
    }

    @Override
    public ChoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.viewholder, parent, false);
        return new ChoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChoreViewHolder holder, int position) {
        holder.textView.setText(mChoreList[position].getName());
    }

    @Override
    public int getItemCount() {
        return mChoreList.length;
    }
}
