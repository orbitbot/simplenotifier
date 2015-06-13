package fi.zebros.simplenotifier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView sender = (TextView) findViewById(R.id.sender);
        TextView message = (TextView) findViewById(R.id.message_content);

        Intent intent = getIntent();
        sender.setText(intent.getStringExtra("from"));
        message.setText(intent.getStringExtra("message"));
    }
}
