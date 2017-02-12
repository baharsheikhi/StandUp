package cs3500.android.standup.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import cs3500.android.standup.adapter.SlideMenuAdapter;
import cs3500.android.standup.model.ItemListenUp;
import cs3500.android.standup.model.ItemSlideMenu;
import cs3500.android.standup.adapter.ListenUpAdapter;

import java.util.ArrayList;
import java.util.List;
import cs3500.android.standup.R;

/**
 * Created by feliciazhang on 2/11/2017.
 */



public class ListenUp extends Fragment{
    private ListView listViewListen;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.listen_up);

            ArrayList<ItemListenUp> searchArrayList1 = GetSearchArrayList1();

            final ListView lv1 = (ListView) findViewById(R.id.news_list);
            listViewListen.setAdapter(new ListenUpAdapter(this, searchArrayList1));

        }

        private ArrayList<SearchResults> GetSearchResults(){
            ArrayList<> results = new ArrayList<SearchResults>();
            return results;
        }
    }
}