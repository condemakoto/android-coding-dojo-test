package com.globallogic.codingdojo.robolectric;

import com.globallogic.codingdojo.data.dto.RssDTO;
import com.globallogic.codingdojo.data.repository.FeedsRepository;
import com.globallogic.codingdojo.mock.CustomRepository;
import com.globallogic.codingdojo.mock.RSSMockFactory;

import retrofit2.Callback;

/**
 * @author Julio Kun
 */

public class CustomFeedsRepository extends CustomRepository implements FeedsRepository {

    public CustomFeedsRepository(RSSMockFactory rssMockFactory) {
        super(rssMockFactory);
    }

    @Override
    public void getFeeds(Callback<RssDTO> callback) {
        setCallback(callback);
    }
}
