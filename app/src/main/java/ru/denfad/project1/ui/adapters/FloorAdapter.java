package ru.denfad.project1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.denfad.project1.R;
import ru.denfad.project1.models.Entrance;

public class FloorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(int position);
    }

    private Entrance entrance;
    private final FloorAdapter.OnStateClickListener onClickListener;

    public FloorAdapter(OnStateClickListener onClickListener, Entrance entrance) {
        this.onClickListener = onClickListener;
        this.entrance = entrance;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.floor_item, parent, false);
        return new EntranceHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EntranceHolder e = (EntranceHolder) holder;
        e.label.setText(String.valueOf(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onStateClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entrance.getFloors();
    }

    public void setBuildings(Entrance entrance){
        this.entrance = entrance;
    }

    public static class EntranceHolder extends RecyclerView.ViewHolder {

        TextView label;

        EntranceHolder(View itemView) {
            super(itemView);
            label= itemView.findViewById(R.id.floor_label);

        }
    }

}

