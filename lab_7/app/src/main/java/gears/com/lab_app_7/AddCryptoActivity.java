package gears.com.lab_app_7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddCryptoActivity extends AppCompatActivity {

    EditText editText,priceEditText;
    Button addButton,submitButton;
    final ArrayList<String> cryptos = new ArrayList<>();
    final ArrayList<String> prices = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_crypto);
        editText = findViewById(R.id.add_edittext);
        priceEditText = findViewById(R.id.add_edittext_price);
        addButton = findViewById(R.id.add_crypto_btn);
        submitButton = findViewById(R.id.add_submit);
    }

    public void addItems(View view) {
        String cryptoName = editText.getText().toString();
        String cryptoPrice = priceEditText.getText().toString();
        cryptos.add(cryptoName);
        prices.add(cryptoPrice);
        Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
        editText.setText("");
        priceEditText.setText("");
    }

    public void submit(View view) {
        final Intent t  = new Intent();
        t.putExtra("Cryptos",cryptos);
        t.putExtra("Prices",prices);
        setResult(RESULT_OK,t);
        finish();
    }
}
