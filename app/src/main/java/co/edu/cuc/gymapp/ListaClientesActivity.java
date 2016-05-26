package co.edu.cuc.gymapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Cliente;
import co.edu.cuc.gymapp.view.ClienteAdaptador;

public class ListaClientesActivity extends AppCompatActivity implements ClienteAdaptador.OnClienteClickListener {

    @BindView(R.id.listaClientesToolbar)
    Toolbar mToolbar;
    @BindView(R.id.listaClientesRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.irAgregarClienteButton)
    FloatingActionButton mFloatingActionButton;

    private List<Cliente> mClientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        ButterKnife.bind(this);

        mClientes = OrmHelper.traerClientes(this);

        mToolbar.setTitle(R.string.clientes);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(new ClienteAdaptador(this, this, mClientes));

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaClientesActivity.this, FormularioClienteActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClienteItemClickListener(Integer position) {
        Intent i = new Intent(this, DetalleUsuarioActivity.class);
        i.putExtra("CLIENTE_ID", mClientes.get(position).getId());
        startActivity(i);
        finish();
    }
}