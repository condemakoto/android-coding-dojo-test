package com.globallogic.codingdojo.wear.di.module;

import com.globallogic.codingdojo.data.dto.ContentDTO;
import com.globallogic.codingdojo.data.dto.ItemDTO;
import com.globallogic.codingdojo.data.dto.RssDTO;
import com.globallogic.codingdojo.data.repository.FeedsRepository;
import com.globallogic.codingdojo.data.service.FeedsRepositoryImplementation;
import com.globallogic.codingdojo.data.service.ServiceFacade;
import com.globallogic.codingdojo.domain.mappers.ContentMapper;
import com.globallogic.codingdojo.domain.mappers.ItemMapper;
import com.globallogic.codingdojo.domain.mappers.RssMapper;
import com.globallogic.codingdojo.domain.mappers.Transformable;
import com.globallogic.codingdojo.domain.model.Content;
import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Julio Kun
 */
@Module
public class ViewModule {

    @Provides
    @Singleton
    ServiceFacade providesServiceFacade() {
        return new ServiceFacade();
    }

    @Provides
    @Singleton
    public FeedsRepository provideFeedRepository(FeedsRepositoryImplementation feedsRepositoryImplementation) {
        return feedsRepositoryImplementation;
    }

    @Provides
    Transformable<RssDTO, RSS> provideRssMapper(RssMapper mapper) {
        return mapper;
    }

    @Provides
    Transformable<ItemDTO, Item> provideItemMapper(ItemMapper mapper) {
        return mapper;
    }

    @Provides
    Transformable<ContentDTO, Content> provideContentMapper(ContentMapper mapper) {
        return mapper;
    }
}
