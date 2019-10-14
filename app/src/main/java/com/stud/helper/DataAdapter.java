package com.stud.helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stud.R;
import com.stud.dto.DataDto;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends ArrayAdapter {

    private List<DataDto> items = new ArrayList<>();
    private Context context;

    public DataAdapter(Context context, List<DataDto> models) {
        super(context, -1);
        this.context = context;
        this.items = models;
    }

    public void setItems(List<DataDto> models) {
        this.items = models;
        notifyDataSetChanged();
    }

    public List<DataDto> getItems() {
        return items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public DataDto getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.data_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        final DataDto item = getItem(position);
        holder.tvTitle.setText(item.key);
        holder.tvData.setText(item.data);

        return convertView;
    }

    public class ViewHolder {
        public TextView tvTitle;
        public TextView tvData;

        public ViewHolder(View view) {
            tvTitle = view.findViewById(R.id.tvTitle);
            tvData = view.findViewById(R.id.tvData);
        }
    }
}
