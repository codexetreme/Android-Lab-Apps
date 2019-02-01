package gears.com.lab_app_6;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import gears.com.lab_app_6.sqlDB.CryptoUtil;
import gears.com.lab_app_6.sqlDB.CryptoValuesReaderContract;

public class EntryActivity extends AppCompatActivity {

    EditText e1,e2,e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        e1 = findViewById(R.id.entry_name);
        e2 = findViewById(R.id.entry_symbol);
        e3 = findViewById(R.id.entry_price);
        Button b = findViewById(R.id.entry_btn);
        final CryptoValuesReaderContract mdbHelper = new CryptoValuesReaderContract(this);

        final Context t = this;
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(e1.getText().toString().equals(null) &&e2.getText().toString().equals(null) && e3.getText().toString().equals(null))) {
                    final CryptoUtil util = new CryptoUtil(mdbHelper, CryptoUtil.WRITE_TO_DB);
                    util.insertValues(new CryptoItem(e1.getText().toString(), e2.getText().toString(), Float.parseFloat(e3.getText().toString())));
                    Toast.makeText(t,"Added",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getParent().getApplicationContext(),"Some Value is empty",Toast.LENGTH_LONG).show();

            }
        });
    }
}
