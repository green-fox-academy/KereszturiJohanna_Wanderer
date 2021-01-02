package com.wanderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Main extends JComponent implements KeyListener {

    public static Board board;
    static int WIDTH;
    static int HEIGHT;

    public static void gameTable(Graphics graphics) {
        printBoard(board, graphics);
    }

    public static void generateLabyrinth() {
        board = new Board(12, 12);
        WIDTH = board.getNumOfColumns()*72;
        HEIGHT = board.getNumOfRows()*72;
        RandomLabyrinthGenerator.createLabyrinth(board);
    }


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

    public static void printBoard(Board board, Graphics graphics) {
        int posX = 0;
        int posY = 0;
        for (Tile tile : board.getBoard()) {
            if (tile.isWall()) {
                PositionedImage image = new PositionedImage("wall.png", posX, posY);
                image.draw(graphics);
            } else {
                PositionedImage image = new PositionedImage("floor.png", posX, posY);
                image.draw(graphics);
            }
            posX += 72;
            if (tile.getId().y == board.getNumOfColumns()) {
                posY += 72;
                posX = 0;
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Húzni
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Ne engedd el
    }
}
