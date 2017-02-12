package cs3500.android.standup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.facebook.FacebookSdk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private JSONObject jsonObject;
    private JSONArray articles;
    private List<String> toRender;
    private MyDownloadTask downloadTask;

    public MainActivity() {
        this.toRender = new ArrayList<>();
        this.downloadTask = new MyDownloadTask("https://newsapi.org/v1/articles?source=techcrunch&apiKey=de48e3ded2c640b18c2e8136c0e314d1");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //inherited behvaior
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the list view from the xml
        lv = (ListView)findViewById(R.id.listen_up);

        //download the data
        downloadTask.execute();

        //the data is returned in the format of a jsonObject, the very last field of
        //the jsonObject is articles
        //articles
        //each of these is a JSON Object
        //with an author, title, description, url, urlToImage, and publishedAt
        try {
            jsonObject = new JSONObject(downloadTask.getJSON());
            articles = (JSONArray) jsonObject.get("articles");
            getListViewString(articles, this.toRender);
        } catch (JSONException e) {
            Log.e("ERROR", e.getMessage(), e);
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.text, this.toRender);
        lv = (ListView) findViewById(R.id.listen_up);
        lv.setAdapter(adapter);
    }

    private void getListViewString(JSONArray jsonArray, List<String> ret) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            ret.add(obj.get("author").toString());
            ret.add(obj.get("title").toString());
            ret.add(obj.get("description").toString());
            ret.add(obj.get("url").toString());
            ret.add(obj.get("urlToImage").toString());
            ret.add(obj.get("publishedAt").toString());
        }
    }

}






