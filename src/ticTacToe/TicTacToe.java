/** TicTacToe.java
 * Brady Haden
 * 
 * This GUI program displays a tic-tac-toe board and
 * allows two players to try and best one another in
 * the classic game. 
 * When a player wins or there is a tie the board is
 * automatically reset for another round.
   * 
   */

package ticTacToe;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;

public class TicTacToe extends JFrame
{
  private static final long serialVersionUID = 1L;
  private static final int WIDTH = 350;
  private static final int HEIGHT = 350;
  private boolean xTurn = true; // is it x's turn?
  private JButton[][] buttonsArr = new JButton[3][3];
  
  //*************
  
  public TicTacToe()
  {
    setTitle("Tic-Tac-Toe");
    setSize(WIDTH, HEIGHT);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    createContents();
    try 
    { 
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
    }
    setVisible(true);
  }
  
  private void createContents()
  { 
    setLayout(new GridLayout(3, 3));
    for (int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        buttonsArr[i][j] = new JButton();
        buttonsArr[i][j].addActionListener(new Listener());
        add(buttonsArr[i][j]);
      }
    }
  }
  
  private class Listener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      JButton btn = (JButton) e.getSource();
      if (btn.getText().isEmpty())
      {
        btn.setText(xTurn ? "X" : "O");
        if (checkForWinner()) // true if current xTurn is winner
        {
          JOptionPane.showMessageDialog(null, (xTurn ? "X" : "O") 
              + " is the winner!");
          resetGame();
        }
        else if(checkBoardFull())
        {
          JOptionPane.showMessageDialog(null, "Its a Tie!");
          resetGame();
        }
        else
          xTurn = !xTurn;
      }
    }
  }
  
  /*
   * method to reset the game. it clears the board and sets xTurn
   * to its default value of 'true'.
   */
  private void resetGame()
  {
    for (int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        buttonsArr[i][j].setText("");
      }
    }
    xTurn = true;
  }
  
  /*
   * this checks for a winner by checking if each row on the board
   * has 3 in a row or each column nhas 3 in a row.
   * it then checks both the major diagonal(top left to bottom right)
   * and the minor diagonal(top right to bottom left) for 3 in a row
   * 
   * returns boolean
   */
  private boolean checkForWinner()
  {
    // check rows
    for (int i=0; i<3; i++)
    {
      if (buttonsArr[i][0].getText().equals(xTurn ? "X" : "O") && 
          buttonsArr[i][1].getText().equals(xTurn ? "X" : "O") && 
          buttonsArr[i][2].getText().equals(xTurn ? "X" : "O"))
        return true;
    }
    
    // check columns
    for (int j=0; j<3; j++)
    {
      if (buttonsArr[0][j].getText().equals(xTurn ? "X" : "O") && 
          buttonsArr[1][j].getText().equals(xTurn ? "X" : "O") && 
          buttonsArr[2][j].getText().equals(xTurn ? "X" : "O"))
        return true;
    }

    // check major diagonal
    if (buttonsArr[0][0].getText().equals(xTurn ? "X" : "O") &&
        buttonsArr[1][1].getText().equals(xTurn ? "X" : "O") &&
        buttonsArr[2][2].getText().equals(xTurn ? "X" : "O"))
      return true;
    // check minor diagonal    
    if (buttonsArr[0][2].getText().equals(xTurn ? "X" : "O") &&
        buttonsArr[1][1].getText().equals(xTurn ? "X" : "O") &&
        buttonsArr[2][0].getText().equals(xTurn ? "X" : "O"))
      return true;
    return false;
  }
  
  /*
   * this checks if the board is full (aka a tie game)
   * 
   * returns boolean
   */
  private boolean checkBoardFull()
  {
    for (int i=0; i<3; i++)
    {
      for (int j=0; j<3; j++)
      {
        if (buttonsArr[i][j].getText().isEmpty())
          return false;
      }
    }
    return true;
  }

  public static void main(String[] args)
  {
    new TicTacToe();
  }

}
