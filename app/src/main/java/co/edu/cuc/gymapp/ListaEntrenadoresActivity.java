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
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.view.EntrenadorAdaptador;


public class ListaEntrenadoresActivity extends AppCompatActivity implements EntrenadorAdaptador.OnEntrenadorClickListener {

    @BindView(R.id.irAgregarEntrenadorButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.listaEntrenadoresToolbar)
    Toolbar mToolbar;
    @BindView(R.id.listaEntrenadoresRecyclerView)
    RecyclerView mRecyclerView;
    private List<Entrenador> mEntrenadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_entrenadores);

        ButterKnife.bind(this);

        mEntrenadores = OrmHelper.traerEntrenadores(this);

        mToolbar.setTitle(R.string.entrenadores);
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
        mRecyclerView.setAdapter(new EntrenadorAdaptador(this, this, mEntrenadores));

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaEntrenadoresActivity.this, FormularioEntrenadorActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void OnEntrenadorItemClickListener(Integer position) {
        Intent i = new Intent(this, DetalleEntrenadorActivity.class);
        i.putExtra("ENTRENADOR_ID", mEntrenadores.get(position).getId());
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
