package cs3500.android.standup.fragment;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cs3500.android.standup.R;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by baharsheikhi on 2/11/17.
 */


/**
 * Created by feliciazhang on 2/11/2017.
 */



public class ShowUp extends Fragment{

        private JSONObject jsonObject1;
        private JSONObject jsonObject2;
        private TextView info;
        private LoginButton loginButton;
        private CallbackManager callbackManager;
        private AccessToken accessToken;
        private ListView lv;
        private List<String> ret;
        private ShowUp me;
        private View rootView;
        ArrayAdapter<String> adapter;


        public ShowUp() {
            this.ret = new ArrayList<String>();
            this.me = this;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext());
            callbackManager = CallbackManager.Factory.create();
            info = (TextView) rootView.findViewById(R.id.info);
            lv = (ListView) rootView.findViewById(R.id.event_list);
            loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
            LoginManager.getInstance().logInWithReadPermissions(
                    this,
                    Arrays.asList("email"));
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            loginButton.setVisibility(View.GONE);
                            accessToken = loginResult.getAccessToken();

               /* info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );*/

                        /* make the API call */
                    new GraphRequest(accessToken,
                            "385640398156882?fields=posts{message},events",
                            null,
                            HttpMethod.GET,
                            new GraphRequest.Callback() {
                                public void onCompleted(GraphResponse response) {
                        /* handle the result */

                                    //final List<String> ret = new ArrayList<String>();
                                   jsonObject1 = response.getJSONObject();
                                    try {
                                        JSONObject posts = (JSONObject) jsonObject1.get("posts");
                                        JSONArray dataPosts = (JSONArray) posts.get("data");
                                        for (int i = 0; i < dataPosts.length(); i++) {
                                            JSONObject obj = (JSONObject) dataPosts.get(i);
                                            try {
                                                ret.add(obj.get("message").toString());
                                            } catch (JSONException e) {
                                            }
                                        }
                                        JSONObject events = (JSONObject) jsonObject1.get("events");
                                        JSONArray dataEvents = (JSONArray) events.get("data");
                                        for (int i = 0; i < dataEvents.length(); i++) {
                                            JSONObject obj = (JSONObject) dataEvents.get(i);
                                            ret.add(obj.get("name").toString());
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                    ).executeAsync();

                    new GraphRequest(accessToken,
                            "162098460575843?fields=events,posts",
                            null,
                            HttpMethod.GET,
                            new GraphRequest.Callback() {
                                public void onCompleted(GraphResponse response) {
                        /* handle the result */
                                    jsonObject2 = response.getJSONObject();
                                    //Log.d("Second JSON", jsonObject2.toString());
                                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, ret) {
                                        @Override
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            Log.d("yo", "in get view");
                                            View view = super.getView(position, convertView, parent);
                                            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                                            text1.setText(adapter.getItem(position));
                                            return view;
                                        }
                                        public void doStuff() {
                                        }
                                    };

                                    lv.setAdapter(adapter);

                                }
                            }
                    ).executeAsync();

                }

                @Override
                public void onCancel() {
                    info.setText("Login attempt canceled.");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Login attempt failed.");
                }


            });
            //while() Log.d("yo","data not ready");


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // your code
                    JSONObject obj = null;
                    String my_URL = "";
                    my_URL = "http://www.facebook.com";//((JSONObject) ret.get(position)).get("url").toString();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(my_URL));
                    startActivity(browserIntent);
                }
            });

        }

    public JSONObject firstJSON() {
        return this.jsonObject1;
    }

    public JSONObject secondJSON() {
        return this.jsonObject2;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.show_up, container, false);
        return rootView;
    }
}