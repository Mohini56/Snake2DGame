import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    JFrame frame;

    Image img;

    JLabel textLabel,iconLabel;

    JButton Yes;
    JButton No;

     Main() {

         // Adding title to the Main Frame
         frame=new JFrame("Snake Game");

         // Adding Boarder design to the main frame
         frame.getRootPane().setBorder(
                 BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green)
         );

         frame.getContentPane().setBackground(Color.BLACK);
         frame.setLayout(null);

         //Adding image to the frame with title
         img= Toolkit.getDefaultToolkit().getImage("C:\\Users\\MOHINI SHARMA\\Downloads\\snakeTitle.jpeg");
         frame.setIconImage(img);


         // Using Jlabel adding image in the frame
         iconLabel=new JLabel();
         ImageIcon icon = new ImageIcon("C:\\Users\\MOHINI SHARMA\\Downloads\\snaketit.jpeg");
         Image scaleImage = icon.getImage().getScaledInstance(100, 100,Image.SCALE_SMOOTH);
         iconLabel.setIcon(new ImageIcon(scaleImage));
         iconLabel.setLayout(null);
         // Setting position of Image using setBounds
         iconLabel.setBounds(134,80,200,100);
         iconLabel.setVisible(true);


         textLabel=new JLabel("Do You Want To Play Game?");
         textLabel.setBounds(90,150,230,150);
         textLabel.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
         textLabel.setForeground(Color.white);

         // to show setBound use setLayout as null
         textLabel.setLayout(null);

         //Adding buttons to the frame
         Yes=new JButton("Yes");
         Yes.setBounds(115,250,60,30);
         Yes.setBackground(Color.green);
         Yes.setForeground(Color.black);
         Yes.addActionListener(this);

         No=new JButton("No");
         No.setBounds(197,250,60,30);
         No.setBackground(Color.green);
         No.setForeground(Color.black);
         No.addActionListener(this);

         // Now in the Last add the textLabel,IconLabel to the frame.
         frame.add(Yes);
         frame.add(No);
         frame.add(textLabel);
         frame.add(iconLabel);
         frame.setBounds(590,200,400,400);
         frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         frame.setVisible(true);
         frame.setLayout(null);

     }



     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource() == Yes) {
             SnakeGame snakeGame = new SnakeGame();

         }
         if (e.getSource() == No) {
             System.exit(0);
         }
     }


     public static void main(String[] args){
        Main snakeGame=new Main();
     }
}
