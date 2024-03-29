package com.example.desapp008;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desapp008.databinding.RecyclerListBinding;

public class EqAdapter extends ListAdapter <Earthquake,EqAdapter.EqViewHolder> {
    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldEq, @NonNull Earthquake newItem) {
            return oldEq.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldEq, @NonNull Earthquake newItem) {
            return oldEq.equals(newItem);
        }
    };

    protected EqAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnItemClickListener onItemClickListener;

    interface OnItemClickListener{
        void onItemClick(Earthquake earthquake);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerListBinding binding = RecyclerListBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EqViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }

    public class EqViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerListBinding binding;
        public EqViewHolder(@NonNull RecyclerListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Earthquake earthquake) {

            binding.magnitudeText.setText(String.valueOf(earthquake.getMagnitude()));
            binding.placeText.setText(earthquake.getPlace());
            binding.getRoot().setOnClickListener( v -> {
                onItemClickListener.onItemClick(earthquake);
            });
            binding.executePendingBindings();
        }

    }


}
