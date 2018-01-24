package pokedex.thepokemoncompany.com.pokedex.interfaces;

import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonResponse;
import pokedex.thepokemoncompany.com.pokedex.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Alessandra on 21/01/2018.
 */

public interface PokemonService {

    @GET("pokemon/")
    Call<PokemonResponse> getListPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonDetail(@Path("id") String id);

    @GET("pokemon/?limit=9999999999&offset=0")
    Call<PokemonResponse>getCompleteListPokemon();



}
