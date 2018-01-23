package pokedex.thepokemoncompany.com.pokedex.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Alessandra on 20/01/2018.
 */

public class Pokemon implements Serializable {
    private Integer id;
    private String name;
    private Integer base_experience;
    private Integer height;
    private Integer weight;
    private ArrayList<PokemonAbility> abilities;
    private ArrayList<PokemonMove> moves;
    private PokemonSprite sprites;
    private ArrayList<PokemonStat> stats;
    private ArrayList<PokemonType> types;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public ArrayList<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<PokemonMove> moves) {
        this.moves = moves;
    }

    public PokemonSprite getSprites() {
        return sprites;
    }

    public void setSprites(PokemonSprite sprites) {
        this.sprites = sprites;
    }

    public ArrayList<PokemonStat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<PokemonStat> stats) {
        this.stats = stats;
    }

    public ArrayList<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<PokemonType> types) {
        this.types = types;
    }
}
