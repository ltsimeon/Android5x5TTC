package edu.quinnipiac.ser210.tictactoeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GameGridActivity extends AppCompatActivity {

    private String userName;
    private TicTacToe tttBackend;
    private GridView tttBoardVisual;
    private ArrayAdapter<String> boardHolder;
    private String[] board1D = new String[25];
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_grid);

        Intent intent = getIntent();
        userName = intent.getStringExtra("Name");
        TextView userNameBox = (TextView) findViewById(R.id.userTitle);
        userNameBox.setText(userName);

        tttBackend = new TicTacToe();
        if (tttBackend.getCurPlayer() == tttBackend.HUMAN_PLAYER) {
            Toast.makeText(getApplicationContext(), "You get to go first!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "The computer is going to go first.", Toast.LENGTH_SHORT).show();
            tttBackend.setMove(tttBackend.COMPUTER_PLAYER,tttBackend.getComputerMove());
        }
        tttBoardVisual = (GridView) findViewById(R.id.tttGrid);
        drawBoard();
        boardHolder = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, board1D);
        tttBoardVisual.setAdapter(boardHolder);
        tttBoardVisual.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (tttBackend.returnCell(position) == "_" && tttBackend.checkForWinner() == 0) { // if player made a valid tap and the game's not over...
                    tttBackend.setMove(tttBackend.HUMAN_PLAYER,position); // update their move onto the board.
                    int curGameState = tttBackend.checkForWinner();
                    if (curGameState == 0) { // if player didn't win/draw...
                        tttBackend.setMove(tttBackend.COMPUTER_PLAYER,tttBackend.getComputerMove()); // computer's turn.
                        if(tttBackend.checkForWinner() > 0) gameEnd(tttBackend.checkForWinner()); // if computer won or drew, end.
                    } else {
                        gameEnd(curGameState); // if player did win/draw, end.
                    }
                    drawBoard(); // and if both player's moves resolved and the game isn't over, redraw the board.
                } else Toast.makeText(getApplicationContext(),"That's not a valid move.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void drawBoard() {
        for (int i = 0; i<25; i++) {
            board1D[i] = tttBackend.returnCell(i);
        }
        tttBoardVisual.invalidateViews();
    }

    public void gameEnd(int finalGameState) {
        drawBoard();
        if (finalGameState == tttBackend.TIE) {
            Toast.makeText(getApplicationContext(),"The game was a tie. Feel free to play again.",Toast.LENGTH_LONG).show();
        }
        if (finalGameState == tttBackend.CROSS_WON) {
            Toast.makeText(getApplicationContext(),"Player X has won!",Toast.LENGTH_LONG).show();
        }
        if (finalGameState == tttBackend.NOUGHT_WON) {
            Toast.makeText(getApplicationContext(),"Player O has won!",Toast.LENGTH_LONG).show();
        }
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setEnabled(true);
    }

    public void onResetButtonClick(View v) {
        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setEnabled(false);
        tttBackend = new TicTacToe();
        if (tttBackend.getCurPlayer() == tttBackend.HUMAN_PLAYER) {
            Toast.makeText(getApplicationContext(), "You get to go first!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "The computer is going to go first.", Toast.LENGTH_LONG).show();
            tttBackend.setMove(tttBackend.COMPUTER_PLAYER,tttBackend.getComputerMove());
        }
        drawBoard();
    }
}
