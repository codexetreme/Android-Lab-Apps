package gears.com.lab_app_8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    EditText t1,t2;
    ListView l1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.place_lat);
        t2 = findViewById(R.id.place_long);
        l1 = findViewById(R.id.recents);
        b1 = findViewById(R.id.go_btn);

        final ListAdapter adapter = new ListAdapter(this,R.layout.list_item);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                LatLng latLng = (LatLng) l1.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this,MapsActivity.class);
                i.putExtra("latlang",new int[]{(int) latLng.latitude, (int) latLng.longitude});
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(t1.getText().toString())){

                    if(!TextUtils.isEmpty(t2.getText().toString())){
                        Intent i = new Intent(MainActivity.this,MapsActivity.class);
                        int lat = Integer.parseInt(t1.getText().toString());
                        int lng = Integer.parseInt(t2.getText().toString());
                        i.putExtra("latlang",new int[]{lat,lng});
                        startActivity(i);
                        adapter.add(new LatLng(lat,lng));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }




}
