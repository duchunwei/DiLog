package org.di.log;

import android.app.Application;

import com.google.gson.Gson;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DiLogManager.init(new DiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return object -> new Gson().toJson(object);
            }

            @Override
            public boolean includeThread() {
                return true;
            }

            @Override
            public int stackTraceDepth() {
                return 10;
            }

            @Override
            public String getGlobalTag() {
                return "dcw";
            }
        }, new DiConsolePrinter(), new DiFilePrinter(getCacheDir().getAbsolutePath(), 0));

    }
}
