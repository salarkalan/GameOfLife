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

        JButton simpleExampleButton = new JButton("Simple Example");
        simpleExampleButton.addActionListener(e -> setSimpleExample());
        buttonPanel.add(simpleExampleButton);

        JButton complexExampleButton = new JButton("Complex Example");
        complexExampleButton.addActionListener(e -> setComplexExample());
        buttonPanel.add(complexExampleButton);

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

    private void setSimpleExample() {
        restartGame();
        grid[1][2].setAlive(true);
        grid[2][3].setAlive(true);
        grid[3][1].setAlive(true);
        grid[3][2].setAlive(true);
        grid[3][3].setAlive(true);
        panel.repaint();
    }

    private void setComplexExample() {
        restartGame();
        // Gosper Glider Gun example
        grid[5][1].setAlive(true);
        grid[5][2].setAlive(true);
        grid[6][1].setAlive(true);
        grid[6][2].setAlive(true);

        grid[5][11].setAlive(true);
        grid[6][11].setAlive(true);
        grid[7][11].setAlive(true);
        grid[4][12].setAlive(true);
        grid[8][12].setAlive(true);
        grid[3][13].setAlive(true);
        grid[9][13].setAlive(true);
        grid[3][14].setAlive(true);
        grid[9][14].setAlive(true);
        grid[6][15].setAlive(true);
        grid[4][16].setAlive(true);
        grid[8][16].setAlive(true);
        grid[5][17].setAlive(true);
        grid[6][17].setAlive(true);
        grid[7][17].setAlive(true);
        grid[6][18].setAlive(true);

        grid[3][21].setAlive(true);
        grid[4][21].setAlive(true);
        grid[5][21].setAlive(true);
        grid[3][22].setAlive(true);
        grid[4][22].setAlive(true);
        grid[5][22].setAlive(true);
        grid[2][23].setAlive(true);
        grid[6][23].setAlive(true);
        grid[1][25].setAlive(true);
        grid[2][25].setAlive(true);
        grid[6][25].setAlive(true);
        grid[7][25].setAlive(true);

        grid[3][35].setAlive(true);
        grid[4][35].setAlive(true);
        grid[3][36].setAlive(true);
        grid[4][36].setAlive(true);

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
