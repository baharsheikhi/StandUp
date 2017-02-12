package cs3500.android.standup;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by baharsheikhi on 2/11/17.
 */

public class MyDownloadTask extends AsyncTask<Void,Void,String> {
    private String json;
    private URL url;

    public MyDownloadTask(String u) {
        try {
            this.url = new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        json = "";
    }

    protected String doInBackground(Void... params) {
        try {
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

    /**
     *
     * @return a JSON object in string form if it has been downloaded
     */
    public String getJSON() {
        while (json == "") {
        }
        return json;
    }
}
