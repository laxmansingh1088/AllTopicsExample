package com.example.test.alltopicsexample.tiktoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.alltopicsexample.R;
import com.example.test.alltopicsexample.utils.BaseActivity;

public class TikToeGameActivity extends BaseActivity {

    private Button buttons[][] = new Button[3][3];
    private boolean player1turn = true;

    private int roundCount = 0;

    private int player1Points;
    private int player2Points;


    private TextView mTvPlayer1;
    private TextView mTvPlayer2;

    private Button mBtnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tiktoe_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Tic Tac Toe");
        onFindView();
        onInView();
        onBindView();
    }


    @Override
    protected void onFindView() {
        mBtnReset = findViewById(R.id.button_reset);
        mTvPlayer1 = findViewById(R.id.text_view_p1);
        mTvPlayer2 = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onInView() {

    }

    @Override
    protected void onBindView() {


        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (!((Button) v).getText().toString().trim().equalsIgnoreCase(""))
            return;


        if (player1turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;


        if (checkForWin()) {
            if (player1turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            gameDraw();
        } else {
            player1turn = !player1turn;
        }


    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }


        for (int j = 0; j < 3; j++) {
            if (field[0][j].equals(field[1][j])
                    && field[0][j].equals(field[2][j])
                    && !field[0][j].equals("")) {
                return true;
            }
        }


        if ((field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals(""))
                || (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals(""))) {
            return true;
        }

        return false;

    }

    private void gameDraw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        player1turn = true;
        roundCount = 0;
    }

    private void player1Wins() {
        Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_LONG).show();
        player1Points++;
        updatePlayersPointBoard();
        resetBoard();
    }

    private void player2Wins() {
        Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_LONG).show();
        player2Points++;
        updatePlayersPointBoard();
        resetBoard();

    }


    private void updatePlayersPointBoard() {
        mTvPlayer1.setText("Player 1 points:--  " + String.valueOf(player1Points));
        mTvPlayer2.setText("Player 2 points:--  " + String.valueOf(player2Points));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1turn = savedInstanceState.getBoolean("player1Turn");

        updatePlayersPointBoard();
    }


    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePlayersPointBoard();
        resetBoard();
    }
}
