package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class PokemonSprite implements Serializable {
    private String front_default;

    public String getFront_default() {
        return front_default;
    }

    public void setFront_default(String front_default) {
        this.front_default = front_default;
    }
}
