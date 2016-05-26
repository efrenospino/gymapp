package co.edu.cuc.gymapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Cliente;

public class FormularioClienteActivity extends AppCompatActivity {

    @BindView(R.id.crearClienteToolbar)
    Toolbar mToolbar;
    @BindView(R.id.btnAgregarCliente)
    Button mCreatClienteButton;
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
    @BindView(R.id.radioGroupGenero)
    RadioGroup mRadioGroup;

    @BindView(R.id.identificacionLayout)
    TextInputLayout mIdentificacionLayout;
    @BindView(R.id.nombreLayout)
    TextInputLayout mNombreLayout;
    @BindView(R.id.apellidoLayout)
    TextInputLayout mApellidoLayout;
    @BindView(R.id.fechaLayout)
    TextInputLayout mFechaLayout;
    @BindView(R.id.pesoLayout)
    TextInputLayout mPesoLayout;
    @BindView(R.id.estaturaLayout)
    TextInputLayout mEstaturaLayout;

    private int year, month, day;
    static final int DATE_DIALOG_ID = 999;
    private Cliente mCliente = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {

            int clienteID = (int) getIntent().getExtras().get("CLIENTE_ID");
            mCliente = OrmHelper.buscarClientePorId(this, clienteID);
            rellenarFormulario();

            mToolbar.setTitle(R.string.crear_cliente);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            mCreatClienteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actualizar();
                }
            });
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FormularioClienteActivity.this, DetalleUsuarioActivity.class);
                    i.putExtra("CLIENTE_ID", mCliente.getId());
                    startActivity(i);
                    finish();
                }
            });

            mCancelarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(FormularioClienteActivity.this, DetalleUsuarioActivity.class);
                    i.putExtra("CLIENTE_ID", mCliente.getId());
                    startActivity(i);
                    finish();
                }
            });

        } else {
            mToolbar.setTitle(R.string.editar_cliente);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mCreatClienteButton.setOnClickListener(new View.OnClickListener() {
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

            ((RadioButton) findViewById(R.id.radioMasculino)).setChecked(true);
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
        String nombre, apellido, cumpleaños, peso, estatura, cedula;

        nombre = mNombreTextView.getText().toString().trim();
        apellido = mApellidoTextView.getText().toString().trim();
        cumpleaños = mFechaNacimientoTextView.getText().toString().trim();
        peso = mPesoTextView.getText().toString().trim();
        estatura = mEstaturaTextView.getText().toString().trim();
        cedula = mCedulaTextView.getText().toString().trim();

        if (validar(cedula, nombre, apellido, cumpleaños, peso, estatura)) {

            int genero = mRadioGroup.getCheckedRadioButtonId();

            int generoid = 0;
            switch (genero) {
                case R.id.radioMasculino:
                    generoid = 1;
                    break;
                case R.id.radioFemenino:
                    generoid = 0;
                    break;
            }

            mCliente.setNombre(nombre);
            mCliente.setApellido(apellido);
            mCliente.setPeso(Integer.parseInt(peso));
            mCliente.setEstatura(Integer.parseInt(estatura));
            mCliente.setFechaNacimiento(cumpleaños);
            mCliente.setGenero(generoid);

            mCliente.editar(this);

            Toast.makeText(this, getString(R.string.cliente_guardado), Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, DetalleUsuarioActivity.class);
            i.putExtra("CLIENTE_ID", mCliente.getId());
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, getString(R.string.llene_campos), Toast.LENGTH_SHORT).show();
        }
    }

    private void rellenarFormulario() {
        mNombreTextView.setText(mCliente.getNombre());
        mApellidoTextView.setText(mCliente.getApellido());

        mCedulaTextView.setText(String.valueOf(mCliente.getIdentificacion()));
        mCedulaTextView.setEnabled(false);

        mFechaNacimientoTextView.setText(mCliente.getFechaNacimiento());
        mPesoTextView.setText(String.valueOf(mCliente.getPeso()));
        mEstaturaTextView.setText(String.valueOf(mCliente.getEstatura()));

        switch (mCliente.getGenero()) {
            case 1:
                ((RadioButton) findViewById(R.id.radioMasculino)).setChecked(true);
                break;
            case 0:
                ((RadioButton) findViewById(R.id.radioFemenino)).setChecked(true);
                break;
        }
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
        int genero;

        nombre = mNombreTextView.getText().toString().trim();
        apellido = mApellidoTextView.getText().toString().trim();
        cedula = mCedulaTextView.getText().toString().trim();
        cumpleaños = mFechaNacimientoTextView.getText().toString().trim();
        peso = mPesoTextView.getText().toString().trim();
        estatura = mEstaturaTextView.getText().toString().trim();
        genero = mRadioGroup.getCheckedRadioButtonId();

        int generoid = 0;
        switch (genero) {
            case R.id.radioMasculino:
                generoid = 1;
                break;
            case R.id.radioFemenino:
                generoid = 0;
                break;
        }

        if (validar(cedula, nombre, apellido, cumpleaños, peso, estatura)) {
            Cliente c = new Cliente(cedula, nombre, apellido,
                    Integer.parseInt(peso), Integer.parseInt(estatura), cumpleaños, 0, generoid);

            c.guardar(this);

            Toast.makeText(this, getString(R.string.cliente_guardado), Toast.LENGTH_SHORT).show();

            onBackPressed();
        } else {
            Toast.makeText(this, getString(R.string.llene_campos), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListaClientesActivity.class));
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

    public boolean validar(String identificacion, String nombre, String apellido, String fecha, String peso, String estatura) {
        return !(TextUtils.isEmpty(nombre.trim()) | TextUtils.isEmpty(identificacion.trim())
                | TextUtils.isEmpty(apellido.trim()) | TextUtils.isEmpty(fecha.trim()) | TextUtils.isEmpty(peso.trim())
                | TextUtils.isEmpty(estatura.trim()));
    }
}
