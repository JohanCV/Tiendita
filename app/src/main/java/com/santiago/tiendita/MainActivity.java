package com.santiago.tiendita;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {
    public static final String API_URL = "https://viveyupi.com";
    ListView listaCategorias;
    Button btnProvedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProvedor = findViewById(R.id.btn_proveedor);
        btnProvedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Proveedor.class);
                startActivity(i);
            }
        });

        listaCategorias = findViewById(R.id.list_categorias);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Yupi yupi = retrofit.create(Yupi.class);
        Call<List<Categoria>> call = yupi.categorias(1);

        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                Log.e("code", response.code()+"");
                switch (response.code()) {
                    case 200:
                        List<Categoria> categorias = response.body();
                        CategoriasAdapter a =
                                new CategoriasAdapter(getApplicationContext(),
                                        R.layout.item_categoria,categorias);
                        listaCategorias.setAdapter(a);
                        for (Categoria categoria : categorias) {
                            Log.e("cat", categoria.imagen_banner+"");
                        }
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });

        Call<Token> call_login = yupi.login("vendedor","codigotecsup");
        call_login.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Log.e("code", response.code()+"");
                switch (response.code()) {
                    case 200:
                        Log.d("token","asdasd");
                        Token token = response.body();
                        Log.d("token",token.token);
                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });
    }
    
    public interface Yupi{
        @GET("/api/categorias/nivel/{nivel}/")
        Call<List<Categoria>> categorias(
                @Path("nivel") Integer nivel
        );

        @POST("/api/api-token-auth/")
        @FormUrlEncoded
        Call<Token> login(
                @Field("username")String usr,
                @Field("password")String pwd
        );
    }
}
