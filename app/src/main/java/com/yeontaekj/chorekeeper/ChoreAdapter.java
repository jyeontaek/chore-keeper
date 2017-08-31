package com.yeontaekj.chorekeeper;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yeontaekj on 8/24/2017.
 */

public class ChoreAdapter extends RecyclerView.Adapter<ChoreAdapter.ChoreViewHolder> {

    private List<Chore> mChoreList;
    private Context mContext;

    public ChoreAdapter(Context context, List<Chore> choreList) {
        mChoreList = choreList;
        mContext = context;
    }

    class ChoreViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ImageButton calendar;

        public ChoreViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.iv_chore_icon);
            textView = (TextView) view.findViewById(R.id.tv_chore_name);
            calendar = (ImageButton) view.findViewById(R.id.button_calendar);
            calendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.show(((AppCompatActivity) mContext).getSupportFragmentManager(), "datePicker");
                }
            });
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
        holder.textView.setText(mChoreList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mChoreList.size();
    }
}
