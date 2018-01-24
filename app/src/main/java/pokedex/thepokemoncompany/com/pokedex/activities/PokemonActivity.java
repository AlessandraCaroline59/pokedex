package pokedex.thepokemoncompany.com.pokedex.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;

import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.adapters.ResultAdapter;
import pokedex.thepokemoncompany.com.pokedex.interfaces.PokemonService;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonList;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonResponse;
import pokedex.thepokemoncompany.com.pokedex.models.Result;
import pokedex.thepokemoncompany.com.pokedex.utils.Constants;
import pokedex.thepokemoncompany.com.pokedex.utils.EndlessScrollListener;
import pokedex.thepokemoncompany.com.pokedex.utils.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonActivity extends AppCompatActivity {

    private ListView pokemonListView;
    ArrayList<Result> paginatedPokemonList = new ArrayList<Result>();
    ArrayList<Result> allPokemonList = new ArrayList<Result>();
    Retrofit retrofit;
    private int offset;
    private PokemonResponse pokemonResponse = new PokemonResponse();
    private ResultAdapter res;
    private final Integer limit = 20;
    PokemonList pokemonListSingleton = PokemonList.getInstance();
    private Boolean hasToDownloadResultList = false;
    SpinnerDialog spinnerDialog = new SpinnerDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        allPokemonList = pokemonListSingleton.getPokemonResultList();

        pokemonListView = (ListView) findViewById(R.id.pokemonListId);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        offset = 0;

        pokemonListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                pokemonResponse = pokemonListSingleton.getPokemonResponse();
                if (pokemonResponse.getNext() != null) {
                    offset += 20;
                    getData(offset);
                    return true;
                } else {
                    return false;
                }
            }
        });
        if(paginatedPokemonList !=null) {
            showData();
        }
        spinnerDialog.showLoadingDialog(this, getString(R.string.loading_title));
        getData(offset);
    }

    private void getData(int offset){
        if (allPokemonList.size() == offset) {
            hasToDownloadResultList = true;
        }
        if (hasToDownloadResultList) {
            PokemonService service = retrofit.create(PokemonService.class);
            Call<PokemonResponse> pokemonResponseCall = service.getListPokemon(limit, offset);

            pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
                @Override
                public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                    if (response.isSuccessful()) {
                        pokemonResponse = response.body();
                        pokemonListSingleton.setPokemonResponse(pokemonResponse);
                        paginatedPokemonList = pokemonResponse.getResults();
                        pokemonListSingleton.setPokemonResultList(paginatedPokemonList);
                        allPokemonList = pokemonListSingleton.getPokemonResultList();

                        spinnerDialog.dismissLoadingDialog(PokemonActivity.this);

                        res.addPokemonList(paginatedPokemonList);
                    } else {
                        Log.e(Constants.TAG, "onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<PokemonResponse> call, Throwable t) {
                    Log.e(Constants.TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            res.addPokemonList(allPokemonList);
            spinnerDialog.dismissLoadingDialog(PokemonActivity.this);
        }
    }

    private void showData(){
        res = new ResultAdapter(PokemonActivity.this,
                paginatedPokemonList);

        pokemonListView.setAdapter( res );
    }



}
