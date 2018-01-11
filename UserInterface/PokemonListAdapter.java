package aaron.com.pokedexapp.UserInterface;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aaron.com.pokedexapp.Controller;
import aaron.com.pokedexapp.Models.PokemonInfo;
import aaron.com.pokedexapp.R;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private List<PokemonInfo> myPokemonInfo;
    private Controller controller;

    public PokemonListAdapter(Controller controller){
        this.controller = controller;
        myPokemonInfo = new ArrayList<>();
    }

    public void setMyPokemon(List<PokemonInfo> myPokemonInfo) {
        this.myPokemonInfo.addAll(myPokemonInfo);
        Collections.sort(this.myPokemonInfo);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView pokemonName;
        public CardView pokemonCard;
        public ProgressBar progressBar;

        public ViewHolder(View v){
            super(v);
            pokemonName = (TextView)v.findViewById(R.id.pokemon_text_view);
            pokemonCard = (CardView)v.findViewById(R.id.pokemon_card_view);
            progressBar = (ProgressBar)v.findViewById(R.id.card_progress_bar);
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
        holder.progressBar.setVisibility(View.GONE);

        holder.pokemonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showPokemonInfo(myPokemonInfo.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPokemonInfo.size();
    }
}
