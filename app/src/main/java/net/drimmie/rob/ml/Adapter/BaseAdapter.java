package net.drimmie.rob.ml.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BaseAdapter extends ArrayAdapter<JSONObject> {
    public BaseAdapter(Context context, int resource, int textViewResourceId, ArrayList<JSONObject> transactions) {
        super(context, resource, textViewResourceId, transactions);
    }

    protected String format(ArrayList<JSONObject> list, int position, String name) {
        try {
            return DecimalFormat.getCurrencyInstance().format(
                    Double.parseDouble(list.get(position).getString(name))
            );
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return "";
    }
}
