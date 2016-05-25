package co.edu.cuc.gymapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.cuc.gymapp.R;

public class PrincipalAdaptador extends RecyclerView.Adapter<PrincipalAdaptador.HomeItemViewHolder> {

    private List<Integer> mHomeItemTexts = new ArrayList<>();
    private List<Integer> mHomeItemImages = new ArrayList<>();
    private OnHomeListItemClickListener mOnHomeListItemClickListener;
    private Context mContext;

    public PrincipalAdaptador(OnHomeListItemClickListener onHomeListItemClickListener, Context context) {
        mOnHomeListItemClickListener = onHomeListItemClickListener;
        mContext = context;
        mHomeItemTexts.addAll(Arrays.asList(R.string.entrenadores, R.string.clientes, R.string.schedulesText, R.string.machinesText));
        mHomeItemImages.addAll(Arrays.asList(R.drawable.gym_trainer, R.drawable.gym_clients, R.drawable.schedule_image, R.drawable.gym_equipment));
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_layout, parent, false);
        return new HomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeItemViewHolder holder, final int position) {
        holder.mTextView.setText(mHomeItemTexts.get(position));
        Picasso.with(mContext)
                .load(mHomeItemImages.get(position)).fit().centerCrop()
                .into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnHomeListItemClickListener.OnHomeListItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHomeItemTexts.size();
    }

    public static class HomeItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        public HomeItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.homeItemImage);
            mTextView = (TextView) itemView.findViewById(R.id.homeItemText);
        }
    }

    public interface OnHomeListItemClickListener {
        void OnHomeListItemClickListener(Integer position);
    }

}
