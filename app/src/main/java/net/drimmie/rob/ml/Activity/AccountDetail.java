package net.drimmie.rob.ml.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import net.drimmie.rob.ml.Model.Account;
import net.drimmie.rob.ml.R;
import net.drimmie.rob.ml.Adapter.TransactionAdapter;

import org.json.JSONObject;

import java.util.ArrayList;

public class AccountDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        Intent intent = getIntent();
        String accountId = intent.getStringExtra(AccountList.SELECTED_ACCOUNT);

        Account account = new Account(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.accounts_toolbar);
        toolbar.setTitle(account.name(accountId));
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        final ArrayList<JSONObject> transactionList = account.transactions(accountId);

        ListView transactionListView = findViewById(R.id.transactionListView);

        TransactionAdapter adapter = new TransactionAdapter(
                this,
                R.layout.transaction_item,
                R.id.transaction_item_description,
                transactionList
        );

        transactionListView.setAdapter(adapter);
    }
}
