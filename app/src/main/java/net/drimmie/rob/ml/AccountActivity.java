package net.drimmie.rob.ml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountActivity extends AppCompatActivity {
    private ListView accountsListView;
    private ArrayAdapter <String> accountListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accountsListView = (ListView) findViewById(R.id.accountListView);

        String[] accounts = new String [] {
                "Chequing", "Saving", "Tax Free", "Credit Card"
        };
        ArrayList<String> accountList = new ArrayList<String>();
        accountList.addAll(Arrays.asList(accounts));

        accountListAdapter = new ArrayAdapter<String>(this, R.layout.account_item, accountList);

        accountsListView.setAdapter(accountListAdapter);

    }
}

