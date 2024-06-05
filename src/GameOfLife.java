/*
 * The GameOfLife Class Contains the grid of cells.
 * It handles initialization, updates, and display of the grid.
 * and implements the rules of the Game of Life.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameOfLife extends JFrame {
    private int rows;
    private int cols;
    private Cell[][] grid;
    private Timer timer;
    private GameOfLifePanel panel;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        initializeGrid();
        initializeUI();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(false);
            }
        }
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                    if (grid[newRow][newCol].isAlive()) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j);
                grid[i][j].determineNextState(aliveNeighbors);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j].updateState();
            }
        }
    }

    private void initializeUI() {
        setTitle("Game of Life");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new GameOfLifePanel(this);
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> startEvolution());
        buttonPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopEvolution());
        buttonPanel.add(stopButton);

        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        buttonPanel.add(restartButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void startEvolution() {
        timer = new Timer(100, e -> {
            updateGrid();
            panel.repaint();
        });
        timer.start();
    }

    private void stopEvolution() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void restartGame() {
        stopEvolution();
        initializeGrid();
        panel.repaint();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameOfLife game = new GameOfLife(64, 64);
            game.setVisible(true);
        });
    }
}
