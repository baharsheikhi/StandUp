package cs3500.android.standup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    JSONObject jsonObject;
    String num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.test);
        lv = (ListView)findViewById(R.id.ourlist);
        MyDownloadTask download = new MyDownloadTask();
        download.execute();
        while (download.json == "") {
            Log.d("test", "ITS EMPTY");
        }
        Log.d("yo", download.json);
        try {
            jsonObject = new JSONObject(download.json);
            num = Integer.toString(jsonObject.length());
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage(), e);
            e.printStackTrace();
        }
        String[] testArray = {"hello", "world", "fux", "wid", "us"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.text, testArray);

        lv = (ListView) findViewById(R.id.ourlist);
        lv.setAdapter(adapter);
        Log.d("JSON", num);
    }

    class MyDownloadTask extends AsyncTask<Void,Void,String>
    {
        String json;
        MyDownloadTask() {
            json = "";
        }
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("https://newsapi.org/v1/articles?source=techcrunch&apiKey=de48e3ded2c640b18c2e8136c0e314d1");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    json = stringBuilder.toString();
                    return stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
    }
}






