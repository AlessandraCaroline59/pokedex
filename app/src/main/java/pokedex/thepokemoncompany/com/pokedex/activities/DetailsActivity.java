package pokedex.thepokemoncompany.com.pokedex.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonStat;
import pokedex.thepokemoncompany.com.pokedex.models.Stat;

public class DetailsActivity extends AppCompatActivity {

    private TextView pokemonId;
    private TextView pokemonName;
    private TextView pokemonHeight;
    private TextView pokemonWeight;
    private TextView baseXP;
    private TextView attack;
    private TextView defense;
    private TextView speed;
    private TextView spAttack;
    private TextView spDefense;
    private TextView hp;
    private ImageView pokemonImage;
    private RelativeLayout background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        RelativeLayout background = (RelativeLayout) findViewById(R.id.background_layout);

        pokemonId = (TextView) findViewById(R.id.pokemonId);
        pokemonName = (TextView) findViewById(R.id.pokemonNameId);
        pokemonHeight = (TextView) findViewById(R.id.pokemonHeightId);
        pokemonWeight = (TextView) findViewById(R.id.pokemonWeightId);
        baseXP = (TextView) findViewById(R.id.baseId);
        attack = (TextView) findViewById(R.id.attackId);
        defense = (TextView) findViewById(R.id.defenseId);
        speed = (TextView) findViewById(R.id.speedId);
        spAttack = (TextView) findViewById(R.id.satkId);
        spDefense = (TextView) findViewById(R.id.sdefId);
        hp = (TextView) findViewById(R.id.hpId);
        pokemonImage = (ImageView) findViewById(R.id.pokemonImageId);

        Bundle extra = getIntent().getExtras();


        if(extra != null){
            Pokemon pokemon = (Pokemon) extra.getSerializable("pokemon");
            pokemonId.setText("#000" + pokemon.getId().toString());
            pokemonName.setText(pokemon.getName().toUpperCase());
            pokemonHeight.setText(pokemon.getHeight().toString());
            pokemonWeight.setText(pokemon.getWeight().toString());
            baseXP.setText(pokemon.getBase_experience().toString());
            ArrayList<PokemonStat> stats = pokemon.getStats();
            for (int i = 0; i < stats.size(); i++){
                switch (stats.get(i).getStat().getName()) {
                    case "attack":
                        attack.setText(stats.get(i).getBase_stat().toString());
                        break;
                    case "defense":
                        defense.setText(stats.get(i).getBase_stat().toString());
                        break;
                    case "speed":
                        speed.setText(stats.get(i).getBase_stat().toString());
                        break;
                    case "special-attack":
                        spAttack.setText(stats.get(i).getBase_stat().toString());
                        break;
                    case "special-defense":
                        spDefense.setText(stats.get(i).getBase_stat().toString());
                        break;
                    case "hp":
                        hp.setText(stats.get(i).getBase_stat().toString());
                        break;
                    default:
                        break;
                }
            }
            Glide.with(this)
                    .load(pokemon.getSprites().getFront_default())
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pokemonImage);


        }
    }
}
