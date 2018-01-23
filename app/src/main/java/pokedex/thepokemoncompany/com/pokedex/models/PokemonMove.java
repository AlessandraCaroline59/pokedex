package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class PokemonMove implements Serializable {
    private Move move;

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
