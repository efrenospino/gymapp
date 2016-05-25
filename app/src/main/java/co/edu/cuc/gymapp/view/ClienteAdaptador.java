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
import java.util.List;

import co.edu.cuc.gymapp.R;
import co.edu.cuc.gymapp.model.Cliente;

public class ClienteAdaptador extends RecyclerView.Adapter<ClienteAdaptador.ClienteViewHolder> {

    private List<Cliente> mClientes = new ArrayList<>();
    private OnClienteClickListener mOnClienteItemClickListener;
    private Context mContext;

    public ClienteAdaptador(OnClienteClickListener onClienteItemClickListener, Context context, List<Cliente> clientes) {
        mOnClienteItemClickListener = onClienteItemClickListener;
        mContext = context;
        mClientes = clientes;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_custom, parent, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ClienteViewHolder holder, final int position) {
        Picasso.with(mContext).load(R.drawable.default_user).fit().centerInside().into(holder.mImageView);
        holder.mLargeTextView.setText(mClientes.get(position).getNombre() + " " + mClientes.get(position).getApellido());
        holder.mMediumTextView.setText(String.valueOf(mClientes.get(position).getIdentificacion()));
        holder.mSmallTextView.setText(mClientes.get(position).getEstatura() + "cm - " + mClientes.get(position).getPeso() + "Kg");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnClienteItemClickListener.onClienteItemClickListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClientes.size();
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mLargeTextView;
        TextView mMediumTextView;
        TextView mSmallTextView;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageViewCard);
            mLargeTextView = (TextView) itemView.findViewById(R.id.largeTextCard);
            mMediumTextView = (TextView) itemView.findViewById(R.id.mediumTextCard);
            mSmallTextView = (TextView) itemView.findViewById(R.id.smallTextCard);
        }
    }

    public interface OnClienteClickListener {
        void onClienteItemClickListener(Integer position);
    }

}
