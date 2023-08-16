import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {


    int B_width=400;

    int B_height=400;

    int max_dots=1600;
    int dot_size=10;
    int init_dot;

    int x[]=new int[max_dots];
    int y[]=new int[max_dots];

    //Apple
    int apple_x;
    int apple_y;

    Image body,head,apple;

    Timer timer;

    int delay=160;

    int score=0;

    boolean leftDirection=true;
    boolean rightDirection=false;
    boolean upDirection=false;
    boolean downDirection=false;

    boolean inGame=true;


    Board(){


        TAdapter tAdapter=new TAdapter();
        addKeyListener(tAdapter);
        setFocusable(true);
        setPreferredSize(new Dimension(B_width,B_height));
        setBackground(Color.black);

        loadImage();
        initGame();
    }


    //Initialize the Game
    public void initGame(){
        init_dot=3;

        x[0]=50;
        y[0]=50;
        //Initialize the snake starting position
        for(int i=0;i<init_dot;i++){
            x[i]=x[0]-dot_size*i;
            y[i]=y[0];
        }

        locateApple();
        timer=new Timer(delay,this);
        timer.start();

    }

    public void loadImage(){
        ImageIcon bodyIcon=new ImageIcon("src/dot.png");
        body=bodyIcon.getImage();
        ImageIcon headIcon=new ImageIcon("src/head.png");
        head=headIcon.getImage();
        ImageIcon appleIcon=new ImageIcon("src/apple.png");
        apple=appleIcon.getImage();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        doDrawing(g);
    }

    public void doDrawing(Graphics g){
        if(inGame) {
            g.drawImage(apple, apple_x, apple_y, this);
            for (int i = 0; i < init_dot; i++) {
                if (i == 0) {
                    g.drawImage(head, x[0], y[0], this);
                } else {
                    g.drawImage(body, x[i], y[i], this);
                }
            }
        }
        else{
            gameOver(g);
            timer.stop();
        }
    }

    public void locateApple(){
        int r=(int)(Math.random()*39);
        apple_x=r*dot_size;
        r=(int)(Math.random()*39);
        apple_y=r*dot_size;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }

    public void move(){
        for(int i=init_dot;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(leftDirection){
            x[0]-=dot_size;
        }
        if(rightDirection){
            x[0]+=dot_size;
        }
        if(upDirection){
            y[0]-=dot_size;
        }
        if(downDirection){
            y[0]+=dot_size;
        }
    }

    public void checkApple(){
        if((apple_x==x[0]) && (apple_y==y[0])){
            init_dot++;
            locateApple();
        }
    }


    public void checkCollision(){
        for(int i=init_dot;i>0;i--){
            if((i>4) && (x[0]==x[i]) && (y[0]==y[i])){
                inGame=false;
            }
        }
        if(x[0]<0){
            inGame=false;
        }
        if(x[0]>=B_width){
            inGame=false;
        }
        if(y[0]<0){
            inGame=false;
        }
        if(y[0]>=B_height){
            inGame=false;
        }
        if(!inGame){
            timer.stop();
        }
    }

    public void restart(){
        setVisible(false);
        new SnakeGame();

    }
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent ke){
            int key=ke.getKeyCode();
            if(key==KeyEvent.VK_SPACE){
                restart();
            }
            if(key==KeyEvent.VK_LEFT && !rightDirection){
                leftDirection=true;
                upDirection=false;
                downDirection=false;
            }
            if(key==KeyEvent.VK_RIGHT && !leftDirection){
                rightDirection=true;
                upDirection=false;
                downDirection=false;
            }
            if(key==KeyEvent.VK_UP && !downDirection){
                leftDirection=false;
                upDirection=true;
                rightDirection=false;
            }
            if(key==KeyEvent.VK_DOWN && !upDirection){
                leftDirection=false;
                downDirection=true;
                rightDirection=false;
            }
        }
    }
    public void gameOver(Graphics g){
        String msg="Game over!";
        String sp="Press 'SPACE' To Restart";
        score=(init_dot-3)*100;
        String scoreMsg="Score:"+Integer.toString(score);


        g.setColor(Color.green);
        Font s=new Font("Courier New", Font.PLAIN, 50);
        FontMetrics f=getFontMetrics(s);
        g.setFont(s);
        g.drawString(msg,(400-f.stringWidth(msg))/2,400/3);

        Font s1=new Font("Courier New", Font.PLAIN, 20);
        FontMetrics f1=getFontMetrics(s1);
        g.setFont(s1);
        g.drawString(scoreMsg,(400-f1.stringWidth(scoreMsg))/2,400/2);

        Font s2=new Font("Courier New", Font.PLAIN, 13);
        FontMetrics f2=getFontMetrics(s2);
        g.setFont(s2);
        g.drawString(sp,(400-f2.stringWidth(sp))/2,340);




    }



}
