package com.globallogic.codingdojo.wear.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WearableRecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.wear.R;
import com.globallogic.codingdojo.wear.ui.adapter.FeedsAdapter;
import com.globallogic.codingdojo.wear.di.component.DaggerViewComponent;
import com.globallogic.codingdojo.wear.di.module.ViewModule;
import com.globallogic.codingdojo.wear.ui.viewhelper.MyOffsettingHelper;
import com.globallogic.mvp.presenters.FeedsPresenter;
import com.globallogic.mvp.view.FeedsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements FeedsAdapter.OnFeedSelectedListener, FeedsView {

    @Bind(R.id.recycle_view_rss)
    protected WearableRecyclerView wearableRecyclerView;
    @Bind(R.id.text_view_no_data_screen)
    protected TextView noDataView;
    @Bind(R.id.text_view_error_screen)
    protected TextView errorView;
    @Bind(R.id.progress_bar)
    protected ProgressBar progressBar;
    @Inject protected FeedsPresenter mPresenter;
    private FeedsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feeds_list_layout);
        ButterKnife.bind(this);

        DaggerViewComponent.builder()
                .viewModule(new ViewModule())
                .build().inject(this);

        wearableRecyclerView.setCenterEdgeItems(true);
        wearableRecyclerView.setOffsettingHelper(new MyOffsettingHelper());

        mAdapter = new FeedsAdapter(this);
        wearableRecyclerView.setAdapter(mAdapter);

        mPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onFeedSelected(Item item) {
        startActivity(ItemDetailActivity.newIntent(this, item));
    }

    @Override
    public void clearFeeds() {
        mAdapter.clearFeeds();
    }

    @Override
    public void displayFeeds(RSS rss) {
        mAdapter.setList(rss.getItems());
        wearableRecyclerView.setVisibility(View.VISIBLE);
        noDataView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void displayNoAvailableDataScreen() {
        wearableRecyclerView.setVisibility(View.GONE);
        noDataView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void displayErrorScreen() {
        wearableRecyclerView.setVisibility(View.GONE);
        noDataView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
