package net.drimmie.rob.ml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AccountDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        Intent intent = getIntent();
        String message = intent.getStringExtra("name");


        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(message);
    }
}
