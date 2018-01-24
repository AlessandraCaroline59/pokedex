package pokedex.thepokemoncompany.com.pokedex.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import pokedex.thepokemoncompany.com.pokedex.R;

public class TrainerActivity extends AppCompatActivity {

    private TextView myAttack;
    private TextView myDefense;
    private TextView mySpeed;
    private TextView mySAtk;
    private TextView mySDef;
    private TextView myHp;
    private TextView myBaseXP;
    private TextView myWeight;
    private ListView myPokeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer);

        myAttack = (TextView) findViewById(R.id.myAttackId);
        myDefense = (TextView) findViewById(R.id.myDefenseId);
        mySpeed = (TextView) findViewById(R.id.mySpeedId);
        mySAtk = (TextView) findViewById(R.id.mySatkId);
        mySDef = (TextView) findViewById(R.id.mySdefId);
        myHp = (TextView) findViewById(R.id.myHpId);
        myBaseXP = (TextView) findViewById(R.id.myBaseXPId);
        myWeight = (TextView) findViewById(R.id.myPokemonWeightId);
        myPokeList = (ListView) findViewById(R.id.myPokeListId);
    }
}
