/*
 * The GameOfLifePanel Class Extends JPanel and handles the drawing of the grid.
 * It Uses Timer for periodic updates.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameOfLifePanel extends JPanel {
    private GameOfLife game;
    private final int CELL_SIZE = 10;

    // The main grid and the Mouse integration
    public GameOfLifePanel(GameOfLife game) {
        this.game = game;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                toggleCell(e.getX(), e.getY());
            }
        });
    }

    private void toggleCell(int x, int y) {
        int col = x / CELL_SIZE;
        int row = y / CELL_SIZE;
        if (row < game.getGrid().length && col < game.getGrid()[0].length) {
            game.getGrid()[row][col].setAlive(!game.getGrid()[row][col].isAlive());
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Cell[][] grid = game.getGrid();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].isAlive()) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        // Draw grid lines
        g.setColor(Color.BLACK);
        for (int i = 0; i <= grid.length; i++) {
            g.drawLine(0, i * CELL_SIZE, grid[0].length * CELL_SIZE, i * CELL_SIZE);
        }
        for (int j = 0; j <= grid[0].length; j++) {
            g.drawLine(j * CELL_SIZE, 0, j * CELL_SIZE, grid.length * CELL_SIZE);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(game.getGrid()[0].length * CELL_SIZE, game.getGrid().length * CELL_SIZE);
    }
}
