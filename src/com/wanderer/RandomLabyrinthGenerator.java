package com.wanderer;

import java.util.ArrayList;

public class RandomLabyrinthGenerator {

    public static Board createLabyrinth(Board board) {
        int half = calculateHalf(board);
        int first = board.getNumOfRows() + 1;

        try {

            Tile firstRight = findNeighbor(board.getBoard().get(first).getRight(), board);
            Tile firstDown = findNeighbor(board.getBoard().get(first).getDown(), board);
            board.getBoard().get(first).setNothing(false);
            generateFirstTiles(firstRight, firstDown);


            for (int i = first + 1; i < board.getBoard().size() - first; i++) {
                if (board.getBoard().get(i).isWall()) {
                    continue;
                }

                int numOfWalls = countWalls(board.getBoard().get(i), board);
                int numOfOccupied = countOccupied(board.getBoard().get(i), board);
                Tile right = findNeighbor(board.getBoard().get(i).getRight(), board);
                Tile down = findNeighbor(board.getBoard().get(i).getDown(), board);

                board.getBoard().get(i).setNothing(false);
                if (numOfWalls == 4) {
                    board.getBoard().get(i).setWall(true);
                    board.getBoard().get(i).setNothing(false);
                } else if (numOfWalls == 3) {
                    if (right.isWall()) {
                        down.setNothing(false);
                        down.setWall(false);
                    } else {
                        right.setWall(false);
                        right.setNothing(false);
                    }
                }else{
                    if (board.getBoard().get(i).getId().x < half) {

                        generateFirstHalf(board, numOfWalls, numOfOccupied, right, down, i);

                    } else {

                        generateSecondHalf(board, numOfWalls, numOfOccupied, right, down, i);
                    }
                }


            }
        } catch (NeighbourNotFoundException e) {
            System.out.println("Damn....");
            System.out.println(e.getMessage());
        }

        return board;
    }

    static int calculateHalf(Board board){
        if (board.getBoard().size() / 2 == 0) {
            return board.getNumOfColumns()/2;
        } else {
            return board.getNumOfColumns() / 2 + 1;
        }
    }

    static void generateFirstTiles(Tile right, Tile down){

        if (right.isNothing() || down.isNothing()) {
            right.setWall(generate());
            right.setNothing(false);
            down.setNothing(false);
        }
        if (right.isWall()) {
            down.setWall(false);

        } else {
            down.setWall(generate());
        }

    }

    static void generateFirstHalf(Board board, int numOfWalls, int numOfOccupied, Tile right, Tile down, int i){

        if (numOfWalls == 2) {
            if (numOfOccupied == 2) {
                right.setNothing(false);
                right.setWall(false);
                down.setNothing(false);
                down.setWall(false);
            } else if (numOfOccupied == 3) {
                if (right.isWall()) {
                    down.setNothing(false);
                    down.setWall(false);
                } else if (down.isWall()) {
                    right.setWall(false);
                    right.setNothing(false);
                } else {
                    right.setWall(generate());
                    right.setNothing(false);
                }
            }
        } else if (numOfWalls == 1) {
            if (numOfOccupied == 3) {
                if (right.isWall()) {
                    down.setWall(generate());
                    down.setNothing(false);
                } else {
                    right.setWall(generate());
                    right.setNothing(false);
                }
            } else if (numOfOccupied == 2) {
                right.setWall(generate());
                right.setNothing(false);
                down.setNothing(false);
                if (right.isWall()) {
                    down.setWall(false);
                } else {
                    down.setWall(generate());
                }
            }
        } else if (numOfWalls == 0) {
            if (numOfOccupied == 2) {
                right.setWall(generate());
                right.setNothing(false);
                down.setNothing(false);
                if (right.isWall()) {
                    down.setWall(generate());
                } else {
                    down.setWall(true);
                }
            } else if (numOfOccupied == 3) {
                right.setWall(generate());
                right.setNothing(false);
            }
        }

    }

    static void generateSecondHalf(Board board, int numOfWalls, int numOfOccupied, Tile right, Tile down, int i){

        if (numOfWalls == 2) {
            if (numOfOccupied == 2) {
                right.setNothing(false);
                right.setWall(false);
                down.setNothing(false);
                down.setWall(false);
            } else if (numOfOccupied == 3) {
                if(right.isWall()){
                    down.setWall(false);
                    down.setNothing(false);
                }else{
                    right.setWall(false);
                    right.setNothing(false);
                }
            }
        } else if (numOfWalls == 1) {
            if (numOfOccupied == 3) {
                if (right.isWall()) {
                    down.setWall(false);
                    down.setNothing(false);
                } else if (down.isWall()) {
                    right.setWall(false);
                    right.setNothing(false);
                } else {
                    right.setWall(generate());
                    right.setNothing(false);
                }
            } else if (numOfOccupied == 2) {
                right.setWall(generate());
                right.setNothing(false);
                down.setNothing(false);
                if (right.isWall()) {
                    down.setWall(false);
                } else {
                    down.setWall(generate());
                }
            }
        } else if (numOfWalls == 0) {
            if (numOfOccupied == 2) {
                right.setWall(generate());
                right.setNothing(false);
                down.setNothing(false);
                if (right.isWall()) {
                    down.setWall(false);
                } else {
                    down.setWall(generate());
                }
            } else if (numOfOccupied == 3) {
                right.setWall(true);
                right.setNothing(false);
            }
        }
    }

    static Tile findNeighbor(Coordinates current, Board board) throws NeighbourNotFoundException {
        for (int i = 0; i < board.getBoard().size(); i++) {
            if (current.equals(board.getBoard().get(i).getId())) {
                return board.getBoard().get(i);
            }
        }

        throw new NeighbourNotFoundException(current.toString());
    }

    static boolean generate() {
        double random = (Math.random());
        if (random > 0.5) {
            return true;
        } else {
            return false;
        }
    }

    static int countWalls(Tile tile, Board board) throws NeighbourNotFoundException {
        int counter = 0;
        if (findNeighbor(tile.getTop(), board).isWall()) {
            counter++;
        }
        if (findNeighbor(tile.getLeft(), board).isWall()) {
            counter++;
        }
        if (findNeighbor(tile.getRight(), board).isWall()) {
            counter++;
        }
        if (findNeighbor(tile.getDown(), board).isWall()) {
            counter++;
        }
        return counter;
    }

    static int countOccupied(Tile tile, Board board) throws NeighbourNotFoundException {
        int counter = 0;
        if (!findNeighbor(tile.getTop(), board).isNothing()) {
            counter++;
        }
        if (!findNeighbor(tile.getLeft(), board).isNothing()) {
            counter++;
        }
        if (!findNeighbor(tile.getRight(), board).isNothing()) {
            counter++;
        }
        if (!findNeighbor(tile.getDown(), board).isNothing()) {
            counter++;
        }
        return counter;
    }
}
