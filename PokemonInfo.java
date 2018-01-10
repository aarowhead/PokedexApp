package aaron.com.pokedexapp;

import java.io.Serializable;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonInfo implements Serializable {
    private String url;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
