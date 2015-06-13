package fi.zebros.simplenotifier;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    Button requestTokenBtn;
    Button invalidateTokenBtn;
    TextView tokenResult;

    String senderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senderId = getString(R.string.senderId);
        tokenResult = (TextView) findViewById(R.id.token_result);
        requestTokenBtn = (Button) findViewById(R.id.request_token_btn);
        invalidateTokenBtn = (Button) findViewById(R.id.invalidate_token_btn);

        final Handler handler = new Handler();

        requestTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "requesting token", Toast.LENGTH_SHORT).show();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            String token = InstanceID.getInstance(MainActivity.this).getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                            final String success = "registration succeeded." + "\nsenderId: " + senderId + "\ntoken: " + token;
                            Log.i(TAG, success);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tokenResult.setText(success);
                                }
                            });
                        } catch (final IOException e) {
                            final String error = "registration failed." + "\nsenderId: " + senderId + "\nerror: " + e.getMessage();
                            Log.e(TAG, error, e);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tokenResult.setText(error);
                                }
                            });
                        }
                        return null;
                    }
                }.execute();
            }
        });

        invalidateTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "invalidating token", Toast.LENGTH_SHORT).show();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            InstanceID.getInstance(MainActivity.this).deleteToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
                            final String success = "delete token succeeded." +"\nsenderId: " + senderId;
                            Log.i(TAG, success);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tokenResult.setText(success);
                                }
                            });
                        } catch (final IOException e) {
                            final String error = "remove token failed." + "\nsenderId: " + senderId + "\nerror: " + e.getMessage();
                            Log.e(TAG, error, e);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tokenResult.setText(error);
                                }
                            });
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
