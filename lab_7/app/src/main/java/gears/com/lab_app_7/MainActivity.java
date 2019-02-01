package gears.com.lab_app_7;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    Context mContext;
    TextView textView,priceTextView,etv;
    boolean showPrices;
    ArrayList<String> cryptos = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();


    ActionMode mActionMode;

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        linearLayout = findViewById(R.id.main_linLayout);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        showPrices = preferences.getBoolean("pref_upd_curr_vals",true);
        preferences.registerOnSharedPreferenceChangeListener(this);
        Log.d("Hello","wassup");
        textView = findViewById(R.id.main_textView);
        priceTextView = findViewById(R.id.main_textView_price);
        etv = findViewById(R.id.exchange_tv);
        etv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu(view);
            }
        });
        registerForContextMenu(textView);

        Collections.addAll(cryptos, "Bitcoin","Ripple","Monero");
        Collections.addAll(prices,"10000","1","250");
        textView.setText(MakeString(prices));
        priceTextView.setText(MakeString(cryptos));


        priceTextView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                // Start the CAB using the ActionMode.Callback defined above
                mActionMode = startActionMode(mActionModeCallback);

                view.setSelected(true);
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.d("Hello",String.valueOf(resultCode));
        if (requestCode==1){
            if (resultCode == RESULT_OK){
                cryptos.addAll(data.getStringArrayListExtra("Cryptos"));
                prices.addAll(data.getStringArrayListExtra("Prices"));
                textView.setText(MakeString(cryptos));
                if(showPrices) {
                    priceTextView.setText(MakeString(prices));
                }
            }
        }
    }

    private String MakeString(ArrayList<String> data) {
        StringBuilder sb = new StringBuilder();
        for (String crypto : data){
            sb.append(crypto).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_first:
                startActivityForResult(new Intent(MainActivity.this,AddCryptoActivity.class),1);
                return true;
            case R.id.menu_second:
                startActivity(new Intent(MainActivity.  this,SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    public void popupMenu(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                Toast.makeText(mContext,menuItem.getTitle(),Toast.LENGTH_LONG).show();
                switch (menuItem.getItemId()){
                    case R.id.popup_first:
                        textView.setText(MakeString(cryptos));
                        priceTextView.setText(MakeString(prices));
                        return true;
                    case R.id.popup_second:
                        Toast.makeText(mContext,"Liked",Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });
        popupMenu.show();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clear_menu, menu);
    }

    boolean b = true;
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_clear:
                textView.setText("");
                priceTextView.setText("");
                return true;
            case R.id.menu_swap:
                if (b){
                    textView.setText(MakeString(prices));
                    priceTextView.setText(MakeString(cryptos));
                }
                else {
                    textView.setText(MakeString(cryptos));
                    priceTextView.setText(MakeString(prices));
                }
                b = !b;
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals ("pref_upd_curr_vals")){
            this.showPrices = sharedPreferences.getBoolean(key,false);
            if(showPrices){
                if (prices != null) {
                    priceTextView.setText(MakeString(prices));
                    priceTextView.setVisibility(View.VISIBLE);
                }
            }
            else
                priceTextView.setVisibility(View.GONE);
        }

        if(key.equals("pref_night_mode")){
            boolean b = sharedPreferences.getBoolean(key,true);
            if(b)
                linearLayout.setBackgroundColor(getResources().getColor(R.color.colorDark));
            else linearLayout.setBackgroundColor(getResources().getColor(R.color.whtie));
        }

        if(key.equals("pref_list")){
            String b = sharedPreferences.getString(key,"Coinbase");
            etv.setText(b);
        }


    }


    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.clear_menu, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_clear:
                    textView.setText("");
                    priceTextView.setText("");
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_swap:
                    if (b){
                        textView.setText(MakeString(prices));
                        priceTextView.setText(MakeString(cryptos));
                    }
                    else {
                        textView.setText(MakeString(cryptos));
                        priceTextView.setText(MakeString(prices));
                    }
                    b = !b;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };



}
