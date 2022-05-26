import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Main extends GameEngine
{
    private int width, height, playerPosX,bulletsCount = 0, pos = 0;
    private final int [] playerPos = new int []{205,155,105,55};
    static boolean _InPlay = false, start = true, shooting = false;

    JLabel label = new JLabel();
    JButton _playButton = new JButton();
    JButton _menuButton = new JButton();
    public Menu menu;
    private ArrayList<Integer> bulletRotaion = new ArrayList<>();
    private ArrayList<Integer> bulletPos = new ArrayList<>();
    private double dealtaTime;
    private double expanding =1,innerRadius = 35, playerSpeed = 0,speed = 100;
    public void init()// initialise things before game starts.
    {
        width = 640; // screen size
        height = 480;// screen size
        setWindowSize(width, height);// override size


        playerPosX = playerPos[pos];

        //StartUpUI();
        menu = new Menu();
       // PowerCal();
    }

    public static void main(String args[])
    {
        Main game = new Main();
        createGame(game, 120); // Game object and targeted frameRate
    }

    @Override
    public void update(double dt)
    {
        if(start)
        {
            StartUpUI();
            start = false;
        }

        if(!_InPlay){ return; }

        _playButton.setVisible(false); // turn off play button
////////////// expanding rings /////////////////////////////////////////////
        // Test 
        if (expanding >= 6) { expanding = 1; }
        playerSpeed +=  speed * dt;
        expanding +=  1 * dt;
        innerRadius += .7 * dt;
        System.out.println(expanding);

        if (!shooting){ return; }

        dealtaTime = dt;




    }

    private void StartUpUI()
    {

        mFrame.setTitle(" Team Ico  :  Boss Maze  ");// just a bit of information on top of window

        mPanel.setLayout(new GridLayout(3,6));
        mPanel.setBorder(BorderFactory.createEmptyBorder(30,130,50, 130));


        _playButton = menu.get_playButton();
        mPanel.add(_playButton);

        _menuButton = menu.get__menuButton();
        //mPanel.add(_menuButton);

        mFrame.add(mPanel);

        mFrame.setResizable(false);
        mFrame.setLocation(800,200);// assume screen size of people and place in somewhere in middle on start up
    }



    @Override
    public void paintComponent()
    {
        clearBackground(width,height);
////////////// Movement testing rings /////////////////////////////////////////////
        changeColor(Color.BLACK); // black ring
        drawSolidCircle(width/2,height/2,230);
        changeColor(Color.blue); // blue ring
        drawSolidCircle(width/2,height/2,180);
        changeColor(Color.white); // white ring
        drawSolidCircle(width/2,height/2,130);
        changeColor(Color.red); // red ring
        drawSolidCircle(width/2,height/2,80);
        changeColor(Color.GREEN); // boss
        drawSolidCircle(width/2,height/2,20);

////////////// expanding rings /////////////////////////////////////////////
        saveCurrentTransform();

        translate(width/2,height/2);
        scale(expanding,expanding);

        drawCircle(0,0,40);


        restoreLastTransform();

////////////// character /////////////////////////////////////////////
        saveCurrentTransform();

        changeColor(Color.lightGray); // just chosen for character currently
        translate(width/2,height/2); // Moves player rotate to centre of screen, so rotation is set from there
        rotate(playerSpeed);
        drawSolidCircle(playerPosX,0,7); // player location

        restoreLastTransform();


////////////// ui Panel /////////////////////////////////////////////
    //    changeColor(Color.GREEN); // boss
     //   drawSolidRectangle(120,20,400,440); // ui

////////////// Attack One /////////////////////////////////////////////

        if (!shooting){ return; } // move player around selected point at set degrees
        for (int i = 0;i < bulletsCount;i++)
        {
            ShootingBullet(i);
        }






    }

    public void ShootingBullet(int indx)
        {
            int bulletMovemeant = bulletPos.get(indx);

            bulletMovemeant -= 10 * dealtaTime;;

            saveCurrentTransform();

            changeColor(Color.yellow); // bullet
            translate(width / 2, height / 2); // To centre of screen
            rotate(bulletRotaion.get(indx));
            drawSolidRectangle(bulletMovemeant, -2.5, 8, 5); // Bullet at players location

            restoreLastTransform();
            bulletPos.set(indx,bulletMovemeant);
        }


    @Override
    public void keyPressed(KeyEvent e)
    {
        if ((e.getKeyCode() == KeyEvent.VK_UP) && (pos < playerPos.length ))
        {
            // move player inwards
            pos ++;
            playerPosX = playerPos[pos];
        }

        if ((e.getKeyCode() == KeyEvent.VK_DOWN) && (pos > 0 ))
        {
            // move player outwards
            pos --;
            playerPosX = playerPos[pos];
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            // move player outwards
            speed *= -1;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            // move player outwards
            speed *= -1;
        }


        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            bulletPos.add(playerPosX - 15);
            bulletRotaion.add((int) playerSpeed);
            bulletsCount ++;
            shooting = true;



        }
    }

    private void PowerCal()
    {
        int months = 52;
        float loan = 31000;
        float paymentPercent = 0.02f;
        float interestRate = 1.2599f;
        float payments = 0;
        float TotalPaid = 0;
        int monthPasted = 0;
        int yearsPasted = 0;

        for (int i = 0; i < months;i++)
        {
            payments = loan * paymentPercent;
            loan -= payments;
            TotalPaid += payments;
            System.out.println("Monthly payments = " + payments);
        }

        System.out.println("After four years we would still owe = " + loan);
        System.out.println("After four years we Paid = " + TotalPaid);

        loan *= interestRate;
        System.out.println("After interest = " + loan);

        while (loan > 1000)
        {
            if(monthPasted == 12)
            {
                loan *= interestRate;
                System.out.println("Still owe = " + loan);
                monthPasted = 0;
                yearsPasted ++ ;
            }

            payments = loan * paymentPercent;

            if(payments <= 400)
            {
                payments = 400;
            }
            loan -= payments;
            TotalPaid += payments;
            System.out.println("Monthly payments = " + payments);
            monthPasted ++;
        }
        System.out.println("It would take another " + yearsPasted + " more years to pay off ");
        System.out.println(" We would have paid = " + TotalPaid + " in the end");
    }


}

