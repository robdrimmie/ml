package net.drimmie.rob.ml.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import net.drimmie.rob.ml.AccountList;
import net.drimmie.rob.ml.R;

import java.io.IOException;
import java.io.InputStream;

public class Main extends AppCompatActivity {
    public static final String ACCOUNTS = "net.drimmie.rob.ml.ACCOUNTS";

    private String accounts;

    private String loadJSONFromAsset(Context context, String fileName) {
        String json;
        try {
            AssetManager am = context.getAssets();
            String[] ffff = am.list("");

            InputStream is = am.open(fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accounts = loadJSONFromAsset(getApplicationContext(), "listOfAccounts.json");
   }

    public void open(View view) {
        Intent intent = new Intent(this, AccountList.class);
        intent.putExtra(ACCOUNTS, accounts);

        startActivity(intent);
    }
}
