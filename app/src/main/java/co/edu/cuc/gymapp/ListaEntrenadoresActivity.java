package co.edu.cuc.gymapp;

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
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.view.EntrenadorAdaptador;


public class ListaEntrenadoresActivity extends AppCompatActivity implements EntrenadorAdaptador.OnEntrenadorClickListener {

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

        for (int i = 0; i < 10; i++) {
            mEntrenadores.add(new Entrenador(i, "First", "Last", i, i, new Date()));
        }

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

    }

    @Override
    public void OnEntrenadorItemClickListener(Integer position) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
