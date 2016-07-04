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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Neloy
 */
class RunSinglePlayer implements ActionListener, ItemListener {
    private JFrame f;
    private final Font fnt;
    private JButton start;
    private JTextField inputPlayer;
    private JLabel pn;
    private String playersTurn;
    private final String[] levels = { "easy", "medium", "hard" }; 
    private JComboBox difficultyLevel;
    private String selectedDiffLevel = "easy";
    private String playerName = "default";
    private char[] flag = new char[9];
    private final int[][] aflag = new int[3][3];
    private char[][] tflag = new char[3][3];
    private JButton[] b = new JButton[9];
    private JLabel[] l = new JLabel[3];
    int count;
    int tcount;
    
    RunSinglePlayer() {
       fnt = new Font("courier new", Font.BOLD, 13);
       int k=0;
       for(int i=0; i<3; i++)
           for(int j=0; j<3; j++)
            aflag[i][j]=k++;
       homeScreen();
    }
    
    private void homeScreen(){
        f=new JFrame("TicTacToe");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start = new JButton("Start");
        inputPlayer = new JTextField(20);
        pn = new JLabel("Player Name:");
        pn.setFont(fnt);
        pn.setBounds(30,30,100,30);
        difficultyLevel = new JComboBox(levels);
        difficultyLevel.setBounds(100, 70, 100, 30);
        inputPlayer.setBounds(150,30,100,30);
        start.setBounds(100,120,100,30);
        
        f.setSize(300,300);
        f.setLocation(600, 250);
        f.setLayout(null);
        f.add(inputPlayer);
        f.add(pn);
        f.add(difficultyLevel);
        f.add(start);
        f.setVisible(true);
        
        start.addActionListener(this);
        difficultyLevel.addItemListener(this);
        
    }
    
    private void initBoard(){
        count = 0;
        for(int i=0;i<9;i++)
            flag[i]= (char)0x0;
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
        l[1] = new JLabel(playerName);
        l[2] = new JLabel("P - 'O'\nC - 'X'");
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
    
    private boolean changeColor(String value)
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
    
    private void modeGameEasy(){
    }
    
    private boolean isWin(char t){
        if(tflag[0][0]==t && tflag[0][1]==t && tflag[0][2]==t)
            return true;
        else if(tflag[1][0]==t && tflag[1][1]==t && tflag[1][2]==t)
            return true;
        else if(tflag[2][0]==t && tflag[2][1]==t && tflag[2][2]==t)
            return true;
        else if(tflag[0][0]==t && tflag[1][0]==t && tflag[2][0]==t)
            return true;
        else if(tflag[0][1]==t && tflag[1][1]==t && tflag[2][1]==t)
            return true;
        else if(tflag[0][2]==t && tflag[1][2]==t && tflag[2][2]==t)
            return true;
        else if(tflag[0][0]==t && tflag[1][1]==t && tflag[2][2]==t)
            return true;
        else if(tflag[0][2]==t && tflag[1][1]==t && tflag[2][0]==t)
            return true;
        else
            return false;
    }
    
    private int rNextMoveGenMedium(int i, int j, char turn){
        tflag[i][j] = turn;
        if(isWin(turn))
        {
            tflag[i][j]=(char)0;
            if(turn == 'X')
                return 1;
            else
                return -1;
        }
        int winCount = 0;
        for(int r = i-1; r <= i+1; r++){
            for(int c = j-1; c <= j+1; c++){
                if(r>=0 && r<3 && c>=0 && c<3 && tflag[r][c]==(char)0){
                    if(turn == 'X')
                        winCount += rNextMoveGenMedium(r, c, 'O');
                    else
                        winCount += rNextMoveGenMedium(r, c, 'X');
                }
            }
        }
        tflag[i][j]=(char)0;
        return winCount;
    }
    
    private int modeGameMedium(){
        int tr = -1, tc = -1, winCount = -100000, tmp;
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                if(tflag[r][c]==(char)0){
                    tflag[r][c]='X';
                    if(isWin('X'))
                        return aflag[r][c];
                    tflag[r][c]='O';
                    if(isWin('O'))
                        return aflag[r][c];
                    tflag[r][c]=(char)0;
                    tmp = rNextMoveGenMedium(r, c, 'X');
                    if(tmp >= winCount){
                        tr = r;
                        tc = c;
                        winCount = tmp;
                    }
                }
            }
        }
        if(tr == -1 || tc == -1){
            return -1;
        }
        return aflag[tr][tc];
    }
    
    private void modeGameHard(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if("Start".equals(action)){
            if("".equals(inputPlayer.getText())){
                JOptionPane.showMessageDialog(null, "Provide Player's Name!");
            }
            else{
                f.dispose();
                playersTurn = playerName = inputPlayer.getText();
                initBoard();
            }
        }
        else{
            if(playersTurn == null ? playerName == null : playersTurn.equals(playerName)){
                if(flag[Integer.parseInt(action)] == (char)0){
                    playersTurn = null;
                    flag[Integer.parseInt(action)] = 'O';
                    b[Integer.parseInt(action)].setText("O");
                    b[Integer.parseInt(action)].setBackground(Color.orange);
                    count++;
                    l[1].setText("C - Computer!");
                    int k = 0;
                    for(int i=0; i<3; i++)
                        for(int j=0; j<3; j++)
                            tflag[i][j] = flag[k++];
                    if(isWin('O')){
                        changeColor("O");
                        JOptionPane.showMessageDialog(null, playerName+" WON !");
                        count = 9;
                    }
                    switch (selectedDiffLevel) {
                        case "easy":
                            modeGameEasy();
                            break;
                        case "medium":
                            int tmp = modeGameMedium();
                            if(tmp != -1){
                                flag[tmp] = 'X';
                                b[tmp].setText("X");
                                b[tmp].setBackground(Color.CYAN);
                                count++;
                            }
                            if(isWin('X')){
                                changeColor("X");
                                JOptionPane.showMessageDialog(null,"Computer WON !");
                                count = 9;
                            }
                            break;
                        case "hard":
                            modeGameHard();
                            break;
                    }
                    playersTurn = playerName;
                    l[1].setText(playerName);
                    if(count == 9){
                        count = 0;
                        int op = JOptionPane.showConfirmDialog(null," Wanna try again? :D ");
                        if(op == JOptionPane.YES_OPTION){
                            f.dispose();
                            initBoard();
                        }
                        else{
                            f.dispose();
                            new RunApplication();
                        }  
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Make A Valid Move!");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Wait For Your Turn!");
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        selectedDiffLevel = (String) e.getItem();
        System.out.println(selectedDiffLevel);
    }
    
}
