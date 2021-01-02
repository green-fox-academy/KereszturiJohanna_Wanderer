package com.wanderer;

import com.sun.corba.se.spi.transport.CorbaAcceptor;

import java.util.Arrays;

public class Tile {

    final Coordinates id;
    final Coordinates top;
    final Coordinates down;
    final Coordinates left;
    final Coordinates right;
   private boolean isWall;
   private boolean isNothing;
   private final boolean isEdge;
    static int[] coordinateGiver = {1, 1};

    public Tile( int numOfRows, int numOfColumns) {
        id = new Coordinates(coordinateGiver[0],coordinateGiver[1] );
        if (coordinateGiver[1] < numOfRows) {
            coordinateGiver[1]++;
        } else if (coordinateGiver[0] < numOfColumns) {
            coordinateGiver[0]++;
            coordinateGiver[1] = 1;
        }
        if (id.x == 1 || id.y == 1 || id.x == numOfColumns || id.y == numOfRows){
            isEdge = true;
            isWall = true;
//            if(id.y == numOfRows){
//                isWall = false;
//            }else{
//                isWall = true;
//            }
            isNothing = false;
            top = new Coordinates(0, 0);
            down = new Coordinates(0,0);
            left = new Coordinates(0,0);
            right = new Coordinates(0,0);
        } else {
            isEdge = false;
            isWall = false;
            top = new Coordinates(id.x, id.y-1);
            down = new Coordinates(id.x, id.y+1);
            right = new Coordinates(id.x+1, id.y);
            left = new Coordinates(id.x-1, id.y);
            isNothing = true;
        }


    }

    public void setWall(boolean wall) {
        if(!isEdge){
            isWall = wall;
        }
    }

    public void setNothing(boolean nothing) {
        isNothing = nothing;
    }


    public boolean isWall() {
        return isWall;
    }

    public boolean isEdge() {
        return isEdge;
    }

    public boolean isNothing() {
        return isNothing;
    }

    public Coordinates getId() {
        return id;
    }

    public Coordinates getTop() {
        return top;
    }

    public Coordinates getDown() {
        return down;
    }

    public Coordinates getLeft() {
        return left;
    }

    public Coordinates getRight() {
        return right;
    }

    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", isWall=" + isWall +
                ", isNothing=" + isNothing +
                '}';
    }
}
