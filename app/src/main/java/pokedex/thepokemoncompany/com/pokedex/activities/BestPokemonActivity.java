package pokedex.thepokemoncompany.com.pokedex.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.adapters.ResultAdapter;
import pokedex.thepokemoncompany.com.pokedex.interfaces.PokemonService;
import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonList;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonResponse;
import pokedex.thepokemoncompany.com.pokedex.models.Result;
import pokedex.thepokemoncompany.com.pokedex.utils.Constants;
import pokedex.thepokemoncompany.com.pokedex.utils.SpinnerDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BestPokemonActivity extends AppCompatActivity {

    private TextView sumAttack;
    private TextView sumDefense;
    private TextView sumSpeed;
    private TextView sumSAtk;
    private TextView sumSDef;
    private TextView sumHp;
    private TextView sumBaseXP;
    private TextView sumWeight;
    private ListView bestPokeList;

    private Integer sumAtk = 0;
    private Integer sumDef = 0;
    private Integer sumSpd = 0;
    private Integer sumSpAtk = 0;
    private Integer sumSpDef = 0;
    private Integer sumHealthPoints = 0;
    private Integer sumBaseEXP = 0;
    private Integer sumWght = 0;

    ArrayList<Result> allPokemonList = new ArrayList<>();
    Retrofit retrofit;
    private PokemonResponse pokemonResponse;
    private Pokemon pokemon;
    ArrayList<Pokemon> pokemonList = new ArrayList<>();
    ArrayList<String> idsToDownload = new ArrayList<>();
    PokemonList pokemonListSingleton = PokemonList.getInstance();
    private Boolean hasToDownloadPokemonDetails = true;
    private Boolean hasVerifiedPokemonsToDownload = false;
    private ResultAdapter res;
    SpinnerDialog spinnerDialog = new SpinnerDialog();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_pokemon);

        sumAttack = (TextView) findViewById(R.id.sumAttackId);
        sumDefense = (TextView) findViewById(R.id.sumDefenseId);
        sumSpeed = (TextView) findViewById(R.id.sumSpeedId);
        sumSAtk = (TextView) findViewById(R.id.sumSatkId);
        sumSDef = (TextView) findViewById(R.id.sumSdefId);
        sumHp = (TextView) findViewById(R.id.sumHpId);
        sumBaseXP = (TextView) findViewById(R.id.sumBaseXPId);
        sumWeight = (TextView) findViewById(R.id.sumPokemonWeightId);
        bestPokeList = (ListView) findViewById(R.id.bestPokeListId);

        retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        allPokemonList = pokemonListSingleton.getPokemonResultList();
        if(hasToDownloadPokemonDetails){
            spinnerDialog.showLoadingDialog(this, getString(R.string.loading_title));
            getPokemonDetailData();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        hasToDownloadPokemonDetails = false;
    }

    private void getPokemonDetailData(){
        PokemonService service = retrofit.create(PokemonService.class);
        if (!hasVerifiedPokemonsToDownload) {
            verifyPokemonsToDownload();
        }
        if ((idsToDownload.size() != 0) && (idsToDownload.size() <= allPokemonList.size())) {
            String pokemonId = idsToDownload.remove(0);
            Call<Pokemon> pokemonCall = service.getPokemonDetail(pokemonId);

            pokemonCall.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (response.isSuccessful()) {
                        pokemon = response.body();
                        pokemonList.add(pokemon);
                        getPokemonDetailData();
                    } else {
                        Log.e(Constants.TAG, "onResponse: " + response.errorBody());
                    }
                }
                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Log.e(Constants.TAG, "onFailure: " + t.getMessage());
                    getPokemonDetailData();
                }
            });
        } else {
            pokemonListSingleton.setPokemonDetailsList(pokemonList);
        }
        getBestPokemonsByAttack();
    }

    public ArrayList<String> verifyPokemonsToDownload() {
        hasVerifiedPokemonsToDownload = true;
        idsToDownload.clear();
        ArrayList<Pokemon> pokemons = pokemonListSingleton.getPokemonDetailsList();
        Boolean hasInList = false;
        for (int i = 0; i < allPokemonList.size(); i++) {
            for (int j = 0; j < pokemons.size(); j++) {
                if (allPokemonList.get(i).getId() == pokemons.get(j).getId()) {
                    pokemonList.add(pokemons.get(j));
                    hasInList = true;
                    break;
                }
            }
            if (!hasInList) {
                idsToDownload.add(allPokemonList.get(i).getId().toString());
            }
            hasInList = false;
        }
        return idsToDownload;
    }

    private void showData(ArrayList<Result> sortedPokemonList){
        res = new ResultAdapter(BestPokemonActivity.this, sortedPokemonList);
        bestPokeList.setAdapter( res );
    }

    private void getBestPokemonsByAttack(){
        ArrayList<Result> sortedPokemonList = new ArrayList<>();
        Collections.sort(pokemonList, new Comparator<Pokemon>() {
            public int compare(Pokemon p1, Pokemon p2) {
                return -(p1.getStats().get(4).getBase_stat().compareTo(p2.getStats().get(4).getBase_stat()));
            }
        });
        Integer count = 0;
        if (pokemonList.size() >= 6) {
            sumAtk = 0;
            sumDef = 0;
            sumSpd = 0;
            sumSpAtk = 0;
            sumSpDef = 0;
            sumHealthPoints = 0;
            sumBaseEXP = 0;
            sumWght = 0;
            for (int a = 0; a < 6; a++) {
                for (int i = 0; i < allPokemonList.size(); i++) {
                    if (allPokemonList.get(i).getId() == pokemonList.get(a).getId()) {
                        sumAtk += pokemonList.get(a).getStats().get(4).getBase_stat();
                        sumDef += pokemonList.get(a).getStats().get(3).getBase_stat();
                        sumSpd += pokemonList.get(a).getStats().get(0).getBase_stat();
                        sumSpAtk += pokemonList.get(a).getStats().get(2).getBase_stat();
                        sumSpDef += pokemonList.get(a).getStats().get(1).getBase_stat();
                        sumHealthPoints += pokemonList.get(a).getStats().get(5).getBase_stat();
                        sumBaseEXP += pokemonList.get(a).getBase_experience();
                        sumWght += pokemonList.get(a).getWeight();
                        sortedPokemonList.add(allPokemonList.get(i));
                    }
                }
            }
        }
        sumAttack.setText(sumAtk.toString());
        sumDefense.setText(sumDef.toString());
        sumSpeed.setText(sumSpd.toString());
        sumSAtk.setText(sumSpAtk.toString());
        sumSDef.setText(sumSpDef.toString());
        sumHp.setText(sumHealthPoints.toString());
        sumBaseXP.setText(sumBaseEXP.toString());
        sumWeight.setText(sumWght.toString());
        if (pokemonList.size() == allPokemonList.size()) {
            spinnerDialog.dismissLoadingDialog(this);
        }
        showData(sortedPokemonList);
    }
}
