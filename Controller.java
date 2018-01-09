package aaron.com.pokedexapp;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aaron on 1/9/2018.
 */

public class Controller implements Callback<PokedexAPI> {

    static final String BASE_URL = "http://pokeapi.co";

    //TODO: Change this to void and make asynchronous
    public AllPokemonResponse getPokemon(){
        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokedexAPI pokedexAPI = retrofit.create(PokedexAPI.class);

        Call<AllPokemonResponse> call = pokedexAPI.getPokemon(10, 5);
        //call.enqueue();
        try {
            Response<AllPokemonResponse> response = call.execute();
            AllPokemonResponse responseBody = response.body();
            return responseBody;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResponse(Call<PokedexAPI> call, Response<PokedexAPI> response) {

    }

    @Override
    public void onFailure(Call<PokedexAPI> call, Throwable t) {

    }
}
