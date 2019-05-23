package me.hatice.mqtt;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application implements Thread.UncaughtExceptionHandler {

    private static Context context;
    private static int RealmConfigVersion = 0;

    public static Context getAppContext() {
        return AppController.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        AppController.context = getApplicationContext();
        if (!BuildConfig.DEBUG) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }

        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(RealmConfigVersion)
                .migration( new MigrationRealm() )
                .build();

        Realm.setDefaultConfiguration(config);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        ///CrashLogActivity.start(this, e);
        System.exit(1);
    }

}
