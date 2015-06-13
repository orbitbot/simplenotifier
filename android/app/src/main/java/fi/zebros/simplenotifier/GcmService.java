package fi.zebros.simplenotifier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

public class GcmService extends GcmListenerService {
    public static final String TAG = GcmService.class.getSimpleName();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);

        Intent messageHandler = new Intent(this, IncomingMessageHandler.class);
        messageHandler.putExtra("from", from);
        messageHandler.putExtra("message", message);
        startService(messageHandler);
    }
}
