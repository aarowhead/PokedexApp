package aaron.com.pokedexapp;

import android.support.v7.util.SortedList;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private List<PokemonInfo> myPokemonInfo;
    private Controller controller;

    public PokemonListAdapter(Controller controller){
        this.controller = controller;
    }

    public void setMyPokemon(List<PokemonInfo> myPokemonInfo) {
        this.myPokemonInfo = myPokemonInfo;
        Collections.sort(myPokemonInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pokemonName;
        public CardView pokemonCard;

        public ViewHolder(View v){
            super(v);
            pokemonName = (TextView)v.findViewById(R.id.pokemon_text_view);
            pokemonCard = (CardView)v.findViewById(R.id.pokemon_card_view);
        }
    }

    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemon_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        PokemonInfo pokemon = myPokemonInfo.get(position);
        holder.pokemonName.setText(pokemon.getName());

        holder.pokemonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showPokemonInfo(myPokemonInfo.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        if(myPokemonInfo == null){
            return 0;
        }
        return myPokemonInfo.size();
    }
}
