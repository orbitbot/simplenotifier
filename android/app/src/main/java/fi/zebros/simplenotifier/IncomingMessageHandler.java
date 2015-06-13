package fi.zebros.simplenotifier;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class IncomingMessageHandler extends IntentService {
    public static final String TAG = IncomingMessageHandler.class.getSimpleName();

    public IncomingMessageHandler() {
        super(TAG);
    }

    public IncomingMessageHandler(String name) {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String message = intent.getStringExtra("message");
        String from = intent.getStringExtra("from");

        Intent displayMessageIntent = new Intent(this, DisplayMessageActivity.class);
        displayMessageIntent.putExtra("message", message);
        displayMessageIntent.putExtra("from", from);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, displayMessageIntent, PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("GCM Message from " + from)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }
}
