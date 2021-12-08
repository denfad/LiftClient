package ru.denfad.project1.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.denfad.project1.R;
import ru.denfad.project1.models.Elevator;

public class ElevatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnStateClickListener {
        void onStateClick(int position);
    }

    private final ElevatorAdapter.OnStateClickListener onClickListener;

    private List<Elevator> elevators;

    public ElevatorAdapter(ElevatorAdapter.OnStateClickListener onClickListener, List<Elevator> elevators) {
        this.onClickListener = onClickListener;
        this.elevators = elevators;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elevator_item, parent, false);
        return new ElevatorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ElevatorHolder e = (ElevatorHolder) holder;
        e.label.setText(elevators.get(position).getLabel());
        e.floor.setText(String.valueOf(elevators.get(position).getFloor()));
        switch (elevators.get(position).getDirection()) {
            case "up":
                e.direction.setImageResource(R.drawable.up);
                break;
            case "down":
                e.direction.setImageResource(R.drawable.down);
                break;
            case "stop":
                e.direction.setImageResource(R.drawable.stop);
                break;
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
        return elevators.size();
    }

    public void setElevators(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public static class ElevatorHolder extends RecyclerView.ViewHolder {

        TextView label;
        TextView floor;
        ImageView direction;


        ElevatorHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.label);
            floor = itemView.findViewById(R.id.floor);
            direction = itemView.findViewById(R.id.direction);


        }
    }

}
