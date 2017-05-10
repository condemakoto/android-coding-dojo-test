package com.globallogic.codingdojo.wear.ui.adapter;

import android.support.wearable.view.WearableRecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.wear.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Juliio Kun
 * @version 1.0
 */

public class FeedsAdapter extends WearableRecyclerView.Adapter<FeedsAdapter.ViewHolder> {

    private OnFeedSelectedListener listener;
    private final List<Item> mList = new ArrayList<>();

    public FeedsAdapter(OnFeedSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewHolder vh = new ViewHolder(inflater.inflate(R.layout.feeds_list_item, parent, false));
        vh.itemView.setOnClickListener(this.feedClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mList.get(position);

        holder.feedTitle.setText(item.getTitle());
        holder.feedDescription.setText(Html.fromHtml(item.getDescription()));
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Item> items) {
        mList.clear();
        mList.addAll(items);
        this.notifyDataSetChanged();
    }

    public void clearFeeds() {
        mList.clear();
        this.notifyDataSetChanged();
    }

    protected class ViewHolder extends WearableRecyclerView.ViewHolder {
        @Bind(R.id.item_rss_title)
        public TextView feedTitle;
        @Bind(R.id.item_rss_description)
        public TextView feedDescription;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private final View.OnClickListener feedClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item item = (Item) v.getTag();
            listener.onFeedSelected(item);
        }
    };

    public interface OnFeedSelectedListener {
        void onFeedSelected(Item item);
    }
}

