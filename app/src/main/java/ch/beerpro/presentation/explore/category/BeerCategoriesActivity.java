package ch.beerpro.presentation.explore.category;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class BeerCategoriesActivity extends AppCompatActivity implements FilterOnItemSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.emptyView)
    View emptyView;

    private FilterOnItemSelectedListener mListener = this;
    private FilterResultRecyclerViewAdapter adapter = new FilterResultRecyclerViewAdapter(mListener);

    private void handleBeersChanged(List<Beer> beers) {
        adapter.submitList(new ArrayList<>(beers));
        if (beers.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String category = getIntent().getStringExtra("category");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryentries);
        ButterKnife.bind(this);

        CategoryViewModel model = ViewModelProviders.of(this).get(CategoryViewModel.class);
        model.setSearchTerm(category);
        model.getFilteredBeers().observe(this, this::handleBeersChanged);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void onItemSelected(View animationSource, Beer item) {
        Intent intent = new Intent(BeerCategoriesActivity.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
        startActivity(intent);
    }
}
