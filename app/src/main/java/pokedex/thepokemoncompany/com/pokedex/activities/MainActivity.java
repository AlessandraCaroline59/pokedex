package pokedex.thepokemoncompany.com.pokedex.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pokedex.thepokemoncompany.com.pokedex.R;

public class MainActivity extends AppCompatActivity {

    private ImageView allPokemons;
    private ImageView myPokemons;
    private ImageView strongPokemons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allPokemons = (ImageView) findViewById(R.id.AllPokemonsId);
        myPokemons = (ImageView) findViewById(R.id.MyPokemonsId);
        strongPokemons = (ImageView) findViewById(R.id.StrongestPokemonsId);

        allPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PokemonActivity.class));
            }
        });

        myPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TrainerActivity.class));
            }
        });

        strongPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BestPokemonActivity.class));
            }
        });



    }


}
