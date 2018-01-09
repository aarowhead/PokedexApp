package aaron.com.pokedexapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Aaron on 1/9/2018.
 */

public interface PokedexAPI {

    @GET("/api/v2/pokemon/")
    Call<AllPokemonResponse> getPokemon(@Query("limit") int limit, @Query("offset") int offset);

}
