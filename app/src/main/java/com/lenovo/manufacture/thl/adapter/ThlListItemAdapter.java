package com.lenovo.manufacture.thl.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.data.List_gouzao;

public class ThlListItemAdapter extends BaseAdapter {

    private List<List_gouzao> objects = new ArrayList<List_gouzao>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ThlListItemAdapter(Context context,List<List_gouzao> objects) {
        this.objects=objects;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public List_gouzao getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.thl_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ImageView imageView=convertView.findViewById(R.id.list_image);
        TextView textView=convertView.findViewById(R.id.list_txt1);
        TextView textView1=convertView.findViewById(R.id.list_txt2);
        imageView.setImageResource(objects.get(position).getImage());
        textView.setText(objects.get(position).getTxt1());
        textView1.setText(objects.get(position).getTxt2());
        initializeViews((List_gouzao)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(List_gouzao object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ImageView listImage;
    private TextView listTxt1;
    private TextView listTxt2;

        public ViewHolder(View view) {
            listImage = (ImageView) view.findViewById(R.id.list_image);
            listTxt1 = (TextView) view.findViewById(R.id.list_txt1);
            listTxt2 = (TextView) view.findViewById(R.id.list_txt2);
        }
    }
}
