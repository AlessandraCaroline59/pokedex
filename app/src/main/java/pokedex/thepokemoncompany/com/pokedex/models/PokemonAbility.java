package pokedex.thepokemoncompany.com.pokedex.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class PokemonAbility implements Serializable {
    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
