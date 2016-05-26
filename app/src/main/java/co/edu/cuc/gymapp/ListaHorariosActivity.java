package co.edu.cuc.gymapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.model.Horario;
import co.edu.cuc.gymapp.view.EntrenadorAdaptador;
import co.edu.cuc.gymapp.view.HorarioAdaptador;

public class ListaHorariosActivity extends AppCompatActivity implements HorarioAdaptador.OnHorarioClickListener {

    @BindView(R.id.irAgregarHorarioButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.listaHorariosToolbar)
    Toolbar mToolbar;
    @BindView(R.id.listaHorariosRecyclerView)
    RecyclerView mRecyclerView;

    private List<Horario> mHorarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        ButterKnife.bind(this);

        mHorarios = OrmHelper.traerHorarios(this);

        mToolbar.setTitle(R.string.horarios);
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
        mRecyclerView.setAdapter(new HorarioAdaptador(this, this, mHorarios));

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaHorariosActivity.this, CrearHorarioActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onEliminarHorarioItemClickListener(final Integer position) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getString(R.string.confirmacion));
        b.setMessage(getString(R.string.seguro_de_eliminar));
        b.setPositiveButton(this.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mHorarios.get(position).eliminar(ListaHorariosActivity.this);
                mHorarios.remove(position);
                ((HorarioAdaptador) mRecyclerView.getAdapter()).removeItemAt(position);
            }
        });
        b.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog a = b.create();
        a.show();
    }

    @Override
    public void onEditarHorarioItemClickListener(Integer position) {
        Intent i = new Intent(this, CrearHorarioActivity.class);
        i.putExtra("HORARIO_ID", mHorarios.get(position).getId());
        startActivity(i);
        finish();
    }
}
