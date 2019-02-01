package gears.com.lab_app_5;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yashodhan on 05-Feb-18, for Lab_app_5
 */

public class JSONDownloader extends AsyncTask<URL,Integer,List<Item>> {
    ProgressBar progressBar;
    String TAG = "hello";
    String response;


    @Override
    protected List<Item> doInBackground(URL... urls) {
        try {

            HttpURLConnection connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            response = convertStreamToString(inputStream);
        } catch (Exception e){
            Log.d(TAG,e.getMessage());
        }
        List<Item> t = new ArrayList<>();
        try {
            t = ParseJSON(response);
        } catch (JSONException e) {
            Log.d(TAG,e.getMessage());
        }
        return t;
    }

    private List<Item> ParseJSON(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        List<Item> items= new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray("data");
        for (int i=0; i<array.length();i++){
            String url = array.getJSONObject(i).getString("url");
            String text = array.getJSONObject(i).getString("text");
            Item t = new Item(url,text);
            items.add(t);
        }

        return items;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(List<Item> t) {
        super.onPostExecute(t);
    }
}
