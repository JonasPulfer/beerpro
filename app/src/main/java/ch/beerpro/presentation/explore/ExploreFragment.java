package ch.beerpro.presentation.explore;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.beerpro.R;
import ch.beerpro.domain.utils.ThemeState;
import ch.beerpro.domain.utils.ThemeStateService;
import ch.beerpro.presentation.explore.search.SearchActivity;
import ch.beerpro.presentation.utils.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

/**
 * This fragment is the first fragment shown in the {@link ch.beerpro.presentation.MainActivity}. It lets users
 * "discover" beers by implementing a search (which simply opens the {@link SearchActivity} and two nested tabs where
 * users can browse beer categories and breweries.
 */
public class ExploreFragment extends Fragment {

    private static final String TAG = "HomeScreenActSearch";
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    /**
     * Fragments all need to have an empty constructor because the system might have the instantiate them.
     */
    public ExploreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, rootView);

        /*
         * Because the two fragments are nested in this fragment, we have to use the getChildFragmentManager.
         * Otherwise strange rendering and other bugs can occur at runtime.
         *
         * The two fragments will communicate directly with the MainActivity (that's why the MainActivity implements
         * two listener interfaces), bypassing this fragment.
         * */
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new BeerCategoriesFragment(), "Bierart");
        adapter.addFragment(new BeerManufacturersFragment(), "Brauerei");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // Hardcoded because of API-Version
        Drawable img;
        if(ThemeStateService.getCurrentTheme() == ThemeState.DEFAULT) {
            img = getContext().getResources().getDrawable(R.drawable.ic_search_black_24dp);
        } else {
            img = getContext().getResources().getDrawable(R.drawable.ic_search_yellow_24dp);
        }
        ((TextView)rootView.findViewById(R.id.beerSearchButton)).setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);

        return rootView;
    }

    /**
     * This method is called when the user clicks the beerSearchButton in fragment_explore. We also use ButterKnife's
     * {@link OnClick} annotation to keep the keep the code shorter.
     * <p>
     * When the activity is started, the shared element (the animationSource) will be animated. It's not actually a
     * shared element in the sense that both layouts somehow share the same instance, we can simply designate two
     * elements as shared by giving both the same sharedElementName, i.e., "search" in this case.
     * <p>
     * See <a href="https://guides.codepath.com/android/shared-element-activity-transition">this blog</a> for details
     * on how it works.
     *
     * @param animationSource
     */
    @OnClick(R.id.beerSearchButton)
    public void openSearchActivity(View animationSource) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        ActivityOptions options =
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), animationSource, "search");
        startActivity(intent, options.toBundle());
    }
}
