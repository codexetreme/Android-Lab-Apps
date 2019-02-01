package gears.com.lab_app_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter customAdapter;
    List<Item> t = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            t = new JSONDownloader().execute(new URL("https://my-json-server.typicode.com/codexetreme/lab_5_json/db")).get();

        } catch (MalformedURLException | ExecutionException | InterruptedException e) {
            Log.d("hello",e.getMessage());
        }
        customAdapter = new CustomAdapter(t,R.layout.item_layout,this);
        recyclerView.setAdapter(customAdapter);
    }




}
