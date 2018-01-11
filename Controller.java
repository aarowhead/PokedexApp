package aaron.com.pokedexapp;

import com.google.gson.Gson;

import aaron.com.pokedexapp.Models.AllPokemonResponse;
import aaron.com.pokedexapp.Models.PokemonInfo;
import aaron.com.pokedexapp.UserInterface.PokemonListActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aaron on 1/9/2018.
 */

public class Controller implements Callback<AllPokemonResponse> {

    private static final String BASE_URL = "http://pokeapi.co";
    private PokemonListActivity mainActivity;

    public Controller(PokemonListActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    public void showPokemonInfo(PokemonInfo pokemonInfo){
        mainActivity.showPokemonInfo(pokemonInfo);
    }

    public void getPokemon(int limit, int offset){
        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokedexAPI pokedexAPI = retrofit.create(PokedexAPI.class);

        Call<AllPokemonResponse> call = pokedexAPI.getPokemon(limit, offset);
        call.enqueue(this);

        //This prevents more requests for the same date being made while awaiting a response
        mainActivity.setAwaitingResponse(true);
    }

    public void resendCall(Call<AllPokemonResponse> call){
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AllPokemonResponse> call, Response<AllPokemonResponse> response) {
        mainActivity.updatePokemonList(response.body());

        //Open up for another response to be sent
        mainActivity.setAwaitingResponse(false);
    }

    @Override
    public void onFailure(Call<AllPokemonResponse> call, Throwable t) {
        mainActivity.showConnectionError(t.getMessage(), call.clone());

        //Open up for another response to be sent
        mainActivity.setAwaitingResponse(false);
    }
}
