
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JFrame;




@SuppressWarnings("serial")
public class WorkingMemory extends JFrame implements MouseMotionListener{

	static mCircle[] mems;
	static int numMem = 1000, width = 1200, height = 800,radius = 10, sensitivity = 20;
	
	public WorkingMemory() {
		addMouseMotionListener(this);
    	mems = new mCircle[numMem];
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI(getContentPane());
        setSize(width, height);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);
        setVisible(true);
    }
    
    private void initUI(Container pane) {

    	pane.setLayout(null);
        
        Random rand = new Random();
        for (int i = 0; i < numMem; i++){
        	int x = rand.nextInt(width- radius) + radius;
        	int y = rand.nextInt(height- radius) + radius;
        	mems[i] = new mCircle(x,y,radius,sensitivity);
        	mems[i].setVisible(true);
        	pane.add(mems[i]);
        	mems[i].setBounds(x, y, radius*2, radius*2);
        }      
    }

    public void log(Object o){
   	 System.out.println(o.toString());
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	WorkingMemory wm = new WorkingMemory();
            }
        });
    }


	@Override
	public void mouseMoved(MouseEvent e) {
		for (mCircle m : mems) {
			m.mouseMoved(e);
		}
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
}