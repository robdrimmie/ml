package net.drimmie.rob.ml.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.drimmie.rob.ml.R;


public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );

        int opened = sharedPref.getInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_false)
        );

        if (opened == 1) {
            Log.d("wtf", "Opened is true");
            openAccountList();
        }
   }

   protected void openAccountList() {
       startActivity(new Intent(this, AccountList.class));
   }

    public void open(View view) {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
        );

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_true)
        );
        editor.apply();

        openAccountList();
    }
}
