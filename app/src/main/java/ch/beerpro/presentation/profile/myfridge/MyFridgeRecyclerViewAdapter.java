package ch.beerpro.presentation.profile.myfridge;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.FridgeContent;
import ch.beerpro.presentation.utils.EntityDiffItemCallback;

public class MyFridgeRecyclerViewAdapter extends ListAdapter<FridgeContent, MyFridgeRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "MyRefrigeratorViewAdap";

    private static final DiffUtil.ItemCallback<FridgeContent> DIFF_CALLBACK = new EntityDiffItemCallback<FridgeContent>();

    private final OnMyFridgeInteractionListener listener;
    private FirebaseUser user;

    public MyFridgeRecyclerViewAdapter(OnMyFridgeInteractionListener listener, FirebaseUser user){
        super(DIFF_CALLBACK);
        this.listener = listener;
        this.user = user;
    }

    @NonNull
    @Override
    public MyFridgeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_my_fridge_listenry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFridgeRecyclerViewAdapter.ViewHolder holder, int position) {
        FridgeContent entry = getItem(position);
        holder.bind(entry, listener);
    }

    class ViewHolder extends RecyclerView.ViewHolder {




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FridgeContent entry, OnMyFridgeInteractionListener listener) {

        }
    }
}
