package aaron.com.pokedexapp;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonInfo implements Serializable, Comparable<PokemonInfo> {
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

    @Override
    public int compareTo(PokemonInfo o) {

        String[] urlPartsThis = this.getUrl().split("/");
        String[] urlPartsOther = o.getUrl().split("/");
        if(urlPartsThis[urlPartsThis.length - 1].matches("\\d+")
                && urlPartsOther[urlPartsOther.length - 1].matches("\\d+")){
            int thisId = Integer.parseInt(urlPartsThis[urlPartsThis.length - 1]);
            int otherId = Integer.parseInt(urlPartsOther[urlPartsOther.length - 1]);

            if(thisId == otherId){
                return 0;
            }else if(thisId > otherId){
                return 1;
            }else if(thisId < otherId){
                return -1;
            }
        }

        return 1;
    }

}
