package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FlowLayout;


public class Game2048View extends JFrame {
	
	private URL image = getClass().getResource("/images/2048.png");
	
	private JButton bottonNewGame;

	private JLabel label2048;
	
	private JLabel labelScore;
	private JLabel labelBestScore;
	
	
	private JLabel score;
	private JLabel bestScore;
	
	private int width = 475;
	private int height = 570;
	
	
	private JPanel gridPanel;
    private JButton[][] gridButtons;
    

	private JPanel backgroundPanel;
	private JPanel backgroundPanel2;
	
	private HashMap<Integer, Color> ColorButtons = new HashMap<>();
	
	private static final String FONT = "Tahoma";
	
	public Game2048View() {
		initialize();
	}

	private void initialize() {
		getContentPane().setBackground(new Color(251, 248, 239));
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("2048");
        
        ImageIcon icon = new ImageIcon(image);
        setIconImage(icon.getImage());
        
        
        setSize(this.width, this.height);
		setLocationRelativeTo(null); // centering

		setResizable(false);
		setFocusable(true); // set focus on window so KeyListener works	
		
		
		
		bottonNewGame = new JButton("New Game");
		bottonNewGame.setForeground(new Color(255, 255, 255));
		bottonNewGame.setBackground(new Color(143, 123, 99));
		bottonNewGame.setBounds(292, 87, 106, 23);
		bottonNewGame.setFont(new Font(FONT, Font.BOLD, 12));
        getContentPane().add(bottonNewGame);
        
        
        
        
        label2048 = new JLabel("2048");
        label2048.setHorizontalAlignment(SwingConstants.CENTER);
        label2048.setForeground(new Color(117, 110, 102));
        label2048.setHorizontalTextPosition(SwingConstants.CENTER);
        label2048.setFont(new Font(FONT, Font.BOLD, 47));
        label2048.setBounds(59, 29, 125, 64);
        getContentPane().add(label2048);
        
        
        
        
        
        
        gridPanel = new JPanel();
        gridPanel.setBackground(new Color(186, 176, 162));
        gridPanel.setBounds(width/8, height/4, 339, 320);
        getContentPane().add(gridPanel, BorderLayout.CENTER);
        gridPanel.setLayout(null);
        
        
        
        
        backgroundPanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) backgroundPanel.getLayout();
        flowLayout_1.setHgap(15);
        backgroundPanel.setBackground(new Color(187, 173, 160));
        backgroundPanel.setBounds(220, 29, 58, 47);
        getContentPane().add(backgroundPanel);
        
        
        backgroundPanel2 = new JPanel();
        FlowLayout flowLayout = (FlowLayout) backgroundPanel2.getLayout();
        flowLayout.setHgap(50);
        backgroundPanel2.setBackground(new Color(187, 173, 160));
        backgroundPanel2.setBounds(292, 29, 106, 47);
        getContentPane().add(backgroundPanel2);
        
        
        
        labelScore = new JLabel("SCORE");
        labelScore.setForeground(new Color(234, 228, 214));
        labelScore.setFont( new Font(FONT, Font.BOLD, 10) );
        backgroundPanel.add(labelScore);
        
        
        labelBestScore = new JLabel("BEST");
        labelBestScore.setForeground(new Color(234, 228, 214));
        labelBestScore.setFont( new Font(FONT, Font.BOLD, 10) );
        backgroundPanel2.add(labelBestScore);
        
        
        
        
        score = new JLabel("");
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont( new Font(FONT, Font.BOLD, 10) );
        backgroundPanel.add(score);
        
        bestScore = new JLabel("");
        bestScore.setHorizontalAlignment(SwingConstants.CENTER);
        bestScore.setFont( new Font(FONT, Font.BOLD, 10) );
        backgroundPanel2.add(bestScore);
        
        initializeGridButtons();
        
        initColorButtons();
        
	}
	

	private void initColorButtons() {
		// TODO Auto-generated method stub
		ColorButtons.put(0,		new Color (238, 228, 218, 90));
		ColorButtons.put(2, new Color (238, 228, 218));
		ColorButtons.put(4, new Color (237, 225, 201));
		ColorButtons.put(8, new Color (243, 178, 122));
		ColorButtons.put(16, new Color (245, 149, 99));
		ColorButtons.put(32, new Color (245, 124, 95));
		ColorButtons.put(64, new Color (246, 93, 93));
		ColorButtons.put(128, new Color (237, 206, 113));
		ColorButtons.put(256, new Color (237, 204, 97));
		ColorButtons.put(512, new Color (237, 200, 80));
		ColorButtons.put(1024, new Color (237, 197, 63));
		ColorButtons.put(2048, new Color (238, 194, 46));
	}

	private void initializeGridButtons() {
        gridButtons = new JButton[4][4];

        // Create and adds the buttons to the grid
        for (int row = 0; row < gridButtons.length; row++) {
            for (int column = 0; column < gridButtons[0].length; column++) {
                JButton button = new JButton("");
                
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.setEnabled(false);
                button.setBorder(null);
                button.setBackground(new Color(205, 193, 179));
                button.setBounds(15+column*80, 10+row*77, 70, 70);
                
                gridButtons[row][column] = button;
                gridPanel.add(gridButtons[row][column]);
            }
        }
    }
	
	public void updateView(int[][] grid, int valueScore, int valueBestScore) {
        // Actualizar la interfaz gráfica con la nueva configuración de la grilla
		
		
		score.setText(String.valueOf(valueScore));
		bestScore.setText(String.valueOf(valueBestScore));
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                if (grid[row][column] == 0) {
                    gridButtons[row][column].setText(""); // Empty cell
                    gridButtons[row][column].setBackground(ColorButtons.get(0));
                } else {
                	
                	gridButtons[row][column].setBackground(ColorButtons.get(grid[row][column]));
                    gridButtons[row][column].setText(String.valueOf(grid[row][column])); // Show number
                }
            }
        }
        repaint();
    }
	
	public JPanel getGridPanel() {
		return gridPanel;
	}

	public void setGridPanel(JPanel gridPanel) {
		this.gridPanel = gridPanel;
	}

	public JButton[][] getGridButtons() {
		return gridButtons;
	}

	public void setGridButtons(JButton[][] gridButtons) {
		this.gridButtons = gridButtons;
	}
	
	public JButton getBottonNewGame() {
		return bottonNewGame;
	}
	
	
}
