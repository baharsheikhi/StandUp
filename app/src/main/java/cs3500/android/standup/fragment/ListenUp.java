package cs3500.android.standup.fragment;

import android.content.Context;
import android.sax.TextElementListener;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cs3500.android.standup.Article;
import cs3500.android.standup.MyDownloadTask;
import cs3500.android.standup.adapter.SlideMenuAdapter;
import cs3500.android.standup.model.ItemListenUp;
import cs3500.android.standup.model.ItemSlideMenu;
import cs3500.android.standup.adapter.ListenUpAdapter;

import java.util.ArrayList;
import java.util.List;
import cs3500.android.standup.R;

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
import org.w3c.dom.Text;

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
import static java.security.AccessController.getContext;



/**
 * Created by feliciazhang on 2/11/2017.
 */


public class ListenUp extends Fragment {

    private ListView lv;
    private JSONObject jsonObject, jsonObject2;
    private JSONArray articles, articles2;
    private List<Article> toRender;
    private MyDownloadTask downloadTask, downloadTask2;
    private View rootView;
    ArrayAdapter<Article> adapter;
    public ListenUp(){

        this.toRender = new ArrayList<Article>();
        this.downloadTask = new MyDownloadTask("https://newsapi.org/v1/articles?source=techcrunch&apiKey=de48e3ded2c640b18c2e8136c0e314d1");
        this.downloadTask2 = new MyDownloadTask("https://newsapi.org/v1/articles?source=bbc-sport&apiKey=de48e3ded2c640b18c2e8136c0e314d1");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = (ListView) rootView.findViewById(R.id.news_list);

        downloadTask.execute();

        try {
            jsonObject = new JSONObject(downloadTask.getJSON());
            articles = (JSONArray) jsonObject.get("articles");
            getListViewString(articles, this.toRender);

        }
        catch (JSONException e){
            Log.e("ERROR", e.getMessage(), e);
        }

        adapter = new ArrayAdapter<Article>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, this.toRender) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d("yo", "in get view");
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(toRender.get(position).title);
                text2.setText(toRender.get(position).author);

                return view;
            }
        };

        lv = (ListView) rootView.findViewById(R.id.news_list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject obj = null;
                String my_URL = "";
                try {
                    my_URL = ((JSONObject) articles.get(position)).get("url").toString();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_URL));
                startActivity(browserIntent);
            }
        });
    }

/*
    @Override
    protected void onActivityStarted(Bundle savedInstanceState) {
        lv = (ListView) rootView.findViewById(R.id.news_list);

        downloadTask.execute();

        try {
            jsonObject = new JSONObject(downloadTask.getJSON());
            articles = (JSONArray) jsonObject.get("articles");
            getListViewString(articles, this.toRender);

        }
        catch (JSONException e){
            Log.e("ERROR", e.getMessage(), e);
        }

        adapter = new ArrayAdapter<Article>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, this.toRender) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Log.d("yo", "in get view");
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(toRender.get(position).title);
                text2.setText(toRender.get(position).author);

                return view;
            }
        };

        lv = (ListView) rootView.findViewById(R.id.news_list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                JSONObject obj = null;
                String my_URL = "";
                try {
                    my_URL = ((JSONObject) articles.get(position)).get("url").toString();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_URL));
                startActivity(browserIntent);
            }
        });
    }*/

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.listen_up, container, false);
        return rootView;
    }
}