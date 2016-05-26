package co.edu.cuc.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Cliente;
import co.edu.cuc.gymapp.model.Entrenador;
import co.edu.cuc.gymapp.model.Sesion;

public class FormularioSesionActivity extends AppCompatActivity {

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
    @BindView(R.id.opcionesCliente)
    Spinner mClienteSpinner;
    @BindView(R.id.opcionesEntrenador)
    Spinner mEntrenadorSpinner;
    @BindView(R.id.completadaLayout)
    LinearLayout mCompletadaLayout;
    @BindView(R.id.completadaCheckBox)
    CheckBox mCheckBox;

    private Sesion mSesion;

    private List<Cliente> mClienteList = new ArrayList<>();
    private List<Entrenador> mEntrenadorList= new ArrayList<>();

    private Integer[] HORAS_TEMPRANO = {7, 8, 9, 10, 11};
    private Integer[] HORAS_TARDE = {12, 13, 14, 15, 16, 17, 18, 19, 20, 21};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_horario);

        ButterKnife.bind(this);

        mClienteList = OrmHelper.traerClientes(this);
        mEntrenadorList = OrmHelper.traerEntrenadores(this);

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

        ArrayAdapter<Cliente> clienteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mClienteList);
        mClienteSpinner.setAdapter(clienteArrayAdapter);

        ArrayAdapter<Entrenador> entrenadorArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mEntrenadorList);
        mEntrenadorSpinner.setAdapter(entrenadorArrayAdapter);

        if (getIntent().getExtras() != null) {

            mCompletadaLayout.setVisibility(View.VISIBLE);

            int horarioID = (int) getIntent().getExtras().get("HORARIO_ID");
            mSesion = OrmHelper.buscarHorarioPorId(this, horarioID);
            rellenarFormulario();

            mToolbar.setTitle(R.string.editar_sesion);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCrearHorarioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizar();
                }
            });

        } else {
            mToolbar.setTitle(R.string.crear_sesion);
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
            mSesion.setHoraInicio(HORAS_TARDE[posicionInicio-HORAS_TEMPRANO.length]);
        } else {
            mSesion.setHoraInicio(HORAS_TEMPRANO[posicionInicio]);
        }

        if (posicionFin > HORAS_TEMPRANO.length-1) {
            mSesion.setHoraFin(HORAS_TARDE[posicionFin-HORAS_TEMPRANO.length]);
        } else {
            mSesion.setHoraFin(HORAS_TEMPRANO[posicionFin]);
        }
        mSesion.setCompletada((mCheckBox.isChecked() ? 1 : 0));
        mSesion.editar(this);

        Toast.makeText(this, getString(R.string.sesion_guardado), Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    private void rellenarFormulario() {

        if (Arrays.asList(HORAS_TEMPRANO).contains(mSesion.getHoraInicio())) {
            mHoraInicioSpinner.setSelection(Arrays.asList(HORAS_TEMPRANO).indexOf(mSesion.getHoraInicio()));
        } else {
            mHoraInicioSpinner.setSelection(HORAS_TEMPRANO.length + Arrays.asList(HORAS_TARDE).indexOf(mSesion.getHoraInicio()));
        }

        if (Arrays.asList(HORAS_TEMPRANO).contains(mSesion.getHoraFin())) {
            mHoraFinSpinner.setSelection(Arrays.asList(HORAS_TEMPRANO).indexOf(mSesion.getHoraFin()));
        } else {
            mHoraFinSpinner.setSelection(HORAS_TEMPRANO.length + Arrays.asList(HORAS_TARDE).indexOf(mSesion.getHoraFin()));
        }
        if (mSesion.isCompletada() == 1) {
            mCheckBox.setChecked(true);
        }

    }

    public void guardar() {
        int posicionInicio = mHoraInicioSpinner.getSelectedItemPosition();
        int posicionFin = mHoraFinSpinner.getSelectedItemPosition();

        Sesion sesion = new Sesion();

        if (posicionInicio > HORAS_TEMPRANO.length-1) {
            sesion.setHoraInicio(HORAS_TARDE[posicionInicio-HORAS_TEMPRANO.length]);
        } else {
            sesion.setHoraInicio(HORAS_TEMPRANO[posicionInicio]);
        }

        if (posicionFin > HORAS_TEMPRANO.length-1) {
            sesion.setHoraFin(HORAS_TARDE[posicionFin-HORAS_TEMPRANO.length]);
        } else {
            sesion.setHoraFin(HORAS_TEMPRANO[posicionFin]);
        }

        sesion.setCliente((Cliente) mClienteSpinner.getSelectedItem());
        sesion.setEntrenador((Entrenador) mEntrenadorSpinner.getSelectedItem());
        sesion.guardar(this);

        Toast.makeText(this, getString(R.string.sesion_guardado), Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListaSesionesActivity.class));
        finish();
    }
}
