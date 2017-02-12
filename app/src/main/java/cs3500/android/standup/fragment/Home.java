package cs3500.android.standup.fragment;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cs3500.android.standup.R;

/**
 * Created by feliciazhang on 2/11/2017.
 */

public class Home extends Fragment{
    public Home(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        return rootView;
    }
}
