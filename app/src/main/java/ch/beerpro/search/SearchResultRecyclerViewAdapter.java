package ch.beerpro.search;

import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.beerpro.R;
import ch.beerpro.search.SearchResultFragment.OnListFragmentInteractionListener;
import ch.beerpro.dummy.Beer;
import com.squareup.picasso.Picasso;


public class SearchResultRecyclerViewAdapter extends ListAdapter<Beer, SearchResultRecyclerViewAdapter.ViewHolder> {

    private static final DiffUtil.ItemCallback<Beer> DIFF_CALLBACK = new DiffUtil.ItemCallback<Beer>() {
        @Override
        public boolean areItemsTheSame(@NonNull Beer oldUser, @NonNull Beer newUser) {
            return oldUser.id == newUser.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Beer oldUser, @NonNull Beer newUser) {
            return oldUser.equals(newUser);
        }
    };

    private static final String TAG = "SearchResultRecyclerVie";
    private final OnListFragmentInteractionListener listener;

    public SearchResultRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        super(DIFF_CALLBACK);
        Log.i(TAG, "constructed");
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_searchresult_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Beer item = getItem(position);
        holder.mItem = item;
        holder.mContentView.setText(item.name);
        holder.mManufacturerView.setText(item.manufacturer);
        holder.mCategoryView.setText(item.category);
        holder.mContentView.setText(item.name);
        Picasso.get().load(item.photo).resize(240, 240).centerInside().into(holder.mImageView);
        holder.mRatingBar.setNumStars(5);
        holder.mRatingBar.setRating(item.avgRating);
        holder.mnumRatings.setText(holder.mView.getResources().getString(R.string.fmt_num_ratings, item.numRatings));
        holder.mView.setOnClickListener(v -> listener.onListFragmentInteraction(holder.mItem));
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mContentView;
        final TextView mManufacturerView;
        final TextView mCategoryView;
        final ImageView mImageView;
        final RatingBar mRatingBar;
        final TextView mnumRatings;
        Beer mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.name);
            mManufacturerView = view.findViewById(R.id.manufacturer);
            mCategoryView = view.findViewById(R.id.category);
            mImageView = view.findViewById(R.id.photo);
            mRatingBar = view.findViewById(R.id.ratingBar);
            mnumRatings = view.findViewById(R.id.numRatings);
        }
    }
}