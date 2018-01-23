package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;

/**
 * Created by Alessandra on 21/01/2018.
 */

public class Stat implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
