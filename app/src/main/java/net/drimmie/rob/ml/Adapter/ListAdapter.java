package net.drimmie.rob.ml.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.drimmie.rob.ml.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter{
    private ArrayList<JSONObject> accounts;
    private Context context;
    private int resource;

    public ListAdapter(Context context, int resource, int textViewResourceId, ArrayList<JSONObject> accounts){
        super(context, resource, textViewResourceId, accounts);
        this.context = context;
        this.resource = resource;
        this.accounts = accounts;
    }

    static class ListViewHolder {
        TextView name;
        TextView number;
        TextView balance;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);

            holder = new ListViewHolder();
            holder.name = convertView.findViewById(R.id.account_item_display_name);
            holder.number = convertView.findViewById(R.id.account_item_account_number);
            holder.balance = convertView.findViewById(R.id.account_item_balance);

            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }


        try {
            holder.name.setText(accounts.get(position).getString("display_name"));
            holder.number.setText(accounts.get(position).getString("account_number"));
            holder.balance.setText(this.currency(accounts, position, "balance"));

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return convertView;
    }
}