
package snakegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SnakeGame extends javax.swing.JFrame {
        
    static final int width = 600;
    static final int height = 600;
    public static String playerUsername;
    
    // CHANGE TO A SUITABLE DESTINATION ON YOUR PC
    
    // CREATE PATH FOR FILE
    File file = new File("C:\\Users\\amend\\Desktop\\java_snake_results.txt");
    public static String onomaArxeiou = "C:\\Users\\amend\\Desktop\\java_snake_results.txt";

    
    public SnakeGame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
                         
    private void initComponents() {
        
        // CREATE FILE FOR RESULTS' STORAGE
        boolean result;
        try {
            result = file.createNewFile();
            if (result) {
                System.out.println("File created");
            } else {
                System.out.println("File already existed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        playerName = new javax.swing.JTextField();
        startGame = new javax.swing.JButton();
        exitGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        setTitle("Snake Game");
        setSize(width,height);
        setLocationRelativeTo(null);
        setVisible(true);
        
        setResizable(false);
        
        jPanel1.setBackground(Color.cyan);
        
        playerName.setFont(new Font("Arial", Font.BOLD, 15));

        jLabel1.setText("WELCOME TO OUR GAME");

        jLabel2.setText("PLAYER NAME:");

        startGame.setText("START GAME");
        startGame.setBackground(Color.black);
        startGame.setForeground(Color.white);
        startGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameActionPerformed(evt);
            }
        });

        exitGame.setText("EXIT GAME");
        exitGame.setBackground(Color.black);
        exitGame.setForeground(Color.white);
        exitGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(startGame, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitGame, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startGame, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(exitGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    private void startGameActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (playerName.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Username field must be filled out!"
                    , "ERROR", 1);            
        } else if (playerName.getText().toString().length() > 10) {
            playerName.setText("");
            JOptionPane.showMessageDialog(null, "Username must have less that than 10 letters!"
                    , "ERROR", 1);
        } else {
            playerUsername = playerName.getText();
            dispose();
            new GameFrame1();      
        }
    }   

    private void exitGameActionPerformed(java.awt.event.ActionEvent evt) {                                         
        System.exit(0);
    }
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                new SnakeGame();
            }
        });
    }
                    
    private javax.swing.JButton exitGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField playerName;
    private javax.swing.JButton startGame;
                
}
