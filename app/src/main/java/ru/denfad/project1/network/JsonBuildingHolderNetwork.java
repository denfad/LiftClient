package ru.denfad.project1.network;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.denfad.project1.models.Building;
import ru.denfad.project1.models.Elevator;

public interface JsonBuildingHolderNetwork {

   @GET("/build/all")
    public Call<List<Building>> getAllBuildings();

    @GET("/build/")
    public Call<Building> getBuilding(@Query("id") int id);

    @GET("/build/search")
    public Call<List<Building>> findBuilding(@Query("address") String address);

    @GET("/elev/entrance/")
    public Call<List<Elevator>> getElevators(@Query("id") int id);

}
