package com.globallogic.codingdojo.robolectric;

import com.globallogic.codingdojo.di.component.ApplicationComponent;
import com.globallogic.codingdojo.di.component.DaggerApplicationComponent;
import com.globallogic.codingdojo.di.module.ApplicationModule;
import com.globallogic.codingdojo.ui.CodingDojoApplication;

/**
 * @author Julio Kun
 */

public class TestApplication extends CodingDojoApplication {

    private TestApplicationModule testApplicationModule;

    @Override
    protected ApplicationModule getApplicationModule() {
        this.testApplicationModule = new TestApplicationModule(this);
        return testApplicationModule;
    }

    public TestApplicationModule getTestApplicationModule() {
        return testApplicationModule;
    }
}
