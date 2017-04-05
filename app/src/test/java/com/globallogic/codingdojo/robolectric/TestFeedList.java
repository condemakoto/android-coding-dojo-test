package com.globallogic.codingdojo.robolectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.globallogic.codingdojo.BuildConfig;
import com.globallogic.codingdojo.R;
import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.ui.activities.ItemDetailActivity;
import com.globallogic.codingdojo.ui.activities.MainActivity;
import com.globallogic.codingdojo.ui.fragments.FeedsFragment;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.*;

/**
 * @author julio.kun
 * @since 0.1
 * <p>
 *     Test the {@link MainActivity}
 * </p>
 */
@RunWith(org.robolectric.RobolectricTestRunner.class)
@Config(sdk = 18, constants = BuildConfig.class, application = TestApplication.class)
public class TestFeedList {

    private CustomFeedsRepository feedsRepository;

    @Before
    public void setUp() {
        TestApplication testApplication = (TestApplication) RuntimeEnvironment.application;
        feedsRepository = testApplication.getTestApplicationModule().getCustomFeedsRepository();
    }

    @Test
    public void testLoadListWithTwoElements() {
        ActivityController<MainActivity> controller  = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();

        MainActivity activity = controller.get();
        FeedsFragment fr = (FeedsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
        assertNotNull(fr);

        View view = fr.getView();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycle_view_rss);
        assertNotNull(rv);

        feedsRepository.sendResponse();

        assertTrue(rv.getAdapter().getItemCount() == 2);

        assertTrue(view.findViewById(R.id.rl_no_data_screen_container).getVisibility() == View.GONE);
        assertTrue(view.findViewById(R.id.rl_normal_screen_container).getVisibility() == View.VISIBLE);
        assertTrue(view.findViewById(R.id.rl_error_screen_container).getVisibility() == View.GONE);
    }

    @Test
    public void testLoadListWithNoElements() {
        ActivityController<MainActivity> controller  = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();

        MainActivity activity = controller.get();
        FeedsFragment fr = (FeedsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
        assertNotNull(fr);

        View view = fr.getView();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycle_view_rss);
        assertNotNull(rv);

        feedsRepository.sendResponseWithEmptyList();

        assertTrue(rv.getAdapter().getItemCount() == 0);

        assertTrue(view.findViewById(R.id.rl_no_data_screen_container).getVisibility() == View.VISIBLE);
        assertTrue(view.findViewById(R.id.rl_normal_screen_container).getVisibility() == View.GONE);
        assertTrue(view.findViewById(R.id.rl_error_screen_container).getVisibility() == View.GONE);
    }

    @Test
    public void testLoadListWithError() {
        ActivityController<MainActivity> controller  = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();

        MainActivity activity = controller.get();
        FeedsFragment fr = (FeedsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
        assertNotNull(fr);

        View view = fr.getView();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycle_view_rss);
        assertNotNull(rv);

        feedsRepository.sendResponseWithError();

        assertTrue(view.findViewById(R.id.rl_no_data_screen_container).getVisibility() == View.GONE);
        assertTrue(view.findViewById(R.id.rl_normal_screen_container).getVisibility() == View.GONE);
        assertTrue(view.findViewById(R.id.rl_error_screen_container).getVisibility() == View.VISIBLE);
    }

    /**
     * This test loads a RSS with only 1 item. Then clicks on that item and get sure that the correct activity
     * is opened with the correct data.
     */
    @Test
    public void testOpenDetailWithFirstElement() {
        ActivityController<MainActivity> controller  = Robolectric.buildActivity(MainActivity.class)
                .create()
                .start()
                .resume()
                .visible();

        MainActivity activity = controller.get();
        FeedsFragment fr = (FeedsFragment) activity.getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
        assertNotNull(fr);

        feedsRepository.sendResponse();

        View view = fr.getView();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recycle_view_rss);
        assertNotNull(rv);

        assertTrue(rv.getAdapter().getItemCount() == 2);

        //These lines are necessary to let the RecyclerView about the space available so it can create its child views.
        rv.measure(0, 0);
        rv.layout(0, 0, 100, 10000);
        assert(rv.getChildCount() > 0);

        View firstItemView = rv.getLayoutManager().findViewByPosition(0);
        assertNotNull(firstItemView);
        firstItemView.performClick();


        //check if it's opening the right activity
        ShadowActivity shadowActivity = Shadows.shadowOf(activity);
        Intent intent = shadowActivity.getNextStartedActivity();
        assertEquals(intent.getComponent().getClassName(), ItemDetailActivity.class.getCanonicalName());

        //.. with the right data
        Bundle extras = intent.getExtras();
        Item item = (Item) extras.getSerializable("item");
        assertNotNull(item);

        assertEquals("This is a mock description for the first item in the list", item.getDescription());
        assertEquals("First feed", item.getTitle());

    }


}
