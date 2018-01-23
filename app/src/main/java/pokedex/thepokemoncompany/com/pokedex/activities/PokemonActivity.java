package pokedex.thepokemoncompany.com.pokedex.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;

import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.adapters.ResultAdapter;
import pokedex.thepokemoncompany.com.pokedex.interfaces.PokemonService;
import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonResponse;
import pokedex.thepokemoncompany.com.pokedex.models.Result;
import pokedex.thepokemoncompany.com.pokedex.utils.Constants;
import pokedex.thepokemoncompany.com.pokedex.utils.EndlessScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonActivity extends AppCompatActivity {

    private ListView pokemonListView;
    ArrayList<Result> allPokemonList = new ArrayList<Result>();
    Retrofit retrofit;
    private int offset;
    private PokemonResponse pokemonResponse;
    private ResultAdapter res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokemonListView = (ListView) findViewById(R.id.pokemonListId);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        offset = 0;

        pokemonListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if(pokemonResponse.getNext() != null){
                    offset+=20;
                    getData(offset);
                    return true;
                }else{
                    return false;
                }
            }
        });
        if(allPokemonList!=null) {
            showData();
        }
        getData(offset);
    }

    private void getData(int offset){
        PokemonService service = retrofit.create(PokemonService.class);
        Call<PokemonResponse> pokemonResponseCall = service.getListPokemon(20, offset);

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if(response.isSuccessful()){
                    pokemonResponse = response.body();
                    allPokemonList = pokemonResponse.getResults();

                    res.addPokemonList(allPokemonList);
                }else{
                    Log.e(Constants.TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e(Constants.TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void showData(){
        res = new ResultAdapter(PokemonActivity.this,
                allPokemonList);

        pokemonListView.setAdapter( res );
    }


}
