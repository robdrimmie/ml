package net.drimmie.rob.ml.Model;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Account {
    private Context context;

    public Account(Context context) {
        this.context = context;
    }

    private String loadJSONFromAsset(String filename) {
        String json;
        try {
            AssetManager am = this.context.getAssets();
            InputStream is = am.open(filename);

            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public ArrayList<JSONObject> getAccounts() {
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();

        String data = loadJSONFromAsset("listOfAccounts.json");

        try {
            JSONArray accounts = new JSONArray(data);
            for (int accountIndex = 0; accountIndex < accounts.length(); accountIndex++) {
                list.add(accounts.getJSONObject(accountIndex));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public ArrayList<JSONObject> transactions(String accountId) {
        ArrayList<JSONObject> list = new ArrayList<>();

        String data = loadJSONFromAsset("accountTransactions.json");

        try {
            JSONArray accounts = new JSONArray(data);

            for (int accountIndex = 0; accountIndex < accounts.length(); accountIndex++) {
                JSONObject allTransactions = accounts.getJSONObject(accountIndex);
                if (allTransactions.has(accountId)) {
                    JSONArray transactions = (JSONArray) allTransactions.get(accountId);

                    for (int transactionIndex = 0; transactionIndex < transactions.length(); transactionIndex++ ) {
                        JSONArray activities = (JSONArray) transactions.getJSONObject(transactionIndex).get("activity");

                        for (int activityIndex = 0; activityIndex< activities.length(); activityIndex++) {
                            list.add(activities.getJSONObject(activityIndex));
                        }
                    }

                    return list;
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
