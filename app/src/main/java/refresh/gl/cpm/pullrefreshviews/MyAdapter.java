package refresh.gl.cpm.pullrefreshviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gl152 on 2017/8/23.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<String> list = new ArrayList<>();

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvData.setText(list.get(position));
        return convertView;
    }

    public void addData(String data) {
        list.add(data);
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tvData;

        public ViewHolder(View view) {
            tvData = (TextView) view.findViewById(R.id.tvData);
        }
    }
}
