package pokedex.thepokemoncompany.com.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alessandra on 21/01/2018.
 */

public class Ability implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
