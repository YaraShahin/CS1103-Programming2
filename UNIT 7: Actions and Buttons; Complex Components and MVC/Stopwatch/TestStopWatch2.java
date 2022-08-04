import java.awt.*;
import javax.swing.*;

/**
 * A trivial applet that tests the StopWatchLabel2 component.
 * The applet just creates and shows a StopWatchLabel2.
 */

public class TestStopWatch2 extends JApplet {

   public void init() {

      StopWatchLabel2 watch = new StopWatchLabel2();
      watch.setFont( new Font("SansSerif", Font.BOLD, 24) );
      watch.setBackground(Color.WHITE);
      watch.setForeground( new Color(180,0,0) );
      watch.setOpaque(true);
      getContentPane().add(watch, BorderLayout.CENTER);

   }

}
