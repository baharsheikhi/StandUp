package cs3500.android.standup.fragment;

import android.content.Context;
import android.sax.TextElementListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
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

import cs3500.android.standup.adapter.SlideMenuAdapter;
import cs3500.android.standup.model.ItemListenUp;
import cs3500.android.standup.model.ItemSlideMenu;
import cs3500.android.standup.adapter.ListenUpAdapter;

import java.util.ArrayList;
import java.util.List;
import cs3500.android.standup.R;

import static java.security.AccessController.getContext;

/**
 * Created by feliciazhang on 2/11/2017.
 */


public class ListenUp extends Fragment {
    public ListenUp() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listen_up, container, false);
        return rootView;
    }
}