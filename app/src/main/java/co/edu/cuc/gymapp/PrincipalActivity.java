package co.edu.cuc.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.view.PrincipalAdaptador;


public class PrincipalActivity extends AppCompatActivity implements PrincipalAdaptador.OnHomeListItemClickListener {

    @BindView(R.id.homeToolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerViewHome)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ButterKnife.bind(this);

        mToolbar.setTitle(R.string.homeText);
        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ((LinearLayoutManager) mRecyclerView.getLayoutManager()).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(new PrincipalAdaptador(this, this));
    }

    @Override
    public void OnHomeListItemClickListener(Integer position) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, ListaEntrenadoresActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ListaClientesActivity.class));
                break;
            default:
                startActivity(new Intent(this, ListaHorariosActivity.class));
                break;
        }
    }
}
