package com.androidjson.firebasegooglelogin_androidjsoncom.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidjson.firebasegooglelogin_androidjsoncom.R;

/**
 * Created by F.Arian on 07.01.18.
 */

public class ListViewAdapter extends BaseAdapter {

    private Item items;
    private LayoutInflater inflater;

    public ListViewAdapter(Context context, Item item) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Adapter has to know your model list
        this.items = item;
    }

    public ListViewAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Adapter has to know your model list
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        //Return listview item count
        //It is the same with your model list size.
        return 0;
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            switch (convertView.getId()) {
                case R.layout.list_view_items:
                    convertView = inflater.inflate(R.layout.list_view_items, null, false);
                    break;
                case R.layout.report_layout:
                    convertView = inflater.inflate(R.layout.report_layout, null, false);
            }

            //Create a holder object and bind its views
            ViewHolder viewHolder = new ViewHolder();

            //Attach it to convertView
            convertView.setTag(viewHolder);
        }
        //Get holder object from convertView
        ViewHolder holder = (ViewHolder) convertView.getTag();

        //Adapter gives position parameter.
        //This parameter helps us to know which item view is wanted by adapter.
        holder.textViewInfo.setText(items.getInfo());
        holder.textViewValue.setText(items.getValue());
        return convertView;
    }

    private class ViewHolder {
        TextView textViewInfo;
        TextView textViewValue;
    }
}
