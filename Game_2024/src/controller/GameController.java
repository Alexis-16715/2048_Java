package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import model.GameModel;
import view.GameView;

public class GameController implements KeyListener {
	
    private GameModel model;
    private GameView view;
    
    
    private JButton bottonNewGame;
    
    
    private int bestScore;
    

    public GameController(GameModel model, GameView view) {
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
	
	
	GameModel.Direction direction = null;
    switch (keyArrows) {
    	
        case KeyEvent.VK_UP:
        	
            direction = GameModel.Direction.UP;
            break;
        case KeyEvent.VK_DOWN:
            direction = GameModel.Direction.DOWN;
            break;
        case KeyEvent.VK_LEFT:
            direction = GameModel.Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            direction = GameModel.Direction.RIGHT;
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
        if (!model.isGameOver()) {
        	view.removeKeyListener(this);
        	view.getLabelOfVicotryOrDefeated().setText("You Lost... Try Again");
          }
       }
    }
	
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
		model = new GameModel();
		view.updateView(model.getGrid(), model.getScore(), bestScore);
		view.addKeyListener(this);
		view.requestFocusInWindow();
	}
		
		
		
}
