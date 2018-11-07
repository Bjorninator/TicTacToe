package com.example.reijn.tictactoe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;

    Game game;
    TileState state;
    GameState gamestate = GameState.IN_PROGRESS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button11);
        button2 = (Button) findViewById(R.id.button12);
        button3 = (Button) findViewById(R.id.button13);
        button4 = (Button) findViewById(R.id.button14);
        button5 = (Button) findViewById(R.id.button15);
        button6 = (Button) findViewById(R.id.button16);
        button7 = (Button) findViewById(R.id.button17);
        button8 = (Button) findViewById(R.id.button18);
        button9 = (Button) findViewById(R.id.button19);
        game = new Game();

    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("Game", game);
        outState.putString("button1", button1.getText().toString());
        outState.putString("button2", button2.getText().toString());
        outState.putString("button3", button3.getText().toString());
        outState.putString("button4", button4.getText().toString());
        outState.putString("button5", button5.getText().toString());
        outState.putString("button6", button6.getText().toString());
        outState.putString("button7", button7.getText().toString());
        outState.putString("button8", button8.getText().toString());
        outState.putString("button9", button9.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);
        button1.setText(inState.getString("button1"));
        button2.setText(inState.getString("button2"));
        button3.setText(inState.getString("button3"));
        button4.setText(inState.getString("button4"));
        button5.setText(inState.getString("button5"));
        button6.setText(inState.getString("button6"));
        button7.setText(inState.getString("button7"));
        button8.setText(inState.getString("button8"));
        button9.setText(inState.getString("button9"));
        game = (Game) inState.getSerializable("Game");
        if(game.gameOver == true){
            for(int i=2131165220; i <2131165229; i++ ){
                Button disable = (Button) findViewById(i);
                disable.setEnabled(false);
            }
        }
    }

    public void tileClicked(View view) {
        int id = view.getId();
        System.out.println(id);
        switch (id) {
            case 2131165223:  state = game.choose(2, 2);
                break;
            case 2131165220:  state = game.choose(2, 1);
                break;
            case 2131165226:  state = game.choose(2, 0);
                break;
            case 2131165222:  state = game.choose(1, 2);
                break;
            case 2131165224:  state = game.choose(1, 1);
                break;
            case 2131165227:  state = game.choose(1, 0);
                break;
            case 2131165221:  state = game.choose(0, 2);
                break;
            case 2131165225:  state = game.choose(0, 1);
                break;
            case 2131165228:  state = game.choose(0, 0);
                break;
        }
        Button button = (Button) findViewById(id);
        switch(state) {
            case CROSS:
                button.setText("X");
                gamestate = game.won();
                break;
            case CIRCLE:
                button.setText("O");
                gamestate = game.won();
                break;
            case INVALID:
                // do something different
                break;
        }

        if(gamestate != GameState.IN_PROGRESS) {
            victory();
            game.gameOver = true;
            for(int i=2131165220; i <2131165229; i++ ){
                Button disable = (Button) findViewById(i);
                disable.setEnabled(false);
            }
        }
    }

    public void victory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Nice");
        if (gamestate == GameState.PLAYER_ONE) {
            builder.setMessage("the first player wins the game");
        } else if (gamestate == GameState.PLAYER_TWO) {
            builder.setMessage("the second player wins the game");
        }else{
            builder.setMessage("It's a draw!");
        }
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    public void resetClicked(View view) {
        for(int i=2131165220; i <2131165229; i++ ){
            Button button = (Button) findViewById(i);
            button.setText("");
            button.setEnabled(true);
        }
        game = new Game();
    }
}
