package net.drimmie.rob.ml.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        // TODO: Move preference management into single place
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                context.MODE_PRIVATE
        );

        int opened = sharedPref.getInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_false)
        );

        if (opened == 1) {
            Log.d("wtf", "Opened is true");
            openAccountList();
        }

        accounts = loadJSONFromAsset(context, "listOfAccounts.json");
   }

   protected void openAccountList() {
       Log.d("wtf", "Opening Account List");
       Intent intent = new Intent(this, AccountList.class);
       intent.putExtra(ACCOUNTS, accounts);

       Log.d("wtf", "starting activity with intent");
       startActivity(intent);
   }

    public void open(View view) {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                context.MODE_PRIVATE
        );

        // TODO: Move preference management into single place
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_true)
        );
        editor.commit();

        openAccountList();
    }
}
