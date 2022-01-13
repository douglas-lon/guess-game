package com.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MyFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 8900203028893010979L;
	
	//"Settings"
    private int[] frameResolution = new int[] {720, 480};
    private String frameTitle = "GuessGame";
    private Color backgroundColor = new Color(241, 230, 205);
    private Color textColor = new Color(240, 240, 240);
    private int attempsNumber = 0;
    private int score = 0;
    private int number;
    private String result;
    private boolean guessed = false;
    private boolean tryAgainVar = true;
    private boolean giveUpVar = false;

    // Panels initiation
    private JPanel topPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel leftCenterPanel = new JPanel();
    private JPanel rightBottomPanel = new JPanel();

    //Widgets initiation
    private JLabel guessTextLabel;
    private JLabel linkLabel;
    private JLabel imageLabel = new JLabel();;
    private JLabel scoreLabel;
    private JLabel attemptsLabel;

    private JButton guessButton;
    private JButton giveUpButton;
    private JButton tryAgainButton;

    private JTextField entryText = new JTextField();
    

    public MyFrame(String theme) {

        try {
  
            UIManager.setLookAndFeel(theme);
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        // Frame settings like width, heigth, title, rezize
        this.setTitle(this.frameTitle);
        this.setSize(this.frameResolution[0], this.frameResolution[1]);
        this.setResizable(false);

        // Frame methods like visible, default, content
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(this.backgroundColor);
        this.setLayout(new BorderLayout());
        
        // Panels creation to do layout organization
        this.createPanels();
        // The "truly" interface objects
        this.createWidgets();
        
        // Adiciona tudo na tela
        this.addTo();
        
    }

    public byte ignore() {

        return 0;
    }

    private void createPanels() {

        this.topPanel = this.defPanel(backgroundColor, 720, 80,
                                      new int[]{0,20,0,20});
        this.leftPanel = this.defPanel(backgroundColor, 360, 380, 
                                       new int[]{90, 0, 20, 0});
        this.rightPanel = this.defPanel(backgroundColor, 360, 380, 
                                        new int[]{20, 55, 20, 55});
        this.bottomPanel = this.defPanel(backgroundColor, 720, 20, 
                                         new int[]{0,280,0,280});
        
        // leftPanel
        this.leftCenterPanel.setLayout(new GridLayout(2, 1, 0, 30));
        this.leftCenterPanel.setBackground(backgroundColor);
        this.leftCenterPanel.setBorder(new EmptyBorder(50, 120, 0, 120));

        //rightPanel
        this.rightBottomPanel.setLayout(new BorderLayout());
        this.rightBottomPanel.setBackground(backgroundColor);
        //this.rightBottomPanel.setBorder(new EmptyBorder(0, 60, 0, 60));

    }

    private void createWidgets() {

        this.guessTextLabel = this.defLabel(
            "<html><body>Guess the Number<br>&nbsp;&nbsp;&nbsp;Number: 1-100</body></html>", 
            Color.black, "Arial", 25, Font.BOLD, SwingConstants.CENTER);
        this.linkLabel = this.defLabel("https://discord.gg/r6vnce4", 
                                       textColor, "Arial", 12, 
                                       Font.BOLD, SwingConstants.CENTER);
        this.attemptsLabel = this.defLabel(
            ("Attempts: " + this.attempsNumber), 
            Color.BLACK, "Arial", 20, Font.BOLD, SwingConstants.CENTER);
        this.scoreLabel = this.defLabel(("Score: " + this.score), 
                                        Color.BLACK, "Arial", 20, 
                                        Font.BOLD, SwingConstants.CENTER);

        // Labels image src/com/images/ic.png
        /*ImageIcon image = new ImageIcon(new ImageIcon(
                                        getClass().
                                        getResource(
                                        "/Resources/images/ic.png"))
                                        .getImage()
                                        .getScaledInstance(
                                        250, 250, 
                                        Image.SCALE_DEFAULT));
		*/
        //this.imageLabel.setIcon(image);
        
        // Buttons 
        this.guessButton = defButton("Guess", 0, 0, false, 
                                     "Arial", 15, Font.BOLD, 
                                     Color.BLACK);
        this.giveUpButton = defButton("Give Up", 100, 50, true, 
                                      "Arial", 15, Font.BOLD, 
                                      Color.BLACK);
        this.tryAgainButton = defButton("Try Again", 100, 50, true, 
                                        "Arial", 15, Font.BOLD, 
                                        Color.BLACK);
    }

    private JLabel defLabel(String text, Color color, String font, 
                            int fontSize, int fontType, int fontAlign) {

        JLabel lab = new JLabel();

        lab.setText(text);
        lab.setHorizontalAlignment(fontAlign);
        lab.setForeground(color);
        lab.setFont(new Font(font, fontType, fontSize));
        
        return lab;

    }

    private JPanel defPanel(Color color,int width, int height, int[] bd) {
        
        JPanel panel = new JPanel();

        panel.setBackground(color);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(bd[0], bd[1], bd[2], bd[3]));
        // cima, esquerda, baixo, direita,

        return panel;
    }

    private JButton defButton(String text, int width, int height, 
                              boolean setSize, String font, 
                              int fontSize, int fontType, Color color) {

        JButton button = new JButton();

        button.setText(text);
        button.addActionListener(this);
        button.setFocusable(false);
        button.setForeground(color);
        button.setFont(new Font(font, fontType, fontSize));
        if (setSize)
            button.setPreferredSize(new Dimension(width, height));

        return button; 
    }

    private void addTo() {
        // Main frame
        this.add(this.topPanel, BorderLayout.NORTH);
        this.add(this.leftPanel, BorderLayout.WEST);
        this.add(this.rightPanel, BorderLayout.EAST);
        this.add(this.bottomPanel, BorderLayout.SOUTH);

        //Panels
        this.leftPanel.add(this.leftCenterPanel, BorderLayout.CENTER);
        this.leftPanel.add(this.guessTextLabel, BorderLayout.NORTH);
        this.leftCenterPanel.add(this.entryText);
        this.leftCenterPanel.add(this.guessButton);

        this.topPanel.add(this.scoreLabel, BorderLayout.WEST);
        this.topPanel.add(this.attemptsLabel, BorderLayout.EAST);

        this.bottomPanel.add(this.linkLabel);

        this.rightPanel.add(this.imageLabel, BorderLayout.NORTH);
        this.rightPanel.add(this.rightBottomPanel, BorderLayout.SOUTH);
        this.rightBottomPanel.add(this.giveUpButton, BorderLayout.EAST);
        this.rightBottomPanel.add(this.tryAgainButton, BorderLayout.WEST);
        
        
        this.setVisible(true);
    }

    public int getNumber() {

        return this.number;
    }

    public int getAttempts() {

        return this.attempsNumber;
    }

    public void setScore(int sco) {

        this.score = sco;
        this.scoreLabel.setText(("Score: " + this.score));
    }

    public void setAttempts(int at) {

        this.attempsNumber = at;
        this.attemptsLabel.setText(("Attempts: " + this.attempsNumber));
    }

    public void setResult(String re) {

        this.result = re;
        JOptionPane.showMessageDialog(this, this.result);
    }

    public void setGuessed(boolean guess) {

        this.guessed = guess;
    }

    public boolean getGuessed() {

        return this.guessed;
    }

    public void lockGuesses() {

        this.guessButton.setEnabled(false);
        this.entryText.setEnabled(false);
    }

    public boolean tryAgain() {
        return this.tryAgainVar;
    }

    public boolean giveUp() {

        return this.giveUpVar;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.guessButton) {
            System.out.println("It's working " + this.entryText.getText());

            try {
                this.number = Integer.parseInt(entryText.getText());
            } catch (Exception NumberFormatException) {
                System.out.println("Type a fucking int mother fucker");
            }

            this.guessed = true;
            entryText.setText("");
            
            
        }

        if(e.getSource() == this.giveUpButton) {
            this.guessButton.setEnabled(false);
            this.entryText.setEnabled(false);
            this.giveUpButton.setEnabled(false);
            this.giveUpVar = true;
        }
        
        if (e.getSource() == this.tryAgainButton) {
            System.out.println("I want to try");
            this.tryAgainVar = false;
            this.giveUpVar = false;
        }
    }
    

}
