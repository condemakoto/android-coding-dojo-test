package com.globallogic.codingdojo.robolectric;

import android.app.Application;

import com.globallogic.codingdojo.data.dto.RssDTO;
import com.globallogic.codingdojo.data.repository.FeedsRepository;
import com.globallogic.codingdojo.data.service.FeedsRepositoryImplementation;
import com.globallogic.codingdojo.di.module.ApplicationModule;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.RSSMockFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Callback;

/**
 * @author Julio Kun
 */

public class TestApplicationModule extends ApplicationModule {

    private CustomFeedsRepository customFeedsRepository;
    private RSSMockFactory rssFactory;

    public TestApplicationModule(Application app) {
        super(app);

        this.rssFactory = new RSSMockFactory();
        this.customFeedsRepository = new CustomFeedsRepository(rssFactory);
    }

    @Override
    public FeedsRepository provideFeedRepository(FeedsRepositoryImplementation feedsRepositoryImplementation) {
        return customFeedsRepository;
    }

    public CustomFeedsRepository getCustomFeedsRepository() {
        return this.customFeedsRepository;
    }

}
