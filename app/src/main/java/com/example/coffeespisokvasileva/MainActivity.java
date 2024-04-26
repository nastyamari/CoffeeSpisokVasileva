package com.example.coffeespisokvasileva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Item> mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mUser = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CoffeeAdapter adapter = new CoffeeAdapter(mUser);
        mRecyclerView.setAdapter(adapter);


        CoffeeAPI flowersAPI = CoffeeAPI.retrofit.create(CoffeeAPI.class);
        final Call<List<Item>> call = flowersAPI.getData();
        call.enqueue(new Callback<List<Item>>() {
                         @Override
                         public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                             // response.isSuccessfull() возвращает true если код ответа 2xx
                             if (response.isSuccessful()) {
                                 mUser.addAll(response.body());
                                 mRecyclerView.getAdapter().notifyDataSetChanged();
                             } else {
                                 // Обрабатываем ошибку
                                 ResponseBody errorBody = response.errorBody();
                                 try {
                                     Toast.makeText(MainActivity.this, errorBody.string(),
                                             Toast.LENGTH_SHORT).show();
                                 } catch (IOException e) {
                                     e.printStackTrace();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Item>> call, Throwable throwable) {
                             Toast.makeText(MainActivity.this, "Что-то пошло не так",
                                     Toast.LENGTH_SHORT).show();
                         }
                     }
        );
    }
}