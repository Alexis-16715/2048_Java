package main;

import javax.swing.SwingUtilities;

import controller.GameController;
import view.GameView;
import model.GameModel;
public class Main {
	
	
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        	GameView game2048View = new GameView();
        	
        	GameModel model = new GameModel();
            GameController controller = new GameController(model, game2048View);

            // Agregue un KeyListener a la vista para manejar las pulsaciones de teclas
            game2048View.addKeyListener(controller);
            
            
            game2048View.setVisible(true);
        	
        });
    }

}
