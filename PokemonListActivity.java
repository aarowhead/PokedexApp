package aaron.com.pokedexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import retrofit2.Call;

public class PokemonListActivity extends AppCompatActivity implements ConnectionErrorDialogFragment.TryAgainListener {

    private RecyclerView pokemonRecyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        controller = new Controller(this);

        pokemonRecyclerView = (RecyclerView)findViewById(R.id.pokemon_list_recycler_view);
        pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonListAdapter = new PokemonListAdapter(controller);
        pokemonRecyclerView.setAdapter(pokemonListAdapter);

        //controller.getPokemon(10,0);
    }

    public void updatePokemonList(AllPokemonResponse allPokemon){
        pokemonListAdapter.setMyPokemon(allPokemon.getPokemonInfo());
        pokemonListAdapter.notifyDataSetChanged();
    }

    public void showPokemonInfo(PokemonInfo pokemonInfo){
        PokemonInfoDialogFragment infoDialog = new PokemonInfoDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("pokemonInfo", pokemonInfo);
        infoDialog.setArguments(args);
        infoDialog.show(getSupportFragmentManager(), "PokemonInfo");
    }

    public void showConnectionError(String errorInfo, Call<AllPokemonResponse> call){
        ConnectionErrorDialogFragment errorDialog = new ConnectionErrorDialogFragment();
        Bundle args = new Bundle();
        args.putString("errorInfo", errorInfo);
        args.putSerializable("failedCall", new CallSerializableWrapper(call));
        errorDialog.setArguments(args);
        errorDialog.show(getSupportFragmentManager(), "ErrorInfo");
    }

    @Override
    public void onTryAgain(Call<AllPokemonResponse> call) {
        controller.resendCall(call);
    }
}
