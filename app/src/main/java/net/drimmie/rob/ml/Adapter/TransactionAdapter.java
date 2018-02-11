package net.drimmie.rob.ml.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.drimmie.rob.ml.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TransactionAdapter extends BaseAdapter{
    private ArrayList<JSONObject> transactions;
    private Context context;
    private int resource;

    public TransactionAdapter(Context context, int resource, int textViewResourceId, ArrayList<JSONObject> transactions){
        super(context, resource, textViewResourceId, transactions);

        this.context = context;
        this.resource = resource;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(resource, parent, false);
        TextView description = itemView.findViewById(R.id.transaction_item_description);
        TextView amount = itemView.findViewById(R.id.transaction_item_amount);
        TextView balance = itemView.findViewById(R.id.transaction_item_balance);

        try {
            description.setText(transactions.get(position).getString("description"));

            String formattedAmount = "";
            if (transactions.get(position).has("deposit_amount")) {
                amount.setTextColor(Color.BLACK);
                formattedAmount = this.format(transactions, position, "deposit_amount");
            }

            if (transactions.get(position).has("withdrawal_amount")) {
                amount.setTextColor(Color.RED);
                formattedAmount = this.format(transactions, position, "withdrawal_amount");
            }

            amount.setText(formattedAmount);

            balance.setText(this.format(transactions, position, "balance"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return itemView;
    }
}