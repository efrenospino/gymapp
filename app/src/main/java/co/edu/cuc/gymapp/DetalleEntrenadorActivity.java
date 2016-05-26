package co.edu.cuc.gymapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.db.OrmHelper;
import co.edu.cuc.gymapp.model.Cliente;
import co.edu.cuc.gymapp.model.Entrenador;

public class DetalleEntrenadorActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.verClienteToolbar)
    Toolbar mToolbar;
    @BindView(R.id.imagenDefault)
    ImageView mImageView;
    @BindView(R.id.verNombreCliente)
    TextView mNomnbre;
    @BindView(R.id.verApellidoCliente)
    TextView mApellido;
    @BindView(R.id.verIdentificacionCliente)
    TextView mIdentificacion;
    @BindView(R.id.verEstaturaCliente)
    TextView mEstatura;
    @BindView(R.id.verPesoCliente)
    TextView mPeso;
    @BindView(R.id.verNacimientoCliente)
    TextView mNacimiento;
    @BindView(R.id.editarButton)
    Button mEditarButton;
    @BindView(R.id.eliminarButton)
    Button mEliminarButton;

    Entrenador mEntrenador = new Entrenador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        ButterKnife.bind(this);

        int entrenadorID = (int) getIntent().getExtras().get("ENTRENADOR_ID");
        mEntrenador = OrmHelper.buscarEntrenadorPorId(this, entrenadorID);

        mToolbar.setTitle(mEntrenador.getNombre() + " " + mEntrenador.getApellido());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picasso.with(this).load(R.drawable.default_user).into(mImageView);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mIdentificacion.setText(getString(R.string.identificacion) + ": " + String.valueOf(mEntrenador.getIdentificacion()));
        mNomnbre.setText(getString(R.string.nameText) + ": " + mEntrenador.getNombre());
        mApellido.setText(getString(R.string.lastNameText) + ": " + mEntrenador.getApellido());
        mNacimiento.setText(getString(R.string.fecha_nacimiento) + ": " + mEntrenador.getFechaNacimiento());
        mPeso.setText(getString(R.string.peso) + ": " + String.valueOf(mEntrenador.getPeso()));
        mEstatura.setText(getString(R.string.estatura) + ": " + String.valueOf(mEntrenador.getAltura()));

        mEditarButton.setOnClickListener(this);
        mEliminarButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editarButton:
                editar();
                break;
            case R.id.eliminarButton:
                eliminar();
                break;
        }
    }

    private void eliminar() {

        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle(getString(R.string.confirmacion));
        b.setMessage(getString(R.string.seguro_de_eliminar));
        b.setPositiveButton(this.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mEntrenador.eliminar(DetalleEntrenadorActivity.this);
                onBackPressed();
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

    private void editar() {
        Intent i = new Intent(this, FormularioEntrenadorActivity.class);
        i.putExtra("ENTRENADOR_ID", mEntrenador.getId());
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, ListaEntrenadoresActivity.class));
        finish();
    }
}
