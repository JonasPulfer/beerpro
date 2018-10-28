package ch.beerpro.presentation.profile.myfridge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import lombok.val;

public class MyFridgeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MyRefrigeratorViewModel model;
    private MyRefrigeratorRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_refrigerator);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mein Kühlschrank");

        model = ViewModelProviders.of(this).get(MyRefrigeratorViewModel.class);
//        model.getMyRefrigeratorContent().observe(this, this::updateMyRatings);

        val layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//       adapter = new MyRefrigeratorRecyclerViewAdapter(this, model.getCurrentUser());
        recyclerView.setAdapter(adapter);
    }



}
