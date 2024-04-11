package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import model.Game2048Model;
import view.Game2048View;

public class Game2048Controller implements KeyListener {
	
    private Game2048Model model;
    private Game2048View view;
    
    
    private JButton bottonNewGame;
    
    
    private int bestScore;
    

    public Game2048Controller(Game2048Model model, Game2048View view) {
        this.model = model;
        this.view = view;
        attachListenersGame();
        model.setBestScore(bestScore);
        view.updateView(model.getGrid(), model.getScore(), model.getBestScore());
        view.addKeyListener(this);
    }
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {int keyArrows = e.getKeyCode();
	
	
	Game2048Model.Direction direction = null;
    switch (keyArrows) {
    	
        case KeyEvent.VK_UP:
            direction = Game2048Model.Direction.UP;
            break;
        case KeyEvent.VK_DOWN:
            direction = Game2048Model.Direction.DOWN;
            break;
        case KeyEvent.VK_LEFT:
            direction = Game2048Model.Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            direction = Game2048Model.Direction.RIGHT;
            break;
        default:
			break;

        
    }
    
    if (direction != null) {
        model.move(direction);
        if(model.getScore() > bestScore) {
        	bestScore = model.getScore();
        }
        
        
        view.updateView(model.getGrid(), model.getScore(), bestScore);
        // Check if the game is over
        if (model.isGameOver()) {
        	
        	
        }
    }}
	
	public void keyReleased(KeyEvent e) {}
	
	
	private void attachListenersGame() {
		bottonNewGame = view.getBottonNewGame();
		
		bottonNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }

        });
	}
	
	private void startNewGame () {
		// TODO Auto-generated method stub
		
		model = new Game2048Model();
		view.updateView(model.getGrid(), model.getScore(), bestScore);
		view.addKeyListener(this);
		view.requestFocusInWindow();
	}
		
		
		
}

