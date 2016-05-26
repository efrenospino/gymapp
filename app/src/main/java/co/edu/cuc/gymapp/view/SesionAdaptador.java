package co.edu.cuc.gymapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.cuc.gymapp.R;
import co.edu.cuc.gymapp.model.Sesion;

public class SesionAdaptador extends RecyclerView.Adapter<SesionAdaptador.HomeItemViewHolder> {

    private List<Sesion> mSesiones = new ArrayList<>();
    private OnSesionClickListener mOnSesionClickListener;
    private Context mContext;

    public SesionAdaptador(OnSesionClickListener onSesionClickListener, Context context, List<Sesion> sesiones) {
        mOnSesionClickListener = onSesionClickListener;
        mContext = context;
        mSesiones = sesiones;
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_custom_sesion, parent, false);
        return new HomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeItemViewHolder holder, final int position) {
        holder.mHoraInicioTextView.setText(String.valueOf(mSesiones.get(position).getHoraInicio()));
        holder.mHoraFinTextView.setText(String.valueOf(mSesiones.get(position).getHoraFin()));
        holder.mEliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSesionClickListener.onEliminarSesionItemClickListener(position);
            }
        });
        holder.mEditarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnSesionClickListener.onEditarSesionItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSesiones.size();
    }

    public static class HomeItemViewHolder extends RecyclerView.ViewHolder {

        TextView mHoraInicioTextView;
        TextView mHoraFinTextView;
        Button mEliminarButton;
        Button mEditarButton;

        public HomeItemViewHolder(View itemView) {
            super(itemView);
            mHoraInicioTextView = (TextView) itemView.findViewById(R.id.horaInicioTextView);
            mHoraFinTextView = (TextView) itemView.findViewById(R.id.horaFinTextView);
            mEliminarButton = (Button) itemView.findViewById(R.id.eliminarHorarioButton);
            mEditarButton = (Button) itemView.findViewById(R.id.editarHorarioButton);
        }
    }

    public void removeItemAt(int position) {
        mSesiones.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mSesiones.size());
    }

    public interface OnSesionClickListener {
        void onEliminarSesionItemClickListener(Integer position);
        void onEditarSesionItemClickListener(Integer position);
    }

}
