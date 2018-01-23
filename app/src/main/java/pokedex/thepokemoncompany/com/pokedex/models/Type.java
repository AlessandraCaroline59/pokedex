package pokedex.thepokemoncompany.com.pokedex.models;

import java.io.Serializable;

/**
 * Created by Alessandra on 21/01/2018.
 */

public class Type implements Serializable {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
