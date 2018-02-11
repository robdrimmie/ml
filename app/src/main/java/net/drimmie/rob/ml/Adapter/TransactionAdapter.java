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

    static class ListViewHolder {
        TextView description;
        TextView amount;
        TextView date;
        TextView balance;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ListViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);

            holder = new ListViewHolder();
            holder.description = convertView.findViewById(R.id.transaction_item_description);
            holder.amount = convertView.findViewById(R.id.transaction_item_amount);
            holder.date = convertView.findViewById(R.id.transaction_item_date);
            holder.balance = convertView.findViewById(R.id.transaction_item_balance);

            convertView.setTag(holder);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }


        try {
            holder.description.setText(transactions.get(position).getString("description"));

            String formattedAmount = "";
            if (transactions.get(position).has("deposit_amount")) {
                holder.amount.setTextColor(Color.BLACK);
                formattedAmount = this.currency(transactions, position, "deposit_amount");
            }

            if (transactions.get(position).has("withdrawal_amount")) {
                holder.amount.setTextColor(Color.RED);
                formattedAmount = this.currency(transactions, position, "withdrawal_amount");
            }

            holder.amount.setText(formattedAmount);

            holder.date.setText(this.date(transactions, position, "date"));
            holder.balance.setText(this.currency(transactions, position, "balance"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return convertView;
    }
}