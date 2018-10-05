package com.example.test.alltopicsexample.dagger.components;


import com.example.test.alltopicsexample.dagger.SharedManagerScope;
import com.example.test.alltopicsexample.dagger.managers.SharedPreferenceManager;
import com.example.test.alltopicsexample.dagger.modules.SharedPrefManagerModule;

import dagger.Component;

@SharedManagerScope
@Component(modules = SharedPrefManagerModule.class)
public interface SharedPrefComponent {
    SharedPreferenceManager getSharedPreferenceManager();

}
