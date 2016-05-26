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
import co.edu.cuc.gymapp.model.Horario;

public class HorarioAdaptador extends RecyclerView.Adapter<HorarioAdaptador.HomeItemViewHolder> {

    private List<Horario> mHorarios = new ArrayList<>();
    private OnHorarioClickListener mOnHorarioClickListener;
    private Context mContext;

    public HorarioAdaptador(OnHorarioClickListener onHorarioClickListener, Context context, List<Horario> horarios) {
        mOnHorarioClickListener = onHorarioClickListener;
        mContext = context;
        mHorarios = horarios;
    }

    @Override
    public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_custom_horario, parent, false);
        return new HomeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeItemViewHolder holder, final int position) {
        holder.mHoraInicioTextView.setText(String.valueOf(mHorarios.get(position).getHoraInicio()));
        holder.mHoraFinTextView.setText(String.valueOf(mHorarios.get(position).getHoraFin()));
        holder.mEliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnHorarioClickListener.onEliminarHorarioItemClickListener(position);
            }
        });
        holder.mEditarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnHorarioClickListener.onEditarHorarioItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHorarios.size();
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
        mHorarios.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mHorarios.size());
    }

    public interface OnHorarioClickListener {
        void onEliminarHorarioItemClickListener(Integer position);
        void onEditarHorarioItemClickListener(Integer position);
    }

}
