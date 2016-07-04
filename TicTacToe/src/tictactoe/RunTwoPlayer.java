/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Neloy
 */
class RunTwoPlayer implements ActionListener{
    private JFrame f;
    private JButton start;
    private JTextField inputPlayer1, inputPlayer2;
    private JLabel pn1,pn2;
    private String playerName1, playerName2;
    
    private JButton[] b = new JButton[9];
    private JLabel[] l = new JLabel[3];
    
    private Font fnt;
    private boolean[] flag = new boolean[9];
    private int count = 0;
    
    RunTwoPlayer()
    {
       fnt = new Font("courier new", Font.BOLD, 24);        
       homeScreen(); 
    }
    
    private void homeScreen()
    {
        f=new JFrame("TicTacToe");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start = new JButton("Start");
        inputPlayer1 = new JTextField(20);
        inputPlayer2 = new JTextField(20);
        pn1 = new JLabel("Player1");
        pn1.setFont(fnt);
        pn2 = new JLabel("Player2");
        pn2.setFont(fnt);
        pn1.setBounds(30,30,100,30);
        inputPlayer1.setBounds(150,30,100,30);
        pn2.setBounds(30,70,100,30);
        inputPlayer2.setBounds(150,70,100,30);
        start.setBounds(100,120,100,30);
        
//        inputPlayer1.setText("");
//        inputPlayer2.setText("");
        
        f.setSize(300,300);
        f.setLocation(600, 250);
        f.setLayout(null);
        f.add(inputPlayer1);
        f.add(pn1);
        f.add(inputPlayer2);
        f.add(pn2);
        f.add(start);
        f.setVisible(true);
        
        start.addActionListener(this);
        
    }
    
    private void startGame()
    {
        for(int i=0;i<9;i++)
           flag[i]=false;
        f=new JFrame("TicTacToe");
        f.setSize(300,300);
        f.setLayout(new GridLayout(4,3));
        f.setLocation(600, 250);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i=0; i<9; i++)
        {
            b[i] = new JButton(" ");
            b[i].setFont(fnt);
        }
        l[0] = new JLabel("Turn:");
        l[1] = new JLabel(playerName1);
        l[2] = new JLabel("p1 - 'O'\np2 - 'X'");
        l[0].setFont(fnt);
        l[1].setFont(fnt);
        for(int i=0; i<3; i++)
            f.add(l[i]);
        for(int i=0; i<9; i++)
        {    
            f.add(b[i]);
            b[i].addActionListener(this);
            b[i].setActionCommand(Integer.toString(i));
        }
        
        f.setVisible(true);
        
    }
    
    private boolean isWin(int index, String value)
    {
        //Check row!
        for(int i=0;i<7;i=i+3)
        {
            if(b[i].getText()==value && b[i+1].getText()==value && b[i+2].getText()==value)
            {
                b[i].setBackground(Color.red);
                b[i+1].setBackground(Color.red);
                b[i+2].setBackground(Color.red);
                b[i].setForeground(Color.yellow);
                b[i+1].setForeground(Color.yellow);
                b[i+2].setForeground(Color.yellow);
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(b[i].getText()==value && b[i+3].getText()==value && b[i+6].getText()==value)
            {
                b[i].setBackground(Color.red);
                b[i+3].setBackground(Color.red);
                b[i+6].setBackground(Color.red);
                b[i].setForeground(Color.yellow);
                b[i+3].setForeground(Color.yellow);
                b[i+6].setForeground(Color.yellow);
                return true;
            }
        }
        if(b[0].getText()==value && b[4].getText()==value && b[8].getText()==value)
        {
            b[0].setBackground(Color.red);
            b[4].setBackground(Color.red);
            b[8].setBackground(Color.red);
            b[0].setForeground(Color.yellow);
            b[4].setForeground(Color.yellow);
            b[8].setForeground(Color.yellow);
            return true;
        }
        if(b[2].getText()==value && b[4].getText()==value && b[6].getText()==value)
        {
            b[2].setBackground(Color.red);
            b[4].setBackground(Color.red);
            b[6].setBackground(Color.red);
            b[2].setForeground(Color.yellow);
            b[4].setForeground(Color.yellow);
            b[6].setForeground(Color.yellow);
            return true;
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String action = ae.getActionCommand();
        
        if("Start".equals(action))
        {
            playerName1 = inputPlayer1.getText();
            playerName2 = inputPlayer2.getText();   
            if(playerName1.equals("") || playerName2.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Player Names Required!");
            }
            else
            {
                f.dispose();
                startGame();
            }
                
        }
        else
        {
            String turn = l[1].getText();
            if((turn == null ? playerName1 == null : turn.equals(playerName1)) && flag[Integer.parseInt(action)] != true)
            {
                count++;
                b[Integer.parseInt(action)].setText("O");
                b[Integer.parseInt(action)].setBackground(Color.cyan);
                flag[Integer.parseInt(action)] = true;
                l[1].setText(playerName2);
                l[1].setForeground(Color.red);
                l[1].setBackground(Color.yellow);
                l[2].setText(("p1 - 'O' p2 - 'X'"));
                if(isWin(Integer.parseInt(action),"O"))
                {
                    JOptionPane.showMessageDialog(null, playerName1+" WON !");
                    count = 9;
                }
            }
            else if((turn == null ? playerName2 == null : turn.equals(playerName2)) && flag[Integer.parseInt(action)] != true)
            {
                count++;
                b[Integer.parseInt(action)].setText("X");
                b[Integer.parseInt(action)].setBackground(Color.orange);
                flag[Integer.parseInt(action)] = true;
                l[1].setText(playerName1);
                l[1].setForeground(Color.blue);
                l[1].setBackground(Color.yellow);
                l[2].setText(("p1 - 'O' p2 - 'X'"));
                if(isWin(Integer.parseInt(action),"X"))
                {
                    JOptionPane.showMessageDialog(null, playerName2+" WON !");
//                    f.dispose();
//                    homeScreen();
                    count = 9;
                }
            }
            else
            {
                l[2].setText("Invalid choice!");
            }
            if(count == 9)
            {
                count = 0;
                int op = JOptionPane.showConfirmDialog(null," Wanna try again? :D ");
                if(op == JOptionPane.YES_OPTION)
                {
                    f.dispose();
                    startGame();
                }
                else
                {
                    f.dispose();
                    new RunApplication();
                }
                
            }
        }
    }
}

