package aaron.com.pokedexapp;

import java.util.List;

/**
 * Created by Aaron on 1/9/2018.
 */

public class AllPokemonResponse {
    private int count;
    private String previous;
    private String next;
    private List<PokemonLinks> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<PokemonLinks> getPokemonLinks() {
        return results;
    }

    public void setPokemonLinks(List<PokemonLinks> pokemonLinks) {
        this.results = pokemonLinks;
    }
}
