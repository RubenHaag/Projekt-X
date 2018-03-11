package ioserver.testframeworks;

import java.awt.Rectangle;

public class PartikelAlt extends Thread{
    private double xVel, yVel, xpos, ypos, down, yVelw, fr1, fr2, fr, width;
    private Rectangle gr;
    private boolean ground = false;
    //Fenster f1;
    long last_time = System.nanoTime();
    public PartikelAlt(){
        xVel = 200;
        yVel = 0;
        yVelw = yVel;
        xpos = 0;
        ypos = 0;
        down = 600;
        ground = false;
        fr1 = 80;
        fr2 = -fr1;
        gr = new Rectangle(0,0,1,1);
        width = 20;
    }
    public PartikelAlt(double xpos, double ypos, double w, Rectangle gr){
        this();
        this.xpos = xpos;
        this.ypos = ypos;
        this.gr = gr;
        this.width = w;
        //this.f1 = f1;

    }
    public void setGround(boolean g){
        ground = g;
    }
    public void nGround(){
        ground = !ground;
    }

    public void run(){
        while(!isInterrupted()){
            long time = System.nanoTime();
            double dt = (double)((time - last_time));
            last_time = time;
            if (xVel < 0){
                fr = fr1;
            }else if(xVel > 0){
                fr = fr2;
            }
            if(ground) {
                yVel = yVelw;
                xpos = xpos + dt / 1000000000* xVel;
                ypos = gr.getMinY()-width;
                if (xVel > 0 || xVel < 0){
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    xVel = xVel + dt / 1000000000* fr;
                }
            } else {
                xpos = xpos + dt / 1000000000* xVel;
                ypos = ypos + dt / 1000000000* yVel;
                yVel = yVel + dt / 1000000000* down;


            }
        }
    }

    public double getxPos(){
        return xpos;
    }
    public double getYVel(){
        return yVel;
    }
    public double getyPos(){
        return ypos;
    }
    public void addxVel(double x){
        xVel = x;
    }
    public void addyVel(double x){
        yVel = -x;

    }
    public void updateGround(Rectangle gr){
        this.gr = gr;
    }

}
