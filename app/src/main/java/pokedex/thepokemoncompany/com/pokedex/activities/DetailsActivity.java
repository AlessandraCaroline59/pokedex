package pokedex.thepokemoncompany.com.pokedex.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;

import pokedex.thepokemoncompany.com.pokedex.R;
import pokedex.thepokemoncompany.com.pokedex.models.Pokemon;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonAbility;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonMove;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonStat;
import pokedex.thepokemoncompany.com.pokedex.models.PokemonType;
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
    private TextView type1;
    private TextView type2;
    private TextView ability1;
    private TextView ability2;
    private TextView ability3;
    private ListView movesList;


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
        type1 = (TextView) findViewById(R.id.type1Id);
        type2 = (TextView) findViewById(R.id.type2Id);
        ability1 = (TextView) findViewById(R.id.ability1Id);
        ability2 = (TextView) findViewById(R.id.ability2Id);
        ability3 = (TextView) findViewById(R.id.ability3Id);
        movesList = (ListView) findViewById(R.id.movesListViewId);


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

            ArrayList<PokemonType> pokemonTypes = pokemon.getTypes();
            for (int i = 0; i < pokemonTypes.size(); i++) {
                switch (pokemonTypes.get(i).getSlot()) {
                    case 1:
                        if (pokemonTypes.get(i) != null) {
                            type1.setText(pokemonTypes.get(i).getType().getName());
                        }
                        break;
                    case 2:
                        if (pokemonTypes.get(i) != null) {
                            type2.setText(pokemonTypes.get(i).getType().getName());
                        }
                        break;
                    default:
                        break;
                }
            }

            ArrayList<PokemonAbility> abilities = pokemon.getAbilities();
            for (int i = 0; i < abilities.size(); i++) {
                switch (abilities.get(i).getSlot()) {
                    case 1:
                        if (abilities.get(i) != null) {
                            ability1.setText(abilities.get(i).getAbility().getName());
                        }
                        break;
                    case 2:
                        if (abilities.get(i) != null) {
                            ability2.setText(abilities.get(i).getAbility().getName());
                        }
                    case 3:
                        if (abilities.get(i) != null) {
                            ability3.setText(abilities.get(i).getAbility().getName());
                        }
                        break;
                    default:
                        break;
                }
            }

            ArrayList<PokemonMove> pokemonMove = pokemon.getMoves();
            ArrayList<String> pokeMove = new ArrayList<>();
            for (int i = 0; i < pokemonMove.size(); i++) {
               pokeMove.add(pokemonMove.get(i).getMove().getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    getApplicationContext(),
                    R.layout.custom_list_view_text_color,
                    R.id.movesId,
                    pokeMove);

            movesList.setAdapter( adapter );
        }
    }
}
