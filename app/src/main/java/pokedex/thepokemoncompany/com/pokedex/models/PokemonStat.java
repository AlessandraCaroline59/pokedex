package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class PokemonStat implements Serializable {
    private Stat stat;
    private Integer base_stat;

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    public Integer getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(Integer base_stat) {
        this.base_stat = base_stat;
    }
}
