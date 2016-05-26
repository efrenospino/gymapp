package co.edu.cuc.gymapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.cuc.gymapp.R;
import co.edu.cuc.gymapp.model.Entrenador;

public class EntrenadorAdaptador extends RecyclerView.Adapter<EntrenadorAdaptador.HomeItemViewHolder> {

    private List<Entrenador> mEntrenadores = new ArrayList<>();
    private OnEntrenadorClickListener mOnEntrenadorItemClickListener;
    private Context mContext;

    public EntrenadorAdaptador(OnEntrenadorClickListener onEntrenadorItemClickListener, Context context, List<Entrenador> entrenadores) {
        mOnEntrenadorItemClickListener = onEntrenadorItemClickListener;
        mContext = context;
        mEntrenadores = entrenadores;
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_custom, parent, false);
        return new HomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeItemViewHolder holder, final int position) {
        holder.mLargeTextView.setText(mEntrenadores.get(position).getNombre() + " " + mEntrenadores.get(position).getApellido());
        holder.mMediumTextView.setText(String.valueOf(mEntrenadores.get(position).getIdentificacion()));
        holder.mSmallTextView.setText(mEntrenadores.get(position).getAltura() + "cm - " + mEntrenadores.get(position).getPeso() + "Kg");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnEntrenadorItemClickListener.OnEntrenadorItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEntrenadores.size();
    }

    public static class HomeItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mLargeTextView;
        TextView mMediumTextView;
        TextView mSmallTextView;

        public HomeItemViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageViewCard);
            mLargeTextView = (TextView) itemView.findViewById(R.id.largeTextCard);
            mMediumTextView = (TextView) itemView.findViewById(R.id.mediumTextCard);
            mSmallTextView = (TextView) itemView.findViewById(R.id.smallTextCard);
        }
    }

    public interface OnEntrenadorClickListener {
        void OnEntrenadorItemClickListener(Integer position);
    }

}
