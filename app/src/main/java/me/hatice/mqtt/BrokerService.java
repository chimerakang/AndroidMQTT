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

        return START_STICKY;
    }
}
