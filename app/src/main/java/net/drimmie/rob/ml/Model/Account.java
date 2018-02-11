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
    private JSONArray accounts;

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

    private void load(String filename) {
        String data = loadJSONFromAsset(filename);

        try {
            accounts = new JSONArray(data);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<JSONObject> getAccounts() {
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();

        this.load("listOfAccounts.json");

        try {
            for (int accountIndex = 0; accountIndex < this.accounts.length(); accountIndex++) {
                list.add(this.accounts.getJSONObject(accountIndex));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
