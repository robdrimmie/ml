package net.drimmie.rob.ml.Activity;

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
import net.drimmie.rob.ml.ListAdapter;
import net.drimmie.rob.ml.Model.Account;
import net.drimmie.rob.ml.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountList extends AppCompatActivity {
    public static final String SELECTED_ACCOUNT = "net.drimmie.rob.ml.SELECTED_ACCOUNT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Toolbar myToolbar = findViewById(R.id.accounts_toolbar);
        setSupportActionBar(myToolbar);

        Account account = new Account(getApplicationContext());

        final ArrayList<JSONObject> listItems = account.getAccounts();

        ListView accountListView = findViewById(R.id.accountListView);

        ListAdapter adapter = new ListAdapter(
        this,
                R.layout.account_item,
                R.id.account_item_display_name,
                listItems
        );

        accountListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AccountDetail.class);

                try {
                    String message = listItems.get(position).getString("id");

                    intent.putExtra(SELECTED_ACCOUNT , message);
                    startActivity(intent);

                } catch (JSONException ex ) {
                    ex.printStackTrace();
                }
            }
        });

        accountListView.setAdapter(adapter);
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
                Context.MODE_PRIVATE
        );

        // TODO: Move preference management into single place
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                getString(R.string.preference_opened),
                getResources().getInteger(R.integer.preference_opened_false)
        );
        editor.apply();

        finish();
    }
}

