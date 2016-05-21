package co.edu.cuc.gymapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.edu.cuc.gymapp.view.MyGridViewAdapter;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.homeGridView)
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        mGridView.setAdapter(new MyGridViewAdapter(this));
    }
}
