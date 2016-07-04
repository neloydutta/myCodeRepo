/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Neloy
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new RunApplication();
    }
    
}

class RunApplication implements ActionListener{
    JFrame f;
    JButton single, multiple;
    JLabel label;
    Font fnt;
    RunApplication(){
        f = new JFrame("TicTacToe");
        fnt = new Font("courier new", Font.BOLD, 17);
        single = new JButton("Single Player");
        multiple = new JButton("Two Player");
        label = new JLabel("Choose Your Play Type!");
        label.setFont(fnt);
        label.setForeground(Color.BLUE);
        label.setBounds(30, 30, 250, 30);
        single.setBounds(75, 100, 130, 30);
        multiple.setBounds(75, 150, 130, 30);
        f.setSize(300, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(300,300);
        f.setLocation(600, 250);
        f.setLayout(null);
        f.add(label);
        f.add(single);
        f.add(multiple);
        single.addActionListener(this);
        multiple.addActionListener(this);
        f.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if("Single Player".equals(e.getActionCommand())){
            new RunSinglePlayer();
            f.dispose();
        }
        else{
            new RunTwoPlayer();
            f.dispose();
        }
    }
}

