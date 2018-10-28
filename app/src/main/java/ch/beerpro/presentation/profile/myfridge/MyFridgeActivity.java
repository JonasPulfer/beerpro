package ch.beerpro.presentation.profile.myfridge;

import android.os.Bundle;
import android.util.Pair;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.domain.utils.ThemeStateService;
import lombok.val;

public class MyFridgeActivity extends AppCompatActivity implements OnMyFridgeInteractionListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MyFridgeViewModel model;
    private MyFridgeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fridge);
        ThemeStateService.setThemeForActivity(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mein Kühlschrank");

        model = ViewModelProviders.of(this).get(MyFridgeViewModel.class);
        model.getWholeFridgeWithBeers().observe(this, this::updateMyFridge);

        val layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

       adapter = new MyFridgeRecyclerViewAdapter(this);
       recyclerView.setAdapter(adapter);

    }


    private void updateMyFridge(List<Pair<FridgeContent, Beer>> entries){
        adapter.submitList(entries);

        //Ansicht, wenn keine Einträge!!
    }


}
