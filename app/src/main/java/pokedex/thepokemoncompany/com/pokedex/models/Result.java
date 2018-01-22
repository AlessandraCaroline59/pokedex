package pokedex.thepokemoncompany.com.pokedex.models;

/**
 * Created by Alessandra on 21/01/2018.
 */

public class Result {
    private Integer id;
    private String url;
    private String name;
    private String image;

    public Integer getId() {
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name.toUpperCase();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
