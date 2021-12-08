package ru.denfad.project1.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.denfad.project1.R;
import ru.denfad.project1.models.Building;
import ru.denfad.project1.network.NetworkService;
import ru.denfad.project1.ui.adapters.BuildingAdapter;

public class SearchActivity extends AppCompatActivity {

    private List<Building> buildings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText search = findViewById(R.id.search);

        RecyclerView list = findViewById(R.id.buildings);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(llm);
        BuildingAdapter.OnStateClickListener onClickListener = new BuildingAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(int position) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("id", buildings.get(position).getId());
                startActivity(intent);
            }
        };
        BuildingAdapter buildingAdapter = new BuildingAdapter(onClickListener, buildings);
        list.setAdapter(buildingAdapter);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NetworkService.getInstance()
                        .getJSONApi()
                        .findBuilding(search.getText().toString())
                        .enqueue(new Callback<List<Building>>() {
                            @Override
                            public void onResponse(Call<List<Building>> call, Response<List<Building>> response) {
                                buildings = response.body();
                                buildingAdapter.setBuildings(buildings);
                                buildingAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<List<Building>> call, Throwable t) {
                                Log.e("Network", t.getLocalizedMessage());
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button scanner = findViewById(R.id.qr);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ScannerActivity.class));
            }
        });

    }
}