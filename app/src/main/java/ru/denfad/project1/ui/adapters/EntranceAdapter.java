package ru.denfad.project1.ui.adapters;


import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.cursoradapter.widget.ResourceCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.denfad.project1.R;
import ru.denfad.project1.models.Building;
import ru.denfad.project1.models.Entrance;

public class EntranceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnStateClickListener{
        void onStateClick(int position);
    }

    private final OnStateClickListener onClickListener;

    private List<Entrance> entrances;
    private static Resources resources;
    private EntranceHolder selectedEntrance = null;

    public EntranceAdapter(OnStateClickListener onClickListener, List<Entrance> entrances, Resources resources) {
        this.onClickListener = onClickListener;
        this.entrances = entrances;
        this.resources = resources;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entrance_item, parent, false);
        return new EntranceHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EntranceHolder e = (EntranceHolder) holder;
        e.label.setText(entrances.get(position).getLabel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    selectedEntrance.select();
                } catch (Exception e){}
                e.select();
                selectedEntrance = e;
                onClickListener.onStateClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entrances.size();
    }

    public void setBuildings(List<Entrance> entrances){
        this.entrances = entrances;
    }

    public static class EntranceHolder extends RecyclerView.ViewHolder {

        TextView label;
        ConstraintLayout layout;
        boolean selected = false;

        EntranceHolder(View itemView) {
            super(itemView);
            label= itemView.findViewById(R.id.entrance_label);
            layout = itemView.findViewById(R.id.entrance_layout);
        }

        void select(){
            if (selected) {
                layout.setBackground(resources.getDrawable(R.drawable.rectangle));
                selected = false;
            }
            else {
                selected = true;
                layout.setBackground(resources.getDrawable(R.drawable.pressed_rectangle));
            }
        }

    }

}
