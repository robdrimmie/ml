package net.drimmie.rob.ml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "net.drimmie.rob.ml.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void open(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        String message = "open";

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
