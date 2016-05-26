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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Sesion;
import co.edu.cuc.gymapp.view.SesionAdaptador;

public class ListaSesionesActivity extends AppCompatActivity implements SesionAdaptador.OnSesionClickListener {

    @BindView(R.id.irAgregarHorarioButton)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.listaHorariosToolbar)
    Toolbar mToolbar;
    @BindView(R.id.listaHorariosRecyclerView)
    RecyclerView mRecyclerView;

    private List<Sesion> mSesiones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horarios);

        ButterKnife.bind(this);

        mSesiones = OrmHelper.traerSesiones(this);

        mToolbar.setTitle(R.string.sesiones);
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
        mRecyclerView.setAdapter(new SesionAdaptador(this, this, mSesiones));

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListaSesionesActivity.this, FormularioSesionActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onEliminarSesionItemClickListener(final Integer position) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getString(R.string.confirmacion));
        b.setMessage(getString(R.string.seguro_de_eliminar));
        b.setPositiveButton(this.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mSesiones.get(position).eliminar(ListaSesionesActivity.this);
                mSesiones.remove(position);
                ((SesionAdaptador) mRecyclerView.getAdapter()).removeItemAt(position);
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
    public void onEditarSesionItemClickListener(Integer position) {
        Intent i = new Intent(this, FormularioSesionActivity.class);
        i.putExtra("HORARIO_ID", mSesiones.get(position).getId());
        startActivity(i);
        finish();
    }
}
