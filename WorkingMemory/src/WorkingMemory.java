
import java.awt.Color;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;




@SuppressWarnings("serial")
public class WorkingMemory extends JFrame {

	mCircle[] mems;
	int numMem = 25, width = 600, height = 400, radius = 20;
    public WorkingMemory() {
    	this.mems = new mCircle[numMem];
        initUI(this);
        
    }
    
    private void initUI(WorkingMemory wm) {
        
    	int numMem = 25, width = 600, height = 400, radius = 20;
        setTitle("Basic Shapes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(width, height);
        setLocationRelativeTo(null); 
        
        JPanel panel = new JPanel();
        panel.setBounds(0,0,width,height);
        panel.setBackground(Color.blue);
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));

       // wm.add(panel);
        
        Random rand = new Random();
        for (int i = 0; i < numMem; i++){
        	int x = rand.nextInt(width- radius) + radius;
        	int y = rand.nextInt(height- radius) + radius;
        	this.mems[i] = new mCircle(x,y,radius);
        	this.mems[i].setVisible(true);
        	wm.add(this.mems[i]);
        }      
        
        log(wm.getComponentCount());
        wm.setVisible(true);
    }

    public void log(Object o){
   	 System.out.println(o.toString());
    }
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
            	WorkingMemory wm = new WorkingMemory();
            }
        });
    }
}