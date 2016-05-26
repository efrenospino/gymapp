package co.edu.cuc.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Horario;

public class CrearHorarioActivity extends AppCompatActivity {

    @BindView(R.id.crearHorarioToolbar)
    Toolbar mToolbar;
    @BindView(R.id.btnAgregarHorario)
    Button mCrearHorarioButton;
    @BindView(R.id.btnCancelarHorario)
    Button mCancelarButton;
    @BindView(R.id.opcionesHoraInicio)
    Spinner mHoraInicioSpinner;
    @BindView(R.id.opcionesHoraFin)
    Spinner mHoraFinSpinner;

    private Horario mHorario;

    private Integer[] HORAS_TEMPRANO = {7, 8, 9, 10, 11};
    private Integer[] HORAS_TARDE = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_horario);

        ButterKnife.bind(this);

        String[] hDia = new String[HORAS_TEMPRANO.length + HORAS_TARDE.length];
        for (int i = 0; i < HORAS_TEMPRANO.length; i++) {
            hDia[i] = HORAS_TEMPRANO[i] + " AM";
        }

        for (int i = 0; i < HORAS_TARDE.length; i++) {
            if (HORAS_TARDE[i] != 12) {
                hDia[HORAS_TEMPRANO.length + i] = (HORAS_TARDE[i] - 12) + " PM";
            } else {
                hDia[HORAS_TEMPRANO.length + i] = HORAS_TARDE[i] + " PM";
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, hDia);
        mHoraInicioSpinner.setAdapter(adapter);
        mHoraFinSpinner.setAdapter(adapter);

        if (getIntent().getExtras() != null) {

            int horarioID = (int) getIntent().getExtras().get("HORARIO_ID");
            mHorario = OrmHelper.buscarHorarioPorId(this, horarioID);
            rellenarFormulario();

            mToolbar.setTitle(R.string.editar_horario);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCrearHorarioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizar();
                }
            });

        } else {
            mToolbar.setTitle(R.string.crear_horario);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCrearHorarioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardar();
                }
            });
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mCancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void actualizar() {

        int posicionInicio = mHoraInicioSpinner.getSelectedItemPosition();
        int posicionFin = mHoraFinSpinner.getSelectedItemPosition();

        if (posicionInicio > HORAS_TEMPRANO.length-1) {
            mHorario.setHoraInicio(HORAS_TARDE[posicionInicio-HORAS_TEMPRANO.length]);
        } else {
            mHorario.setHoraInicio(HORAS_TEMPRANO[posicionInicio]);
        }

        if (posicionFin > HORAS_TEMPRANO.length-1) {
            mHorario.setHoraFin(HORAS_TARDE[posicionFin-HORAS_TEMPRANO.length]);
        } else {
            mHorario.setHoraFin(HORAS_TEMPRANO[posicionFin]);
        }

        mHorario.editar(this);

        Toast.makeText(this, getString(R.string.horario_guardado), Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    private void rellenarFormulario() {

        if (Arrays.asList(HORAS_TEMPRANO).contains(mHorario.getHoraInicio())) {
            mHoraInicioSpinner.setSelection(Arrays.asList(HORAS_TEMPRANO).indexOf(mHorario.getHoraInicio()));
        } else {
            mHoraInicioSpinner.setSelection(HORAS_TEMPRANO.length + Arrays.asList(HORAS_TARDE).indexOf(mHorario.getHoraInicio()));
        }

        if (Arrays.asList(HORAS_TEMPRANO).contains(mHorario.getHoraFin())) {
            mHoraFinSpinner.setSelection(Arrays.asList(HORAS_TEMPRANO).indexOf(mHorario.getHoraFin()));
        } else {
            mHoraFinSpinner.setSelection(HORAS_TEMPRANO.length + Arrays.asList(HORAS_TARDE).indexOf(mHorario.getHoraFin()));
        }
    }

    public void guardar() {
        int posicionInicio = mHoraInicioSpinner.getSelectedItemPosition();
        int posicionFin = mHoraFinSpinner.getSelectedItemPosition();

        Horario horario = new Horario();

        if (posicionInicio > HORAS_TEMPRANO.length-1) {
            horario.setHoraInicio(HORAS_TARDE[posicionInicio-HORAS_TEMPRANO.length]);
        } else {
            horario.setHoraInicio(HORAS_TEMPRANO[posicionInicio]);
        }

        if (posicionFin > HORAS_TEMPRANO.length-1) {
            horario.setHoraFin(HORAS_TARDE[posicionFin-HORAS_TEMPRANO.length]);
        } else {
            horario.setHoraFin(HORAS_TEMPRANO[posicionFin]);
        }
        horario.guardar(this);

        Toast.makeText(this, getString(R.string.horario_guardado), Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListaHorariosActivity.class));
        finish();
    }
}
