package cs3500.android.standup;

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
import cs3500.android.standup.fragment.Home;
import cs3500.android.standup.fragment.ListenUp;
import cs3500.android.standup.fragment.Settings;
import cs3500.android.standup.fragment.ShowUp;
import cs3500.android.standup.fragment.SpeakUp;
import cs3500.android.standup.model.ItemSlideMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private List<ItemSlideMenu> listSliding;
    private SlideMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private RelativeLayout mainContent;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //inherited behvaior
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initial component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainContent = (RelativeLayout) findViewById(R.id.main);
        listSliding = new ArrayList<>();

        //add item for sliding list
        listSliding.add(new ItemSlideMenu(0, "Stand Up"));
        listSliding.add(new ItemSlideMenu(0, "Listen Up"));
        listSliding.add(new ItemSlideMenu(0, "Speak Up"));
        listSliding.add(new ItemSlideMenu(0, "Show Up"));
        listSliding.add(new ItemSlideMenu(0, "Settings"));
        adapter = new SlideMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //icon to open/close menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set title
        setTitle(listSliding.get(0).getTitle());

        //item selected
        listViewSliding.setItemChecked(0, true);

        //close
        drawerLayout.closeDrawer(listViewSliding);

        //open at start
        replaceFragment(0);

        //item click
        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setTitle(listSliding.get(position).getTitle());
                //item selected
                listViewSliding.setItemChecked(position, true);

                //replace fragment
                replaceFragment(position);

                //close drawer
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    //side drop down menu
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate (Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //replace fragment

    private void replaceFragment(int pos){

        Fragment fragment = null;
        switch (pos){
            case 0:
                fragment = new Home();
                break;
            case 1:
                fragment = new ListenUp();
                break;
            case 2:
                fragment = new SpeakUp();
                break;
            case 3:
                fragment = new ShowUp();
                break;
            case 4:
                fragment = new Settings();
                break;
            default:
                fragment = new Home();
                break;
        }

        if (null != fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main, fragment);
            transaction.commit();
        }
    }
}






