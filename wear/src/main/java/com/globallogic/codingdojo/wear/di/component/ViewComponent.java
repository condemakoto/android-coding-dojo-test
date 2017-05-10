package com.globallogic.codingdojo.wear.di.component;

import com.globallogic.codingdojo.wear.ui.activity.MainActivity;
import com.globallogic.codingdojo.wear.di.module.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Julio Kun
 */
//@PerActivity
@Singleton
@Component  (modules = ViewModule.class)
public interface ViewComponent {
    void inject(MainActivity activity);
}
