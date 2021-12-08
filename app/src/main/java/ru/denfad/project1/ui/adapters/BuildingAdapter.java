package ru.denfad.project1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.denfad.project1.R;
import ru.denfad.project1.models.Building;
import ru.denfad.project1.models.Entrance;

public class BuildingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(int position);
    }

    private final OnStateClickListener onClickListener;

    private List<Building> buildings;

    public BuildingAdapter(OnStateClickListener onClickListener, List<Building> buildings) {
        this.onClickListener = onClickListener;
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.building_item, parent, false);
        return new BuildingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BuildingViewHolder b = (BuildingViewHolder) holder;
        b.address.setText(buildings.get(position).getAddress());
        b.entrances.setText("Подъезды: ");
        for(Entrance e:buildings.get(position).getEntrances()) {
            b.entrances.append(e.getLabel()+" ");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onStateClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    public void setBuildings(List<Building> buildings){
        this.buildings = buildings;
    }

    public static class BuildingViewHolder extends RecyclerView.ViewHolder {

        TextView address;
        TextView entrances;

        BuildingViewHolder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            entrances = itemView.findViewById(R.id.entrances);
        }
    }

}
