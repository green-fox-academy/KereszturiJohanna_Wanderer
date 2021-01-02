package com.wanderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Main extends JComponent implements KeyListener{

    public static Board board;

    public static void gameTable(Graphics graphics){
        PositionedImage image = new PositionedImage("floor.png", 300, 300);
        image.draw(graphics);

    }

    public static void generateLabyrinth(){
        board = new Board(12,12);
        RandomLabyrinthGenerator.createLabyrinth(board);
    }



    static int WIDTH = 864;  // vászon szélessége
    static int HEIGHT = 864; // vászon magassága

    public static void main(String[] args) {
        generateLabyrinth();
        JFrame jFrame = new JFrame("Wanderer");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImagePanel panel = new ImagePanel();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jFrame.add(panel);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.pack();

    }

    static class ImagePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            gameTable(graphics);
        }
    }

    public static void printBoardToConsole(Board board){
        for (Tile tile : board.getBoard()) {
            if(tile.isWall()){
                System.out.print("O");
            }else{
                System.out.print(" ");
            }
            System.out.print("|");
            if(tile.getId().y == board.getNumOfRows())
                System.out.println();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
