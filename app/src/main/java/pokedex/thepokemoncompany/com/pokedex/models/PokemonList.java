package pokedex.thepokemoncompany.com.pokedex.models;

import android.util.Log;

import java.util.ArrayList;

import pokedex.thepokemoncompany.com.pokedex.utils.Constants;

/**
 * Created by Alessandra on 23/01/2018.
 */

public class PokemonList {

    private static PokemonList mInstance = null;
    private ArrayList<Result> pokemonResultList = new ArrayList<>();
    private ArrayList<Pokemon> pokemonDetailsList = new ArrayList<>();
    private PokemonResponse pokemonResponse = new PokemonResponse();

    public static PokemonList getInstance(){
        if(mInstance == null){
            mInstance = new PokemonList();
        }
        return mInstance;
    }

    public void setPokemonResultList(ArrayList<Result> pokemonList) {
        Boolean hasInList = false;
        for (int i = 0; i < pokemonList.size(); i++) {
            for (int j = 0; j < pokemonResultList.size(); j++) {
                if (pokemonResultList.get(j).getId() == pokemonList.get(i).getId()) {
                    hasInList = true;
                    break;
                }
            }
            if (!hasInList) {
                pokemonResultList.add(pokemonList.get(i));
            }
            hasInList = false;
        }
    }

    public ArrayList<Result> getPokemonResultList() {
        return pokemonResultList;
    }

    public void setPokemonDetailsList(ArrayList<Pokemon> pokemonList) {
        Boolean hasInList = false;
        for (int i = 0; i < pokemonList.size(); i++) {
            for (int j = 0; j < pokemonDetailsList.size(); j++) {
                if (pokemonDetailsList.get(j).getId() == pokemonList.get(i).getId()) {
                    hasInList = true;
                    break;
                }
            }
            if (!hasInList) {
                pokemonDetailsList.add(pokemonList.get(i));
            }
            hasInList = false;
        }
    }

    public ArrayList<Pokemon> getPokemonDetailsList() {
        return pokemonDetailsList;
    }

    public PokemonResponse getPokemonResponse() {
        return pokemonResponse;
    }

    public void setPokemonResponse(PokemonResponse pokemonResponse) {
        this.pokemonResponse = pokemonResponse;
    }
}
