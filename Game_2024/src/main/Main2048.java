package main;

import javax.swing.SwingUtilities;

import controller.Game2048Controller;
import view.Game2048View;
import model.Game2048Model;
public class Main2048 {
	
	
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

        	Game2048View game2048View = new Game2048View();
        	
        	Game2048Model model = new Game2048Model();
            Game2048Controller controller = new Game2048Controller(model, game2048View);

            // Agregue un KeyListener a la vista para manejar las pulsaciones de teclas
            game2048View.addKeyListener(controller);
            
            
            game2048View.setVisible(true);
        	
        });
    }

}
