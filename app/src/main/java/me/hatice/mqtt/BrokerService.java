package me.hatice.mqtt;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.moquette.server.Server;

/**
 * Created by paula on 25/12/17.
 */

public class BrokerService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("borker", "onStartCommand");

        //run broker
        try {
            new Server().startServer();
            Toast.makeText(getApplicationContext(), "Broker started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("launch broker", "io exception");
        }

        /*
        Server server = new Server();
        try {
            MemoryConfig memoryConfig = new MemoryConfig(new Properties());
            memoryConfig.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME,
                    Environment.getExternalStorageDirectory().getAbsolutePath() +
                            File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);

            server.startServer(memoryConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            MemoryConfig memoryConfig = new MemoryConfig(new Properties());
            memoryConfig.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME,
                    Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);
            server.startServer(memoryConfig);

            // server.startServer();//is not working due to DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME;
            Log.d("broker","Server Started");
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (Exception e){ e.printStackTrace(); }
        */

        return START_STICKY;
    }
}
