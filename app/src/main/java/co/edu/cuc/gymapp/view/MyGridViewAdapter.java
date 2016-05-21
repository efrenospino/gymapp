package co.edu.cuc.gymapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import co.edu.cuc.gymapp.R;

public class MyGridViewAdapter extends BaseAdapter {

    Context mContext;
    int[] mGridItemIcons = {R.drawable.gym_trainer, R.drawable.gym_clients, R.drawable.gym_equipment, R.drawable.schedule_image};
    int[] mGridItemTexts = {R.string.trainersText, R.string.clientsText, R.string.machinesText, R.string.schedulesText};

    private static LayoutInflater inflater = null;

    public MyGridViewAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return mGridItemIcons.length;
    }

    @Override
    public Object getItem(int position) {
        return mGridItemTexts[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();

        convertView = inflater.inflate(R.layout.grid_item_layout, null);
        holder.mImageView = (ImageView) convertView.findViewById(R.id.gridItemIcon);
        holder.mTextView= (TextView) convertView.findViewById(R.id.gridItemText);
        convertView.setTag(holder);

        Picasso.with(mContext).load(mGridItemIcons[position]).fit().centerInside().into(holder.mImageView);
        holder.mTextView.setText(mGridItemTexts[position]);

        return convertView;
    }

    private static class Holder {
        ImageView mImageView;
        TextView mTextView;
    }
}
