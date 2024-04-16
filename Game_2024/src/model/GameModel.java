package model;


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

        // Iterar sobre cada columna
        for (int col = 0; col < grid.length; col++) {
            int insertPos = 0;
            boolean canMerge = false;
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] != 0) {
                    if (canMerge && grid[insertPos - 1][col] == grid[row][col]) {
                        // Fusionarse con la celda anterior
                        grid[insertPos - 1][col] *= 2;
                        grid[row][col] = 0;
                        score += grid[insertPos - 1][col];
                        moved = true;
                        canMerge = false; // Evitar la fusión inmediata con el siguiente
                    } else {
                        // Pasar a la siguiente posición disponible
                        if (row != insertPos) {
                            grid[insertPos][col] = grid[row][col];
                            grid[row][col] = 0;
                            moved = true;
                        }
                        insertPos++;
                        canMerge = true; // Habilitar posible fusión con la siguiente
                    }
                }
            }
        }

        return moved;
    }

    
    
    
    private boolean moveDown() {
        boolean moved = false;

        for (int col = 0; col < grid.length; col++) {
            int insertPos = grid.length - 1;
            boolean canMerge = false;

            for (int row = grid.length - 1; row >= 0; row--) {
                if (grid[row][col] != 0) {
                    if (canMerge && grid[insertPos + 1][col] == grid[row][col]) {
                        grid[insertPos + 1][col] *= 2;
                        grid[row][col] = 0;
                        score += grid[insertPos + 1][col];
                        moved = true;
                        canMerge = false; 
                    } else {
                        if (row != insertPos) {
                            grid[insertPos][col] = grid[row][col];
                            grid[row][col] = 0;
                            moved = true;
                        }
                        insertPos--;
                        canMerge = true;
                    }
                }
            }
        }

        return moved;
    }


    private boolean moveLeft() {
        boolean moved = false;

        for (int row = 0; row < grid.length; row++) {
            int insertPos = 0;
            boolean canMerge = false;

            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] != 0) {
                    if (canMerge && grid[row][insertPos - 1] == grid[row][col]) {
                        grid[row][insertPos - 1] *= 2;
                        grid[row][col] = 0;
                        score += grid[row][insertPos - 1];
                        moved = true;
                        canMerge = false;
                    } else {
                        if (col != insertPos) {
                            grid[row][insertPos] = grid[row][col];
                            grid[row][col] = 0;
                            moved = true;
                        }
                        insertPos++;
                        canMerge = true;
                    }
                }
            }
        }

        return moved;
    }


    private boolean moveRight() {
        boolean moved = false;

        for (int row = 0; row < grid.length; row++) {
            int insertPos = grid[row].length - 1;
            boolean canMerge = false;

            for (int col = grid[row].length - 1; col >= 0; col--) {
                if (grid[row][col] != 0) {
                    if (canMerge && grid[row][insertPos + 1] == grid[row][col]) {
                        grid[row][insertPos + 1] *= 2;
                        grid[row][col] = 0;
                        score += grid[row][insertPos + 1];
                        moved = true;
                        canMerge = false;
                    } else {
                        if (col != insertPos) {
                            grid[row][insertPos] = grid[row][col];
                            grid[row][col] = 0;
                            moved = true;
                        }
                        insertPos--;
                        canMerge = true;
                    }
                }
            }
        }

        return moved;
    }

    
    private void addNewNumber() {
        int value = (random.nextDouble() < 0.9) ? 2 : 4; // 90%  para 2, 10% para 4
        // Coloque el nuevo número en una celda vacía.
        boolean addToGrid = true;
        while (addToGrid == true) {
            int row = random.nextInt(grid.length);
            int col = random.nextInt(grid.length);
            if (grid[row][col] == 0) {
                grid[row][col] = value;
                addToGrid = false;
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
		return grid;
	}

	public boolean isGameOver() {
		return canMove();
	}

}
