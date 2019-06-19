package com.santiago.tiendita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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

public class Proveedor extends AppCompatActivity {

    public static final String API_URL = "https://viveyupi.com";
    ListView listaProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);
        
        listaProveedor = findViewById(R.id.list_proveedor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Proveedor.Yupi yupi = retrofit.create(Proveedor.Yupi.class);
        Call<List<Proveedores>> call = yupi.proveedores();

        call.enqueue(new Callback<List<Proveedores>>() {
            @Override
            public void onResponse(Call<List<Proveedores>> call, Response<List<Proveedores>> response) {
                Log.e("code", response.code()+"");
                switch (response.code()) {
                    case 200:
                        List<Proveedores> Proveedor = response.body();
                        ProveedorAdapter a =
                                new ProveedorAdapter(getApplicationContext(),
                                        R.layout.item_proveedor, Proveedor);
                        listaProveedor.setAdapter(a);

                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Proveedores>> call, Throwable t) {
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

        @POST("/api/api-token-auth/")
        @FormUrlEncoded
        Call<Token> login(
                @Field("username")String usr,
                @Field("password")String pwd
        );

        @GET("/api/categorias/nivel/{nivel}/")
        Call<List<Categoria>> categorias(
                @Path("nivel") Integer nivel
        );

        @GET("/api/proveedores/")
        Call<List<Proveedores>> proveedores(
        );

    }
}
