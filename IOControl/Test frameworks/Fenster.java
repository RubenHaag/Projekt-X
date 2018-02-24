import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Fenster extends JFrame implements KeyListener,
ActionListener{
	private int x = 0,y = 0, width = 700, height = 500, x1 = 150, y1 =150, speed = 5, w = 20, c= 50, b = 40;
	private int rx = 100, ry = 10, size = 105,r2x = 300,r2y= 200,widthDB = 50,heightDB = 50;
	private Toolkit t;
	private Rectangle r1, r2, r3, gr, pl, h1,db,r;
	private boolean coll, coll1, ground, isLookingRight;
	private int i= 0;
	private Partikel pa = new Partikel(b, c, w, gr);
	
	
	private static final long serialVersionUID = -3853732973302441381L;
	JTextArea a;
	Fenster(){
		t = Toolkit.getDefaultToolkit();
		setTitle("Spiel");
		Dimension d = t.getScreenSize();
		x = (int) ((d.getWidth()- width)/2);
		y = (int) ((d.getHeight() - height)/2);
		setBounds(x,y, width,height);
		pa.start();
		Timer timer = new Timer(4, new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	Fenster.this.repaint();
            	//pa.updateGround(gr);
            	System.out.println(""+ ground);
            }
        });
		timer.start();

	}
	

	private void onGround(boolean g){
		pa.setGround(g);
		
	}
	
	private void initListeners() {
		
	}




	private void initComponents() {
		a = new JTextArea();
		a.addKeyListener(this);
		a.setBounds(1, 1, 200, 200);
		this.add(a);
		a.setEditable(false);
		a.setVisible(true);
		r1 = new Rectangle(rx, ry, size, size);
		r2 = new Rectangle(x1, y1, size, size);
		r3 = new Rectangle(r2x, r2y, (size/2), (size/2));
		gr = new Rectangle(getWidth()*1/4,getHeight()- 50, getWidth()*1/2, 30);
		pl = new Rectangle(b, c, w, w);
		h1 = new Rectangle(getWidth()*1/8,getHeight()- 180, getWidth()*1/4, 30);

		
	
	}



	public static void main(String[] args){ // MAIN IST DAAAA
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
               
            }

			
        });
		
       
		
	}




	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
	displayInfo(e, true);
		
	}

	public static void createAndShowGUI() {
		Fenster f1 = new Fenster();
		f1.initComponents();
		f1.initListeners();
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f1.setVisible(true);
		
	}

	@Override
    public void paint(Graphics g) 
    {
		
		c = (int) pa.getyPos();
		b = (int) pa.getxPos();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(b, c, 20, 20);
		pl.setBounds(b, c, 20, 25);
		g.setColor(Color.LIGHT_GRAY);
		gr.setBounds(getWidth()*1/4,getHeight()- 50, getWidth()*1/2, 30);
		g.fillRect(getWidth()*1/4,getHeight()- 50, getWidth()*1/2, 30);
		g.fillRect(getWidth()*1/8,getHeight()- 180, getWidth()*1/4, 30);
		g.setColor(Color.MAGENTA);
		//g.fillRect((int) pl.getMinX() - widthDB,(int) (pl.getMaxY() - heightDB), widthDB, heightDB);
		g.setColor(Color.LIGHT_GRAY);
		 //#portalgun
		if(pl.intersects(gr)){
			if(pa.getYVel() >= 0 && (pl.getMaxY() + 10 < gr.getMinY() || pl.getMaxY() - 10 < gr.getMinY())) {
				r = gr;
				ground = true;
				System.out.println("a");
			}
		}else if(pl.intersects(h1)){
			//System.out.println(pl.getMaxY()+ " " + pl.getMinY());
			if(pa.getYVel() >= 0 && (pl.getMaxY() + 10 < h1.getMinY() || pl.getMaxY() - 10 < h1.getMinY())) { //#luke
				r =h1;
				
				ground = true;
			}else{ground = false;}
		}
		else{
			ground = false;
			
		}
		pa.updateGround(r);
		onGround(ground);
		
		/*
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		r2.setBounds(x1, y1, size, size);
		
		if(coll){
			g.setColor(Color.GREEN);
			g.fillRect(rx,ry,size,size);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x1,y1, size, size);
			 g.fillRect(r2x, r2y,(size/2),(size/2));
			
			
			}else{
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x1,y1, size, size);
		        g.fillRect(rx,ry,size,size);
		        g.fillRect(r2x, r2y, (size/2),(size/2));
			
					
					}
        
        */
    }
	@Override
	public void keyReleased(KeyEvent e) {
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		displayInfo(e, false);
		collide();
		
	}


	private void collide(){
		if( r2.intersects(r1)){
			coll = true;
		}else{
			coll = false;
		}
		
	}
	
	
		
	

	private void displayInfo(KeyEvent e, boolean p) {
		char c = e.getKeyChar();
		String keyString;
		
		if(p){
		
		if (c == 'w') {
            /*keyString = "vor";
            y1--;
            collide();
            repaint();
            */
			if(ground){
				ground = false;
				onGround(ground);
				pa.addyVel(500);
			}
			
            
        }else if (c == 's'){
        	/*
        	keyString = "zurück";
        	y1++;
            collide();
        	repaint();*/
        } else if (c == 'a'){
        	/*keyString = "links";
        	x1--;
            collide();
        	repaint();*/
        	pa.addxVel(-100);
        	isLookingRight = false;
        }else if (c == 'd'){
        	/*
        	keyString = "rechts";
        	x1++;
            collide();
        	repaint();
        	*/
        	
        	pa.addxVel(100);
        	isLookingRight = true;
        } else {keyString = "" + c;}
		
		}else{
			keyString = "";
		}
		
		//a.setText(keyString);
		
	
	
		}
	public void attack() {
		if(isLookingRight){
			db = new Rectangle((int) pl.getMaxX(),(int) (pl.getMaxY() - heightDB), widthDB, heightDB);
			
		}else{
			db = new Rectangle((int) pl.getMinX() - widthDB,(int) (pl.getMaxY() - heightDB), widthDB, heightDB);
			}
	}
}
	
