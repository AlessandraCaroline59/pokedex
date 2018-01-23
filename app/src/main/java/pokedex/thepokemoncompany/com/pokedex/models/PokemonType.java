package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class PokemonType implements Serializable {
    private Type type;
    private Integer slot;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
