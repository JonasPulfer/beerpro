package ch.beerpro.presentation.profile.myfridge;


import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.RequestOptions;

import java.text.DateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.GlideApp;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.presentation.utils.EntityPairDiffItemCallback;

public class MyFridgeRecyclerViewAdapter extends ListAdapter<Pair<FridgeContent, Beer>, MyFridgeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyRefrigeratorViewAdap";

    private static final DiffUtil.ItemCallback<Pair<FridgeContent, Beer>> DIFF_CALLBACK = new EntityPairDiffItemCallback<>();

    private final OnMyFridgeInteractionListener listener;

    public MyFridgeRecyclerViewAdapter(OnMyFridgeInteractionListener listener){
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_my_fridge_listenry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Pair<FridgeContent, Beer> item = getItem(position);
        holder.bind(item.first, item.second, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.manufacturer)
        TextView manufacturer;

        @BindView(R.id.photo)
        ImageView photo;

        @BindView(R.id.addedAt)
        TextView addedAt;

        @BindView(R.id.amount)
        TextView amount;

        @BindView(R.id.removeFromFridge)
        Button removeButton;

        @BindView(R.id.increaseAmount)
        Button increaseAmountButton;

        @BindView(R.id.decreaseAmount)
        Button decreaseAmountButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FridgeContent content, Beer beer, OnMyFridgeInteractionListener listener) {
            name.setText(beer.getName());
            manufacturer.setText(beer.getManufacturer());
            name.setText(beer.getName());
            GlideApp.with(itemView).load(beer.getPhoto()).apply(new RequestOptions().override(240, 240).centerInside())
                    .into(photo);
            itemView.setOnClickListener(v -> listener.onMoreClickedListener(photo, beer));

            String formattedDate =
                    DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT).format(content.getAddedAt());
            addedAt.setText(formattedDate);
            amount.setText(String.valueOf(content.getAmount()));
            removeButton.setOnClickListener(v -> listener.onRemoveButtonClickedListener(content.getUserId(), content.getBeerId()));
            increaseAmountButton.setOnClickListener(
                    v -> listener.onIncreaseAmountClickedListener(content.getAmount(), content.getUserId(), content.getBeerId())
            );
            decreaseAmountButton.setOnClickListener(v ->
                    listener.onDecreaseAmountClickedListener(content.getAmount(), content.getUserId(), content.getBeerId())
            );

        }
    }
}
