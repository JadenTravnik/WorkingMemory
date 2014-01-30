import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class mCircle extends JLabel implements MouseMotionListener {
	
	int x, y, r, Xc, Yc,sR;
	Color current;
	StopWatch sw;
	boolean active;
	private Timer timer = new Timer();
	private int decayTime;
	
	
	public mCircle(int x,  int y, int r) {
		this.addMouseMotionListener(this);
		this.x = x;
		this.y = y;
		this.r = r;
		this.sR = this.r + 10;
		this.active = true;
		Xc = x + r/2;
		Yc = y + r/2;
		current = Color.black;
		sw = new StopWatch();
		this.decayTime = 3;
		timer.schedule(new UpdateTask(this),  0, 500);

	}
	
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(current);

        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
               RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        g2d.fillOval(x, y, r, r);   
   } 

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }

    public void mouseMoved(MouseEvent e) {
        if (Math.sqrt((e.getX()-Xc)*(e.getX()-Xc) + (e.getY()-Yc)*(e.getY()-Yc)) < sR){
        	current = Color.green;
        	active = true;
        	sw.stop();
        	this.repaint();
        }
        else if(active){
        	active = false;
        		sw.start();
        }
        
     }

     public void mouseDragged(MouseEvent e) {
        
     }   

     public void log(Object o){
    	 System.out.println(o.toString());
     }
     
    private class UpdateTask extends TimerTask {
    	
    	mCircle m;
    	public UpdateTask(mCircle m) {
			this.m = m;
		}

		@Override
		public void run() {
			EventQueue.invokeLater(new Runnable(){
				public void run() {
					if(!active){
						if(sw.getElapsedTimeSecs() < decayTime){
							current = current.darker();
						}
						else {
							sw.stop();
							current = Color.black;
						}
						m.repaint();
					}
				}
			});
			
		}
    	
    }
}