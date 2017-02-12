package cs3500.android.standup.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;

import cs3500.android.standup.model.ItemListenUp;
import cs3500.android.standup.R;

/**
 * Created by felicia zhang  on 2/12/2017.
 */

public class ListenUpAdapter extends BaseAdapter {
    private static ArrayList<ItemListenUp> searchArrayList1;

    private LayoutInflater mInflater;

    public ListenUpAdapter(Context context, ArrayList<ItemListenUp> results) {
        searchArrayList1 = results;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return searchArrayList1.size();
    }

    public Object getItem(int position) {
        return searchArrayList1.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_source_row, null);
            holder = new ViewHolder();
       //     holder.imgFeaturePhoto = (ImageView) convertView.findViewById(R.id.feature_photo);
            holder.txtHeadline= (TextView) convertView.findViewById(R.id.headline);
            holder.txtSourcePreview = (TextView) convertView.findViewById(R.id.source_preview);
            holder.txtSourceDate = (TextView) convertView.findViewById(R.id.source_date);
            holder.txtSource = (TextView) convertView.findViewById(R.id.source);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtHeadline.setText(searchArrayList1.get(position).getHeadline());
        holder.txtSourcePreview.setText(searchArrayList1.get(position).getSourcePreview());
        holder.txtSourceDate.setText(searchArrayList1.get(position).getSourceDate());
        holder.txtSource.setText(searchArrayList1.get(position).getSource());
  //      holder.imgFeaturePhoto.setImageResource(searchArrayList1.get(position).getFeaturePhoto());

        return convertView;
    }

    static class ViewHolder {
        ImageView imgFeaturePhoto;
        TextView txtHeadline;
        TextView txtSourcePreview;
        TextView txtSourceDate;
        TextView txtSource;
    }
}