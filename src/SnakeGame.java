import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame extends JFrame{
    // declaring Image
    Image img;

    // Declaring class Board
    Board board;


    SnakeGame(){

        // setting title of the Snakegame Frame
        setTitle("Snake Game");

        // Adding Image to the snakeGame frame
        img= Toolkit.getDefaultToolkit().getImage("src//head.png");
        setIconImage(img);

        // calling Board for initialising gaming setup
        board =new Board();

        // then add board panel to the snake Game frame
        add(board);
        
        //this pack method packaged frame & board in prefered size
        pack();
        // setting position of the frame
        setBounds(590,200,400,400);

        // user can't resize frame add this below method
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new SnakeGame().setVisible(true);
    }
}
