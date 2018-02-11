package net.drimmie.rob.ml.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import net.drimmie.rob.ml.ListAdapter;
import net.drimmie.rob.ml.Model.Account;
import net.drimmie.rob.ml.R;
import net.drimmie.rob.ml.TransactionAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        Toolbar myToolbar = findViewById(R.id.accounts_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String accountId = intent.getStringExtra(AccountList.SELECTED_ACCOUNT);

        Account account = new Account(getApplicationContext());
        final ArrayList<JSONObject> transactionList = account.transactions(accountId);

        ListView transactionListView = findViewById(R.id.transactionListView);

        TransactionAdapter adapter = new TransactionAdapter(
                this,
                R.layout.account_item,
                R.id.account_item_display_name,
                transactionList
        );

        transactionListView.setAdapter(adapter);
    }
}
