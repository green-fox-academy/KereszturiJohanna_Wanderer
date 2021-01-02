package com.wanderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private ArrayList<Tile> board;
    private int numOfRows;
    private int numOfColumns;
    private int numOfTiles;

    public Board(int numOfRows, int numOfColumns) {
        this.numOfColumns = numOfColumns;
        this.numOfRows = numOfRows;
        numOfTiles = numOfRows * numOfColumns;
        this.board = new ArrayList<>();
        for (int i = 0; i < numOfTiles; i++) {
            board.add(new Tile(numOfRows, numOfColumns));
        }
    }

    public List<Tile> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public int getNumOfTiles() {
        return numOfTiles;
    }
}
