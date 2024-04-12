package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameModel {
	
	private int[][] grid;
    private Random random;
    private int score;
    private int bestScore;

	public GameModel() {
        grid = new int[4][4];
        random = new Random();
        addNewNumber();
        addNewNumber();
    }	
	
	public void move(Direction direction) {
        boolean moved = false;

        switch (direction) {
            case UP:
                moved = moveUp();
                break;
            case DOWN:
                moved = moveDown();
                break;
            case LEFT:
                moved = moveLeft();
                break;
            case RIGHT:
                moved = moveRight();
                break;
        }
        
        
        

        if (moved) {
        	addNewNumber();
        }
    }
	
	
	// Enum para las direcciones de movimiento
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
	
	

	private boolean moveUp() {
        boolean moved = false;

        // Mover números hacia arriba para cada columna
        for (int col = 0; col < grid.length; col++) {
            int insertPos = 0;
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] != 0) {
                    if (row != insertPos) {
                        // Move the number to insertPos
                        grid[insertPos][col] = grid[row][col];
                        grid[row][col] = 0;
                        moved = true;
                    }
                    insertPos++;
                }
            }

            for (int row = 0; row < grid.length - 1; row++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row + 1][col]) {
                    // Merge the numbers
                    grid[row][col] *= 2;
                    grid[row + 1][col] = 0;
                    score += grid[row][col]; // Update score
                    moved = true;
                }
            }

            insertPos = 0;
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] != 0) {
                    if (row != insertPos) {
                        // Move the number to insertPos after merging
                        grid[insertPos][col] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    insertPos++;
                }
            }
        }

        return moved;
    }
    
    private boolean moveDown() {
        boolean moved = false;

        //Mover números hacia abajo para cada columna
        for (int col = 0; col < grid.length; col++) {
            // Comprimir números hasta el fondo
            int insertPos = grid.length - 1;
            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][col] != 0) {
                    if (row != insertPos) {
                        grid[insertPos][col] = grid[row][col];
                        grid[row][col] = 0;
                        moved = true;
                    }
                    insertPos--;
                }
            }

            // Merge adjacent identical numbers
            for (int row = grid.length - 1; row > 0; row--) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row - 1][col]) {
                    // Merge the numbers
                    grid[row][col] *= 2;
                    grid[row - 1][col] = 0;
                    score += grid[row][col]; // Actualiza la score
                    moved = true;
                }
            }

            // Compress numbers to the bottom after merging
            insertPos = grid.length - 1;
            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][col] != 0) {
                    if (row != insertPos) {
                        // Move the number to insertPos after merging
                        grid[insertPos][col] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    insertPos--;
                }
            }
        }

        return moved;
    }

    private boolean moveLeft() {
        boolean moved = false;

        // Mover números hacia izquierda para cada columna
        for (int row = 0; row < grid.length; row++) {
            // Compress numbers to the left
            int insertPos = 0;
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] != 0) {
                    if (col != insertPos) {
                        // Move the number to insertPos
                        grid[row][insertPos] = grid[row][col];
                        grid[row][col] = 0;
                        moved = true;
                    }
                    insertPos++;
                }
            }

            // Merge adjacent identical numbers
            for (int col = 0; col < grid.length - 1; col++) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row][col + 1]) {
                    // Merge the numbers
                    grid[row][col] *= 2;
                    grid[row][col + 1] = 0;
                    score += grid[row][col]; // Actualiza la score
                    moved = true;
                }
            }

            // Comprimir números hacia la izquierda después de fusionarlos
            insertPos = 0;
            for (int col = 0; col < grid.length; col++) {
                if (grid[row][col] != 0) {
                    if (col != insertPos) {
                        // Mueva el número para insertarPos después de fusionar
                        grid[row][insertPos] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    insertPos++;
                }
            }
        }

        return moved;
    }

    private boolean moveRight() {
        boolean moved = false;

        // Mover números hacia derecha para cada columna
        for (int row = 0; row < grid.length; row++) {
            // Compress numbers to the right
            int insertPos = grid.length - 1;
            for (int col = grid.length - 1; col >= 0; col--) {
                if (grid[row][col] != 0) {
                    if (col != insertPos) {
                        // Move the number to insertPos
                        grid[row][insertPos] = grid[row][col];
                        grid[row][col] = 0;
                        moved = true;
                    }
                    insertPos--;
                }
            }

            for (int col = grid.length - 1; col > 0; col--) {
                if (grid[row][col] != 0 && grid[row][col] == grid[row][col - 1]) {
                    // Merge the numbers
                    grid[row][col] *= 2;
                    grid[row][col - 1] = 0;
                    score += grid[row][col]; // Actualiza la score
                    moved = true;
                }
            }

            // Comprimir números a la derecha después de fusionarlos
            insertPos = grid.length - 1;
            for (int col = grid.length - 1; col >= 0; col--) {
                if (grid[row][col] != 0) {
                    if (col != insertPos) {
                        grid[row][insertPos] = grid[row][col];
                        grid[row][col] = 0;
                    }
                    insertPos--;
                }
            }
        }

        return moved;
    }
    
    private void addNewNumber() {
        int value = (random.nextDouble() < 0.9) ? 2 : 4; // 90% chance for 2, 10% chance for 4
        // Place the new number in an empty cell
        while (true) {
            int row = random.nextInt(grid.length);
            int col = random.nextInt(grid.length);
            if (grid[row][col] == 0) {
                grid[row][col] = value;
                break;
            }
        }
    }
	
	
    private boolean canMove() {
        // Comprobar si hay posibles movimientos
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    return true; // There is at least one empty cell
                }
                if (j < grid.length - 1 && grid[i][j] == grid[i][j + 1]) {
                    return true; // Hay al menos dos celdas adyacentes con el mismo valor
                }
                if (i < grid.length - 1 && grid[i][j] == grid[i + 1][j]) {
                    return true; // Hay al menos dos celdas adyacentes con el mismo valor
                }
            }
        }
        
        return false;
    }
    
    
	
	public int getScore() {
		return score;
	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}


	public int[][] getGrid() {
		// TODO Auto-generated method stub
		return grid;
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return canMove();
	}

}
