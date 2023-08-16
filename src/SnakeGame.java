import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGame extends JFrame{

    Image img;

    Board board;


    SnakeGame(){

        setTitle("Snake Game");
        img= Toolkit.getDefaultToolkit().getImage("src//head.png");
        setIconImage(img);

        board =new Board();
        add(board);
        //this pack method packaged frame & board in prefered size
        pack();
        setBounds(590,200,400,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new SnakeGame().setVisible(true);
    }
}
