package gears.com.lab_app_6;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

/**
 * Created by cse on 2/20/2018.
 */

public class ListViewAdapter extends ArrayAdapter<CryptoItem> {
    Context mContext;
    int res;
    public ListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.mContext = context;
        this.res = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public CryptoItem getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView t1,t2,t3;
        convertView = LayoutInflater.from(mContext).inflate(res,parent,false);
        CryptoItem t = getItem(position);


        t1 = convertView.findViewById(R.id.list_item_name);
        t2 = convertView.findViewById(R.id.list_item_symbol);
        t3 = convertView.findViewById(R.id.list_item_price);
        t1.setText(t.getName());
        t2.setText(t.getSymbol());
        t3.setText(String.valueOf(t.getPrice()));
        return convertView;

    }
}
