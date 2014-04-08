import java.awt.Color;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class LTCircle extends MemoryCircle{
	
	
	public LTCircle(int x,  int y, int r, int sensitivity) {
		super(x,y,r,sensitivity);
	}
    
    @Override
    public void mouseMoved(MouseEvent e) {
    	double distance = Math.sqrt((e.getX()-Xc)*(e.getX()-Xc) + (e.getY()-Yc)*(e.getY()-Yc));
        if (distance < sR){
        	current = Color.green;
        	active = true;
        	distance = (distance == 0) ? 0.000001: distance;
        	
        	float hsbVals[] = Color.RGBtoHSB(current.getRed(), current.getGreen(), current.getBlue(), null);
			current = Color.getHSBColor(hsbVals[0], hsbVals[1], (float) (15/distance));
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