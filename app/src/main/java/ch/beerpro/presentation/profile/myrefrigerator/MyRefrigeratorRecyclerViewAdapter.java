package ch.beerpro.presentation.profile.myrefrigerator;


import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.data.repositories.RefigeratorRepository;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.MyBeer;
import ch.beerpro.domain.models.MyBeerFromRating;
import ch.beerpro.domain.models.MyBeerFromWishlist;
import ch.beerpro.domain.models.RefrigeratorTypeOfBeer;
import ch.beerpro.presentation.profile.mybeers.MyBeersRecyclerViewAdapter;
import ch.beerpro.presentation.profile.mybeers.OnMyBeerItemInteractionListener;
import ch.beerpro.presentation.utils.DrawableHelpers;
import ch.beerpro.presentation.utils.EntityDiffItemCallback;

public class MyRefrigeratorRecyclerViewAdapter extends ListAdapter<RefrigeratorTypeOfBeer, MyRefrigeratorRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyRefrigeratorViewAdap";

    private static final DiffUtil.ItemCallback<RefrigeratorTypeOfBeer> DIFF_CALLBACK = new EntityDiffItemCallback<RefrigeratorTypeOfBeer>();

    private final OnMyRefrigeratorInteractionListener listener;
    private FirebaseUser user;

    public MyRefrigeratorRecyclerViewAdapter(OnMyRefrigeratorInteractionListener listener, FirebaseUser user){
        super(DIFF_CALLBACK);
        this.listener = listener;
        this.user = user;
    }

    @NonNull
    @Override
    public MyRefrigeratorRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_my_refrigerator_listenry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRefrigeratorRecyclerViewAdapter.ViewHolder holder, int position) {
        RefrigeratorTypeOfBeer entry = getItem(position);
        holder.bind(entry, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(RefrigeratorTypeOfBeer entry, OnMyRefrigeratorInteractionListener listener) {

        }
    }
}
