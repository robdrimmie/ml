package net.drimmie.rob.ml.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import net.drimmie.rob.ml.Adapter.ListAdapter;
import net.drimmie.rob.ml.Model.Account;
import net.drimmie.rob.ml.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AccountList extends AccountBase {
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
}

