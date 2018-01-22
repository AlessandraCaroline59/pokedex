package pokedex.thepokemoncompany.com.pokedex.models;

import java.util.ArrayList;


/**
 * Created by Alessandra on 21/01/2018.
 */

public class PokemonResponse {
    private Integer count;
    private String previous;
    private String next;
    private ArrayList<Result> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
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

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }
}
