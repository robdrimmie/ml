package net.drimmie.rob.ml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<JSONObject>{
    private ArrayList<JSONObject> accounts;
    private Context context;
    private int resource;

    public ListAdapter(Context context, int resource, int textViewResourceId, ArrayList<JSONObject> accounts){
        super(context, resource, textViewResourceId, accounts);
        this.context = context;
        this.resource = resource;
        this.accounts = accounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(resource, parent, false);
        TextView name = (TextView) itemView.findViewById(R.id.account_item_display_name);
        TextView number = (TextView) itemView.findViewById(R.id.account_item_account_number);
        TextView balance = (TextView) itemView.findViewById(R.id.account_item_balance);

        try {
            name.setText(accounts.get(position).getString("id"));
            number.setText(accounts.get(position).getString("name"));
            balance.setText(accounts.get(position).getString("sex"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return itemView;
    }
}