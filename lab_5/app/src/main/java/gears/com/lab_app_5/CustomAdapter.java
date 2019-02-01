package gears.com.lab_app_5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yashodhan on 05-Feb-18, for Lab_app_5
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    private List<Item> itemList = new ArrayList<>();
    private int itemLayout;
    private Context mContext;
    CustomAdapter(List<Item> items, int itemLayout, Context context) {
        this.itemList = items;
        this.itemLayout = itemLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(itemLayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item t = itemList.get(position);
        holder.textView.setText(t.text);
        Picasso.with(mContext).setLoggingEnabled(true);
        Picasso.with(mContext).load(t.image_Url).into(holder.imageView);
    }

    public void add(List<Item> t){
        itemList.addAll(t);
        Log.d("hello",t.toString());
        notifyDataSetChanged();
    }

    public void remove(Item t){
        int position = itemList.indexOf(t);
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_layout_img_view);
            textView = itemView.findViewById(R.id.item_layout_text_view);
        }
    }
}
