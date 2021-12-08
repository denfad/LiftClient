package ru.denfad.project1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.denfad.project1.R;
import ru.denfad.project1.models.Building;
import ru.denfad.project1.models.Elevator;
import ru.denfad.project1.models.Entrance;
import ru.denfad.project1.network.NetworkService;
import ru.denfad.project1.ui.adapters.ElevatorAdapter;
import ru.denfad.project1.ui.adapters.EntranceAdapter;
import ru.denfad.project1.ui.adapters.FloorAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Entrance> entrances = new ArrayList<>();
    private Building building = null;
    private MyTimer myTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //отобрадение этажей
        RecyclerView floorList = findViewById(R.id.floors);
        GridLayoutManager llm2 = new GridLayoutManager(getApplicationContext(),4);
        floorList.setLayoutManager(llm2);
        FloorAdapter.OnStateClickListener onStateClickListener2 = new FloorAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int position) {

            }
        };
        FloorAdapter adapter2 = new FloorAdapter(onStateClickListener2,new Entrance(1,null,0, null));
        floorList.setAdapter(adapter2);
        //отображение лифтов
        RecyclerView elevators = findViewById(R.id.elevators);
        LinearLayoutManager llm3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        elevators.setLayoutManager(llm3);
        ElevatorAdapter.OnStateClickListener onStateClickListener3 = new ElevatorAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int position) {
                //TODO
            }
        };
        ElevatorAdapter adapter3 = new ElevatorAdapter(onStateClickListener3,new ArrayList<>());
        elevators.setAdapter(adapter3);


        //отображение подъездов
        RecyclerView list = findViewById(R.id.entrances);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        list.setLayoutManager(llm);
        EntranceAdapter.OnStateClickListener onStateClickListener = new EntranceAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int position) {
                adapter2.setBuildings(entrances.get(position));
                adapter2.notifyDataSetChanged();
                adapter3.setElevators(entrances.get(position).getElevators());
                adapter3.notifyDataSetChanged();
                try {
                    myTimer.setId(entrances.get(position).getId());
                }
                catch (Exception e){
                    myTimer = new MyTimer(2000,2000,entrances.get(position).getId(),adapter3);
                    myTimer.start();
                }

            }
        };
        EntranceAdapter adapter = new EntranceAdapter(onStateClickListener,entrances, getResources());
        list.setAdapter(adapter);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);
        NetworkService.getInstance()
                .getJSONApi()
                .getBuilding(id)
                .enqueue(new Callback<Building>() {
                    @Override
                    public void onResponse(Call<Building> call, Response<Building> response) {
                        building = response.body();
                        entrances = building.getEntrances();
                        adapter.setBuildings(entrances);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Building> call, Throwable t) {

                    }
                });
    }




    private class MyTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */

        int id;
        ElevatorAdapter adapter;
        public MyTimer(long millisInFuture, long countDownInterval, int id, ElevatorAdapter adapter) {
            super(millisInFuture, countDownInterval);
            this.id = id;
            this.adapter = adapter;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            NetworkService.getInstance()
                    .getJSONApi()
                    .getElevators(id)
                    .enqueue(new Callback<List<Elevator>>() {
                        @Override
                        public void onResponse(Call<List<Elevator>> call, Response<List<Elevator>> response) {
                            adapter.setElevators(response.body());
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<List<Elevator>> call, Throwable t) {

                        }
                    });
        }

        @Override
        public void onFinish() {
            myTimer = new MyTimer(2000,2000, id, adapter);
            myTimer.start();
        }


    }
}