package gears.com.lab_app_8;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.zip.Inflater;

/**
 * Created by cse on 3/6/2018.
 */

public class ListAdapter extends ArrayAdapter<LatLng> {
    Context mContext;
    int res;
    public ListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.mContext = context;
        this.res = resource;
    }

    @Nullable
    @Override
    public LatLng getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView t1,t2;
        convertView = LayoutInflater.from(mContext).inflate(res,parent,false);
        t1 = convertView.findViewById(R.id.list_lat);
        t2 = convertView.findViewById(R.id.list_long);
        LatLng latLng = getItem(position);
        t1.setText(String.valueOf( latLng.latitude));
        t2.setText(String.valueOf( latLng.longitude));
        return convertView;
    }
}
