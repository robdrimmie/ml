package net.drimmie.rob.ml;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.drimmie.rob.ml.Activity.AccountDetail;
import net.drimmie.rob.ml.Activity.Main;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountList extends AppCompatActivity {
    private ListView accountsListView;
    private ArrayAdapter <String> accountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar myToolbar = findViewById(R.id.accounts_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quit, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_quit:
                quitToMain();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void quitToMain() {
        Context context = getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key),
                context.MODE_PRIVATE
        );

        // TODO: Move preference management into single place
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_false)
        );
        editor.commit();

        finish();
    }

    private void oldstuff() {
//        accountsListView = (ListView) findViewById(R.id.accountListView);

        Intent intent = getIntent();
//
//        JSONArray accountsJ;
////        try {
//            //accountsJ = new JSONArray(intent.getStringExtra(Main.ACCOUNTS));
//
//            //Log.d("wtf", new Integer(accountsJ.length()).toString());
//            String[] accounts = new String[]{
//                    "Chequing", "Saving", "Tax Free", "Credit Card"
//            };
//            ArrayList<String> accountList = new ArrayList<String>();
//            accountList.addAll(Arrays.asList(accounts));
//
//            accountListAdapter = new ArrayAdapter<String>(this, R.layout.account_item, accountList);
//
//            accountsListView.setAdapter(accountListAdapter);
//
//            accountsListView.setOnItemClickListener(new ListView.OnItemClickListener() {
//
//
//                @Override
//                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                    // Do something when a list item is clicked
//                    Intent intent = new Intent(getApplicationContext(), AccountDetail.class);
//                    String message = "some account";
//
//                    intent.putExtra("name", "value");
//                    startActivity(intent);
//                }
//            });
////        } catch (JSONException ex) {
////            ex.printStackTrace();
////        }


    }
}

