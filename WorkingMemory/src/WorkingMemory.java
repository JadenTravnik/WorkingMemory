
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;




@SuppressWarnings("serial")
public class WorkingMemory extends JFrame implements MouseMotionListener{

	static LTCircle[] longTermMemories;
	static int numMem = 1000, width = 1200, height = 800,radius = 10, sensitivity = 20;
	
	public WorkingMemory() {
		addMouseMotionListener(this);
		longTermMemories = new LTCircle[numMem];
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	JPanel longTerm = new JPanel();
    	initLongTerm(longTerm);
        setSize(width, height);
        setLocationRelativeTo(null);
        longTerm.setBackground(Color.black);
        getContentPane().add(longTerm);
        setVisible(true);
    }
    
    private void initLongTerm(Container pane) {

    	pane.setLayout(null);
        
        Random rand = new Random();
        for (int i = 0; i < numMem; i++){
        	int x = rand.nextInt(width- radius) + radius;
        	int y = rand.nextInt(height- radius) + radius;
        	longTermMemories[i] = new LTCircle(x,y,radius,sensitivity);
        	longTermMemories[i].setVisible(true);
        	pane.add(longTermMemories[i]);
        	longTermMemories[i].setBounds(x, y, radius*2, radius*2);
        }      
    }

    public void log(Object o){
   	 System.out.println(o.toString());
    }
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @SuppressWarnings("unused")
			public void run() {
            	WorkingMemory wm = new WorkingMemory();
            }
        });
    }


	@Override
	public void mouseMoved(MouseEvent e) {
		for (LTCircle m : longTermMemories) {
			m.mouseMoved(e);
		}
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
}