package co.edu.cuc.gymapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Entrenador;

public class FormularioEntrenadorActivity extends AppCompatActivity {

    @BindView(R.id.crearClienteToolbar)
    Toolbar mToolbar;
    @BindView(R.id.btnAgregarCliente)
    Button mCrearEntrenador;
    @BindView(R.id.btnCancelarCliente)
    Button mCancelarButton;
    @BindView(R.id.btnLimpiarCliente)
    Button mLimpiarButton;
    @BindView(R.id.txtCedulaCliente)
    TextInputEditText mCedulaTextView;
    @BindView(R.id.txtNombreCliente)
    TextInputEditText mNombreTextView;
    @BindView(R.id.txtApellidoCliente)
    TextInputEditText mApellidoTextView;
    @BindView(R.id.txtCumpleañosCliente)
    TextInputEditText mFechaNacimientoTextView;
    @BindView(R.id.txtPesoCliente)
    TextInputEditText mPesoTextView;
    @BindView(R.id.txtEstaturaCliente)
    TextInputEditText mEstaturaTextView;

    private int year, month, day;
    static final int DATE_DIALOG_ID = 999;
    private Entrenador mEntrenador = new Entrenador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        ButterKnife.bind(this);



        if (getIntent().getExtras() != null) {

            int entrenadorID = (int) getIntent().getExtras().get("ENTRENADOR_ID");
            mEntrenador = OrmHelper.buscarEntrenadorPorId(this, entrenadorID);
            rellenarFormulario();

            mToolbar.setTitle(R.string.editar_entrenador);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCrearEntrenador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizar();
                }
            });
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FormularioEntrenadorActivity.this, DetalleEntrenadorActivity.class);
                    i.putExtra("ENTRENADOR_ID", mEntrenador.getId());
                    startActivity(i);
                    finish();
                }
            });

            mCancelarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FormularioEntrenadorActivity.this, DetalleEntrenadorActivity.class);
                    i.putExtra("ENTRENADOR_ID", mEntrenador.getId());
                    startActivity(i);
                    finish();
                }
            });

        } else {
            mToolbar.setTitle(R.string.crear_entrenador);

            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCrearEntrenador.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guardar();
                }
            });
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


        mFechaNacimientoTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("Touch", "Yes");
                showDialog(DATE_DIALOG_ID);
                return false;
            }
        });


    }

    private void actualizar() {
        String nombre, apellido, cumpleaños, peso, estatura;

        nombre = mNombreTextView.getText().toString().trim();
        apellido = mApellidoTextView.getText().toString().trim();
        cumpleaños = mFechaNacimientoTextView.getText().toString().trim();
        peso = mPesoTextView.getText().toString().trim();
        estatura = mEstaturaTextView.getText().toString().trim();

        mEntrenador.setNombre(nombre);
        mEntrenador.setApellido(apellido);
        mEntrenador.setPeso(Integer.parseInt(peso));
        mEntrenador.setAltura(Integer.parseInt(estatura));
        mEntrenador.setFechaNacimiento(cumpleaños);

        mEntrenador.editar(this);

        Toast.makeText(this, getString(R.string.entrenador_guardado), Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, DetalleEntrenadorActivity.class);
        i.putExtra("ENTRENADOR_ID", mEntrenador.getId());
        startActivity(i);
        finish();
    }

    private void rellenarFormulario() {
        mNombreTextView.setText(mEntrenador.getNombre());
        mApellidoTextView.setText(mEntrenador.getApellido());

        mCedulaTextView.setText(String.valueOf(mEntrenador.getIdentificacion()));
        mCedulaTextView.setEnabled(false);

        mFechaNacimientoTextView.setText(mEntrenador.getFechaNacimiento());
        mPesoTextView.setText(String.valueOf(mEntrenador.getPeso()));
        mEstaturaTextView.setText(String.valueOf(mEntrenador.getAltura()));
    }

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            mFechaNacimientoTextView.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

        }
    };

    public void guardar() {
        String nombre, apellido, cedula, cumpleaños, peso, estatura;

        nombre = mNombreTextView.getText().toString().trim();
        apellido = mApellidoTextView.getText().toString().trim();
        cedula = mCedulaTextView.getText().toString().trim();
        cumpleaños = mFechaNacimientoTextView.getText().toString().trim();
        peso = mPesoTextView.getText().toString().trim();
        estatura = mEstaturaTextView.getText().toString().trim();

        Entrenador c = new Entrenador(Integer.valueOf(cedula), nombre, apellido,
                Integer.valueOf(peso), Integer.valueOf(estatura), cumpleaños, 0);

        c.guardar(this);

        Toast.makeText(this, getString(R.string.entrenador_guardado), Toast.LENGTH_SHORT).show();

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListaEntrenadoresActivity.class));
        finish();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                return new DatePickerDialog(this, datePickerListener,
                        1990, month, day);
        }
        return null;
    }
}
