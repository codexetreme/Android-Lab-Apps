package gears.com.lab_app_9_socket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class SelectorActivity extends AppCompatActivity {

    RadioGroup grp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);
        grp = findViewById(R.id.radio_grp);

        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){

                    case R.id.server_radio_btn:
                        startActivity(new Intent(SelectorActivity.this,MainActivity.class));
                        break;
                    case R.id.client_radio_btn:
                        startActivity(new Intent(SelectorActivity.this,ClientActivity.class));
                        break;
                }
            }
        });

    }
}
