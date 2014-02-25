import java.awt.Color;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class LTCircle extends MemoryCircle{
	
	
	public LTCircle(int x,  int y, int r, int sensitivity) {
		super(x,y,r,sensitivity);
	}
    
    @Override
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


     public void log(Object o){
    	 System.out.println(o.toString());
     }
}