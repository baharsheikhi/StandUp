package cs3500.android.standup;

import android.content.Intent;
import android.net.Uri;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.R.id.list;
import static android.R.id.text1;
import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private JSONObject jsonObject;
    private JSONArray articles;
    private List<Article> toRender;
    private MyDownloadTask downloadTask;

    public MainActivity() {
        this.toRender = new ArrayList<Article>();
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
        ArrayAdapter<Article> adapter = new ArrayAdapter<Article>(this, android.R.layout.simple_list_item_2, android.R.id.text1, this.toRender) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d("yo","in get view");
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(toRender.get(position).title);
                text2.setText(toRender.get(position).author);
                return view;
            }
        };
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, this.toRender);
        lv = (ListView) findViewById(R.id.listen_up);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // your code
                JSONObject obj = null;
                String my_URL = "";
                try {
                    my_URL = ((JSONObject) articles.get(position)).get("url").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_URL));
                startActivity(browserIntent);
            }
        });

    }

    private void getListViewString(JSONArray jsonArray, List<Article> ret) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            //ret.add(obj.get("author").toString());
            ret.add(new Article(obj.get("title").toString(), obj.get("author").toString()));
            //ret.add(obj.get("description").toString());
            //ret.add(obj.get("url").toString());
            //ret.add(obj.get("urlToImage").toString());
            //ret.add(obj.get("publishedAt").toString());*/
        }
    }
}






