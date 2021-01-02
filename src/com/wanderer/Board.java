package com.wanderer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private ArrayList<Tile> board;
    private int numOfColumns;
    private int numOfRows;
    private int numOfTiles;

    public Board(int numOfColumns, int numOfRows) {
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        numOfTiles = numOfColumns * numOfRows;
        this.board = new ArrayList<>();
        for (int i = 0; i < numOfTiles; i++) {
            board.add(new Tile(numOfRows, numOfColumns));
        }
        System.out.println();
    }

    public List<Tile> getBoard() {
        return Collections.unmodifiableList(board);
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfTiles() {
        return numOfTiles;
    }
}
