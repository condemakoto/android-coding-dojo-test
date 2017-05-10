package com.globallogic.codingdojo.wear.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.wear.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Julio Kun
 */

public class ItemDetailActivity extends Activity {

    private final static String KEY_ITEM = "item";
    @Bind(R.id.item_rss_image)
    protected ImageView vFeedImage;
    @Bind(R.id.item_rss_title)
    protected TextView vFeedTitle;
    @Bind(R.id.item_rss_description)
    protected TextView vFeedDescription;
    @Bind(R.id.tv_item_rss_pub_date)
    protected TextView vFeedDate;

    public static Intent newIntent(Context context, Item item) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(KEY_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_layout);
        ButterKnife.bind(this);

        Item item = (Item) getIntent().getSerializableExtra(KEY_ITEM);
        vFeedTitle.setText(item.title);
        vFeedDescription.setText(Html.fromHtml(item.fullContent));
        vFeedDate.setText(item.pubDate);

        if (item.content != null && !TextUtils.isEmpty(item.content.url)) {
            Picasso.with(vFeedTitle.getContext())
                    .load(item.content.url)
                    .into(vFeedImage);
        }
    }


}
