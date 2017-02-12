package cs3500.android.standup.fragment;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cs3500.android.standup.R;

/**
 * Created by feliciazhang on 2/11/2017.
 */



public class ShowUp extends Fragment{
    public ShowUp(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.show_up, container, false);
        return rootView;
    }
}