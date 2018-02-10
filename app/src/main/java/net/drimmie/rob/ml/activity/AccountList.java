package net.drimmie.rob.ml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.drimmie.rob.ml.activity.AccountDetail;
import net.drimmie.rob.ml.activity.Main;

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

        accountsListView = (ListView) findViewById(R.id.accountListView);

        Intent intent = getIntent();

        JSONArray accountsJ;
        try {
            accountsJ = new JSONArray(intent.getStringExtra(Main.ACCOUNTS));

            Log.d("wtf", new Integer(accountsJ.length()).toString());
            String[] accounts = new String[]{
                    "Chequing", "Saving", "Tax Free", "Credit Card"
            };
            ArrayList<String> accountList = new ArrayList<String>();
            accountList.addAll(Arrays.asList(accounts));

            accountListAdapter = new ArrayAdapter<String>(this, R.layout.account_item, accountList);

            accountsListView.setAdapter(accountListAdapter);

            accountsListView.setOnItemClickListener(new ListView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                    // Do something when a list item is clicked
                    Intent intent = new Intent(getApplicationContext(), AccountDetail.class);
                    String message = "some account";

                    intent.putExtra("name", "value");
                    startActivity(intent);
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


    }
}

