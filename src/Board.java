import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener {

    // height & width of board panel
    int B_width=400;
    int B_height=400;

    // maximum dots snake can have initialize it with max_dots
    int max_dots=1600;
    
    // each dot size of snake
    int dot_size=10;

    // length of dots in initial phase of snake
    int init_dot;

    // decalre array for snake position as(x,y) coordinate
    int x[]=new int[max_dots];
    int y[]=new int[max_dots];

    //Apple coordinate
    int apple_x;
    int apple_y;

    // declare images
    Image body,head,apple;

    // declare timer
    Timer timer;

    // declare delay as 160 milliseconds
    int delay=160;

    // declare score as 0 as initial 
    int score=0;

    // declare direction
    boolean leftDirection=true;
    boolean rightDirection=false;
    boolean upDirection=false;
    boolean downDirection=false;

    // decalre game as on at initial 
    boolean inGame=true;

    // board constructor
    Board(){
        
        // TAdapter initialize here for KeyEvent
        TAdapter tAdapter=new TAdapter();
        addKeyListener(tAdapter);  // add Action listener for an KeyEvent
        
        setFocusable(true);

        // setting the dimension of the board panel
        setPreferredSize(new Dimension(B_width,B_height)); 
        setBackground(Color.black); // set the background color

        // then call initaite method for setting the initial position of the snake
        initGame();

        // first call the Load Image method to set the image on Board
        loadImage();
    }


    //Initialize the position of the snake
    public void initGame(){
        // initializing the total dot size at initial point
        init_dot=3;

        // setting the initail postion of(x,y) coordinate
        x[0]=50; 
        y[0]=50;
        
        //Initialize the snake starting position
        for(int i=1;i<init_dot;i++){
            x[i]=x[0]+dot_size*i; // Its position is towards left at initial point
            y[i]=y[0];
        }

        /* After initialising the position of the snake then locate 
        the position of apple on the board */
        
        locateApple();

        // timer call the actionEvent as timer control the snake dynamics for moving
        timer=new Timer(delay,this); // here delay is the time between timer 0 -to- timer 1 etc...
        timer.start(); // then start the timer

    }

    // Load the image used in snakeGame (head , dot , apple )
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

    // draw the imagees of the snake using graphics
    public void doDrawing(Graphics g){
        if(inGame) { 
            // drawing apple image
            g.drawImage(apple, apple_x, apple_y, this);

            // for loop used to draw the head and body of snake
            for (int i = 0; i < init_dot; i++) {
                if (i == 0) {
                    g.drawImage(head, x[0], y[0], this);
                } else {
                    g.drawImage(body, x[i], y[i], this);
                }
            }
        }
        else{
            // if the inGame is false then call gameover() & stop the timer
            gameOver(g);
            timer.stop();
        }
    }

    // Locating the position of apple on board panel
    public void locateApple(){

        // Here (apple_x,apple_y) as coordinate
        // Here 39 is the limit value of the width & height
        int r=(int)(Math.random()*39); // Math.random give no between 0 to 1
        apple_x=r*dot_size; 
        r=(int)(Math.random()*39);
        apple_y=r*dot_size;

    }

    // action event call for snake movement
    // and smake for the collision and checking apple
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint(); // is used to refresh the frame
    }

    // this method responsible for the movement of snake
    public void move(){
        // moving the snake body for last position to i=1;
        for(int i=init_dot;i>0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }

        // then moving snake headv position for each position
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

    // checking apple is eaten by snake or not
    public void checkApple(){

        /* if the coordinate of(apple_x,apple_y) collide with the head
        of snake*/
        if((apple_x==x[0]) && (apple_y==y[0])){
            init_dot++; // if eaten then increment the snake dots 
            locateApple(); // then again locate the apple in the board
        }
    }

    // check collision happens or not
    public void checkCollision(){

        // this for loop check the snake body collision 
        for(int i=init_dot;i>0;i--){
            if((i>4) && (x[0]==x[i]) && (y[0]==y[i])){
                inGame=false;
            }
        }

        // these 4 are Boundary collision of board with snake
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

        // if the collision happens then stop the game here by stopping timer
        if(!inGame){
            timer.stop();
        }
    }

    public void restart(){
        setVisible(false);
        new SnakeGame();

    }

    // Keyevent
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent ke){
            
            int key=ke.getKeyCode();

            // for space press for restart the game
            if(key==KeyEvent.VK_SPACE){
                restart();
            }
            // left press
            if(key==KeyEvent.VK_LEFT && !rightDirection){
                leftDirection=true;
                upDirection=false;
                downDirection=false;
            }

            // right press
            if(key==KeyEvent.VK_RIGHT && !leftDirection){
                rightDirection=true;
                upDirection=false;
                downDirection=false;
            }
            // Up press
            if(key==KeyEvent.VK_UP && !downDirection){
                leftDirection=false;
                upDirection=true;
                rightDirection=false;
            }

            // Down press
            if(key==KeyEvent.VK_DOWN && !upDirection){
                leftDirection=false;
                downDirection=true;
                rightDirection=false;
            }
        }
    }

    /* this is the graphics method after collision is happened to provide the score 
    and ask for restart as well*/
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
