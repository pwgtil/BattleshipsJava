package battleship;

import java.util.Arrays;

public class PlayerState {
    public char[][] getBoard() {
        return board;
    }

    char[][] board;

    PlayerState(){
        board = new char[Board.boardSize][Board.boardSize];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '~');
        }
        int a = 5;
    }
}

