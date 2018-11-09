package com.example.reijn.tictactoe;

import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;
    private Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed;
    public Boolean gameOver;

    public Game() {

        // create the board
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        playerOneTurn = true;
        gameOver = false;
    }
    public TileState choose(int row, int column) {
        //check if the the tile is blank
        if(board[row][column] == TileState.BLANK){
            // if so check if it's player one his turn
            if(playerOneTurn){
                // change the player and add the symbol to the tile
                // add 1 to the moved plays for calculating draw
                playerOneTurn = false;
                board[row][column] = TileState.CROSS;
                movesPlayed++;
               return TileState.CROSS;
            }
            else{
                // change the player and add the symbol to the tile
                // add 1 to the moved plays for calculating draw
                playerOneTurn = true;
                board[row][column] = TileState.CIRCLE;
                movesPlayed++;
                return TileState.CIRCLE;
            }
        }
        else{
            // return invalid when it's not blank
            return TileState.INVALID;
        }

    }
    public GameState won(){
        // check if there are three symbols in a row for the winner
        for(int i=0; i<BOARD_SIZE; i++){
            if(board[i][0] == TileState.CIRCLE) {
                if(board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                    return GameState.PLAYER_TWO;
                }
            }else if(board[i][0] == TileState.CROSS){
                if(board[i][0] == board[i][1] && board[i][0] == board[i][2]){
                    return GameState.PLAYER_ONE;
                }
            }

            if(board[0][i] == TileState.CIRCLE) {
                if(board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                    return GameState.PLAYER_TWO;
                }
            }else if(board[0][i] == TileState.CROSS){
                if(board[0][i] == board[1][i] && board[0][i] == board[2][i]){
                    return GameState.PLAYER_ONE;
                }
            }
        }
        if(board[0][0] == TileState.CIRCLE) {
            if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
                return GameState.PLAYER_TWO;
            }
        }else if(board[0][0] == TileState.CROSS){
            if(board[0][0] == board[1][1] && board[0][0] == board[2][2]){
                return GameState.PLAYER_ONE;
            }
        }
        if(board[0][2] == TileState.CIRCLE) {
            if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
                return GameState.PLAYER_TWO;
            }
        }else if(board[0][2] == TileState.CROSS){
            if(board[0][2] == board[1][1] && board[0][2] == board[2][0]){
                return GameState.PLAYER_ONE;
            }
        }
        // check if it is a draw
        if(movesPlayed == 9){
            return GameState.DRAW;
        }

        // return in progress when nobody won and it is not a draw
        return GameState.IN_PROGRESS;
    }

}
