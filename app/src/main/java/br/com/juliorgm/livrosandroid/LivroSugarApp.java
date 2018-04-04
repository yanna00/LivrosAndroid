package br.com.juliorgm.livrosandroid;

import com.orm.SugarApp;
import com.orm.SugarContext;

public class LivroSugarApp extends SugarApp{

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
