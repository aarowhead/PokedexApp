package aaron.com.pokedexapp.Models;

import java.io.Serializable;

import aaron.com.pokedexapp.Models.AllPokemonResponse;
import retrofit2.Call;

/**
 * Created by Aaron on 1/10/2018.
 */

public class CallSerializableWrapper implements Serializable {
    private Call<AllPokemonResponse> call;

    public CallSerializableWrapper(Call<AllPokemonResponse> call) {
        this.call = call;
    }

    public Call<AllPokemonResponse> getCall() {
        return call;
    }

    public void setCall(Call<AllPokemonResponse> call) {
        this.call = call;
    }
}
