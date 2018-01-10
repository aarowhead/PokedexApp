package aaron.com.pokedexapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private AllPokemonResponse myPokemon;

    public void setMyPokemon(AllPokemonResponse myPokemon) {
        this.myPokemon = myPokemon;
    }

    public AllPokemonResponse getMyPokemon() {
        return myPokemon;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pokemonName;

        public ViewHolder(View v){
            super(v);
            pokemonName = (TextView)v.findViewById(R.id.pokemon_text_view);
        }
    }

    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_card, parent, false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PokemonLink pokemon = myPokemon.getPokemonLinks().get(position);
        holder.pokemonName.setText(pokemon.getName());

    }

    @Override
    public int getItemCount() {
        if(myPokemon == null){
            return 0;
        }
        return myPokemon.getPokemonLinks().size();
    }
}
