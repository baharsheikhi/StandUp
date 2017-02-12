package cs3500.android.standup.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cs3500.android.standup.model.ItemSlideMenu;
import cs3500.android.standup.R;

/**
 * Created by feliciazhang on 2/11/2017.
 */

public class SlideMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> lstItem;

    public SlideMenuAdapter(Context context, List<ItemSlideMenu> lstItem) {
        this.context = context;
        this.lstItem = lstItem;
    }

    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = View.inflate(context, R.layout.item_sliding_layout, null);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.item_image);
        TextView tv = (TextView)convertView.findViewById(R.id.item_title);
        tv.setTextColor(Color.parseColor("#FFF3EE"));
        tv.setTextSize(30);
        tv.setPadding(30,50,10,50);

        ItemSlideMenu item = lstItem.get(position);
        image.setImageResource(item.getImage());
        tv.setText(item.getTitle());


        return convertView;
    }
}
