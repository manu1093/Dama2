/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prove;
import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;
 
import java.awt.*;
import java.awt.event.*;
 
/* 
 * LayeredPaneDemo.java requires
 * images/dukeWaveRed.gif. 
 */
public class LayeredPaneDemo extends JPanel
                             {
    private String[] layerStrings = { "Yellow (0)", "Magenta (1)",
                                      "Cyan (2)",   "Red (3)",
                                      "Green (4)" };
    private Color[] layerColors = { Color.yellow, Color.magenta,
                                    Color.cyan,   Color.red,
                                    Color.green };
 
    private JLayeredPane layeredPane;
    private JLabel dukeLabel;
    private JCheckBox onTop;
    private JComboBox layerList;
 
    //Action commands
    private static String ON_TOP_COMMAND = "ontop";
    private static String LAYER_COMMAND = "layer";
 
    //Adjustments to put Duke's toe at the cursor's tip.
    private static final int XFUDGE = 40;
    private static final int YFUDGE = 57;
 
    public LayeredPaneDemo()    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));       
 
        //Create and set up the layered pane.
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 310));
        layeredPane.setBorder(BorderFactory.createTitledBorder(
                                    "Move the Mouse to Move Duke"));
       
 
        //This is the origin of the first label added.
        Point origin = new Point(10, 20);
 
        //This is the offset for computing the origin for the next label.
        int offset = 35;
 
        //Add several overlapping, colored labels to the layered pane
        //using absolute positioning/sizing.
        for (int i = 0; i < layerStrings.length; i++) {
            JLabel label = createColoredLabel(layerStrings[i],layerColors[i], origin);
            layeredPane.add(label, new Integer(i));
            //origin.x += offset;
           // origin.y += offset;
        }
 
        //Create and add the Duke label to the layered pane.
        
      
 
        //Add control pane and layered pane to this JPanel.
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(layeredPane);
    }
 
    /** Returns an ImageIcon, or null if the path was invalid. */
   
 
    //Create and set up a colored label.
    private JLabel createColoredLabel(String text,Color color,Point origin) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
       // label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(origin.x, origin.y, 140, 140);
        return label;
    }
 
    //Create the control pane for the top of the frame.
    
 
    //Make Duke follow the cursor.
   
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("LayeredPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new LayeredPaneDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}