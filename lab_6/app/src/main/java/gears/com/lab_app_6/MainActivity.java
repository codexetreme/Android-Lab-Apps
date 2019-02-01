package gears.com.lab_app_6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import gears.com.lab_app_6.sqlDB.CryptoUtil;
import gears.com.lab_app_6.sqlDB.CryptoValuesReaderContract;


public class MainActivity extends AppCompatActivity {

    CryptoValuesReaderContract mdbHelper;
    Button btn;
    TextView textView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mdbHelper = new CryptoValuesReaderContract(this);


        final CryptoUtil util = new CryptoUtil(mdbHelper,CryptoUtil.WRITE_TO_DB);
        List<CryptoItem> k = util.getAllCryptos();

        listView = findViewById(R.id.main_listView);
        ListViewAdapter adapter = new ListViewAdapter(this,R.layout.list_item);
        listView.setAdapter(adapter);
        adapter.addAll(k);
        adapter.notifyDataSetChanged();

        Log.d("TAG",String.valueOf(k.size()));
        btn = findViewById(R.id.getCryptoButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,EntryActivity.class));

            }
        });
//        textView = findViewById(R.id.textViewmain);
//        util.insertValues(new CryptoItem("Bitcoin","BTC",10000.00f));
//        util.insertValues(new CryptoItem("Ripple","XRP",0.97f));
//        util.insertValues(new CryptoItem("Monero","XMR",232.00f));

        //        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }



}
