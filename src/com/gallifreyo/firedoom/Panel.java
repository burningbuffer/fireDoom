package com.gallifreyo.firedoom;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements Runnable {

    public static int WIDTH;
    public static int HEIGHT;

    private Thread thread;
    private boolean isRunning = false;

    private BufferedImage image;
    private Graphics2D g;

    Fire fire = new Fire();


    public Panel(int WIDTH, int HEIGHT){
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this, "Thread");
            thread.start();
        }
    }

    public void config(){
        isRunning = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics();
    }

    public void update(){
        fire.update();
    }

    public void render(){
        if(g != null){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        }
        //------------------------------------------
        fire.render(g);


        //------------------------------------------
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();

    }


//    public static int getWidth(){ return this.WIDTH; }
//    public static int getHeight(){ return this.HEIGHT; }



    @Override
    public void run() {
        config();

        long lastTime = System.nanoTime();
        double amountOfTicks = 30.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        double timer = System.currentTimeMillis();
        int frames = 0;

        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                update();
                render();
                frames++;
                delta--;
            }
            if(System.currentTimeMillis() - timer >= 1000){
                frames = 0;
                timer+=1000;
            }
        }


    }
}
