package snakegame;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.*;
import java.util.Random;
import javax.swing.JPanel;
import static snakegame.SnakeGame.playerUsername;
import static snakegame.SnakeGame.onomaArxeiou;

public class GamePanel1 extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 75;
    public static int writeRecord = 0;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    
    public static String playersName = playerUsername;
    public static int playerScore;

    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    
    JButton restart;

    char direction = 'R';

    boolean running = false;
    Timer timer;
    Random random;


    GamePanel1() {

        random = new Random();
        
        restart = new JButton("RESTART GAME");
        restart.setBackground(Color.red);
        restart.setForeground(Color.black);
        restart.setVisible(true);
        
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });
        
        restart.setBounds(180,350, 250,50);
        
        restart.setEnabled(false);
        restart.setVisible(false);

        this.setLayout(null);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.add(restart);

        startGame();

    }

    public void startGame() {

        newApple();

        running = true;

        timer = new Timer(DELAY, this);
        timer.start();  

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {

        if (running) {
            
            // UNCOMMENT IF YOU WANT TO SEE THE LINES BETWEEN RAWS AND COLUMNS
            
            /*
            for (int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {

                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }
            */

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i=0; i<bodyParts; i++) {

                if (i==0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45,180,0));
                    
                    // UNCOMMENT TO MAKE SNAKE BODY CHANGE COLORS
                    
                    // g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            
            g.drawString("Player's name: " + playersName, (SCREEN_WIDTH - metrics.stringWidth("Player's name: " + playersName))/2, g.getFont().getSize() + 15);
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize() + 55);

        } else {
            
            if (writeRecord == 0) {
                
                playerScore = applesEaten;
        
                FileOutputStream outputStream = null;

                try {
                    outputStream = new FileOutputStream((onomaArxeiou), true);
                } catch(FileNotFoundException e) {
                    System.out.println("Error opening the file stuff.txt.");
                    System.exit(0);
                }

                PrintWriter outputWriter = new PrintWriter(outputStream);

                System.out.println("Writing to file.");
                outputWriter.println("GAME RESULT");
                outputWriter.println("PLAYER USERNAME: " + playersName.toUpperCase());
                outputWriter.println("PLAYER SCORE: " + playerScore);
                outputWriter.println("\n\n");
                outputWriter.close();                
            }
            
            writeRecord++;

            gameOver(g);

        }
    }

    public void newApple() {

        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    public void move() {

        for (int i=bodyParts; i>0; i--) {

            x[i] = x[i-1];
            y[i] = y[i-1];

        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;

            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;

            case 'L':
               x[0] = x[0] - UNIT_SIZE;
               break;

            case 'R':
               x[0] = x[0] + UNIT_SIZE;
               break;

        }

    }

    public void checkApple() {

        if ((x[0] == appleX) && (y[0] == appleY)) {

            bodyParts++;
            applesEaten++;
            newApple();
        }

    }

    public void checkCollisions() {

        for (int i=bodyParts; i>0; i--) {

            if ((x[0] == x[i]) && (y[0] == y[i])) {

                running = false;
            }
        }

        if (x[0] < 0) {

            running = false;

        }

        if (x[0] + UNIT_SIZE > SCREEN_WIDTH) {

            running = false;

        }

        if (y[0] < 0) {

            running = false;

        }

        if (y[0] + UNIT_SIZE > SCREEN_HEIGHT) {

            running = false;

        }

    }

    public void gameOver(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics metrics1 = getFontMetrics(g.getFont());

        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics2 = getFontMetrics(g.getFont());

        g.drawString("Player's name: " + playersName, (SCREEN_WIDTH - metrics2.stringWidth("Player's name: " + playersName))/2, g.getFont().getSize() + 15);
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize() + 55);

        restart.setVisible(true);
        restart.setEnabled(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }

        repaint();
        
    }
    
    private void restartActionPerformed(java.awt.event.ActionEvent evt) {
        
        // delete the parent JFrame of the JPanel

        ((Window) getRootPane().getParent()).dispose();
        
        writeRecord = 0;
        
        new GameFrame1();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }

        }
    }
    
}
