package aaron.com.pokedexapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PokemonListActivity extends AppCompatActivity {

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
        pokemonListAdapter = new PokemonListAdapter();
        pokemonRecyclerView.setAdapter(pokemonListAdapter);

        controller.getPokemon();
    }

    public void updatePokemonList(AllPokemonResponse allPokemon){
        pokemonListAdapter.setMyPokemon(allPokemon);
        pokemonListAdapter.notifyDataSetChanged();
    }
}
