package net.drimmie.rob.ml;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<JSONObject>{
    private ArrayList<JSONObject> transactions;
    private Context context;
    private int resource;

    public TransactionAdapter(Context context, int resource, int textViewResourceId, ArrayList<JSONObject> transactions){
        super(context, resource, textViewResourceId, transactions);
        this.context = context;
        this.resource = resource;
        this.transactions = transactions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(resource, parent, false);
        TextView name = (TextView) itemView.findViewById(R.id.account_item_display_name);
//        TextView number = (TextView) itemView.findViewById(R.id.account_item_account_number);
//        TextView balance = (TextView) itemView.findViewById(R.id.account_item_balance);

        try {
//            name.setText("okie");
            name.setText(transactions.get(position).getString("id"));
//            number.setText(accounts.get(position).getString("account_number"));
//            String formattedBalance = DecimalFormat.getCurrencyInstance().format(
//                    Double.parseDouble(accounts.get(position).getString("balance"))
//            );
//            balance.setText(formattedBalance);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return itemView;
    }
}