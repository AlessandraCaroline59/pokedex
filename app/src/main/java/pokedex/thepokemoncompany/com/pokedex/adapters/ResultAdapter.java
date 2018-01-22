package pokedex.thepokemoncompany.com.pokedex.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.interfaces.PokemonService;
import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.Result;
import pokedex.thepokemoncompany.com.pokedex.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alessandra on 21/01/2018.
 */

public class ResultAdapter extends ArrayAdapter<Result> {

    private Context context;
    private ArrayList<Result> dataset = new ArrayList<>();
    private Pokemon pokemon;
    Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).
            addConverterFactory(GsonConverterFactory.create()).build();

    public ResultAdapter(Context context, ArrayList<Result> results) {
        super(context, 0, results);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Result result = dataset.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlistview, parent, false);
        }

        TextView pokemonNumber = (TextView) convertView.findViewById(R.id.pokeId);
        TextView pokemonName = (TextView) convertView.findViewById(R.id.nameId);
        ImageView pokemonImage = (ImageView) convertView.findViewById(R.id.imageId);

        pokemonNumber.setText("#000" + result.getId().toString());
        pokemonName.setText(result.getName());
        Glide.with(context).load("https://pokeapi.co/media/sprites/pokemon/" + result.getId().toString() + ".png").centerCrop().crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(pokemonImage);

        showDetails(convertView, result);

        return convertView;
    }

    @Override
    public int getCount(){
        return dataset.size();
    }

    public void addPokemonList(ArrayList<Result> allPokemonList) {
        dataset.addAll(allPokemonList);
        notifyDataSetChanged();
    }


    private void showDetails(View convertView, final Result result){
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    PokemonService service = retrofit.create(PokemonService.class);
                    Call<Pokemon> detailCall = service.getPokemonDetail(result.getId().toString());

                    detailCall.enqueue(new Callback<Pokemon>() {
                        @Override
                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                            if(response.isSuccessful()){
                                pokemon = response.body();
                                Log.e(Constants.TAG, "Valores: " + pokemon.getName());
                            }else{
                                Log.e(Constants.TAG, "onResponse: " + response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Pokemon> call, Throwable t) {
                            Log.e(Constants.TAG, "onFailure: " + t.getMessage());
                        }
                    });
                }
        });
    }
}
