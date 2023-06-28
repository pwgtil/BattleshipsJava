package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Board {

    //--STATICS---------------------------------------------------------------------------------------------------------
    //--Setting up mappings-------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    static final int boardSize = 10;

    public static final HashMap<Integer, String> rowInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(i, String.valueOf((char) (i + 'A')));
            }
        }
    };
    public static final HashMap<Integer, String> colInt2Str = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(i, String.valueOf(i + 1));
            }
        }
    };

    public static final HashMap<String, Integer> rowStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(String.valueOf((char) (i + 'A')), i);
            }
        }
    };
    public static final HashMap<String, Integer> colStr2Int = new HashMap<>() {
        {
            for (int i = 0; i < boardSize; i++) {
                put(String.valueOf(i + 1), i);
            }
        }
    };

    //-----------------------------------------------------------------------------------------------------------------
    //--Main stuff -------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    ArrayList<Area> ships;

    Board(){
    }



}
