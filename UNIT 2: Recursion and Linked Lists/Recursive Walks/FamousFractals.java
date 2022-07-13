import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FamousFractals extends JFrame implements ActionListener {

	public static void main(String[] args) {
		JFrame window = new FamousFractals();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(50, 50);
		window.setVisible(true);
	}
	
	private int currentNumberOfPoints;
	private InputCanvas input;
	private DisplayCanvas display;
	private JComboBox pointCountSelect;
	private JComboBox recursionLevelSelect;
	
	public FamousFractals() {
		super("Famous Fractals");
		pointCountSelect = new JComboBox();
		recursionLevelSelect = new JComboBox();
		display = new DisplayCanvas();
		input = new InputCanvas(display);
		JPanel canvases = new JPanel();
		canvases.setBackground(Color.DARK_GRAY);
		canvases.setLayout(new GridLayout(1,2,5,5));
		canvases.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));
		canvases.add(input);
		canvases.add(display);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("Number of Points: "));
		bottom.add(pointCountSelect);
		for (int i = 3; i < 10; i++)
			pointCountSelect.addItem("" + i);
		pointCountSelect.setSelectedIndex(2);
		currentNumberOfPoints = 5;
		input.setPointCount(5);
		pointCountSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evt) { 
				if (currentNumberOfPoints != pointCountSelect.getSelectedIndex() + 1) {
					currentNumberOfPoints = pointCountSelect.getSelectedIndex() + 3;
					input.setPointCount(currentNumberOfPoints);
					checkMaxRecursionLevel();
					display.setRecursionLevel(recursionLevelSelect.getSelectedIndex() + 1);
;				}
			}
		});
		JButton clear = new JButton("Clear");
		bottom.add(Box.createHorizontalStrut(40));
		bottom.add(clear);
		clear.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				input.setPointCount(currentNumberOfPoints);
			}
		});
		bottom.add(Box.createHorizontalStrut(40));
		bottom.add(new JLabel("Recursion Level"));
		bottom.add(recursionLevelSelect);
		checkMaxRecursionLevel();
		recursionLevelSelect.setSelectedIndex(3);
		display.setRecursionLevel(4);
		recursionLevelSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				display.setRecursionLevel(recursionLevelSelect.getSelectedIndex() + 1);
			}
		});
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(canvases, BorderLayout.CENTER);
		getContentPane().add(bottom,BorderLayout.SOUTH);
		setJMenuBar(makeMenus());
		pack();
		setResizable(false);
	}
	
	private void checkMaxRecursionLevel() {
	    int max	= (int)(Math.log(100000)/Math.log(currentNumberOfPoints-1));
	    int curMax = recursionLevelSelect.getItemCount();
	    if (curMax < max) {
	    	for (int i = curMax+1; i <= max; i++)
	    		recursionLevelSelect.addItem("" + i);
	    }
	    else if (curMax > max) {
	    	int selected = recursionLevelSelect.getSelectedIndex();
	    	if (selected >= max)
	    		recursionLevelSelect.setSelectedIndex(max-1);
	    	for (int i = curMax-1; i >= max; i--)
	    		recursionLevelSelect.removeItemAt(i);
	    }
	}
	
	private JMenuBar makeMenus() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Examples");
		menubar.add(menu);
		String[] itemList = new String[] {
				"Koch Curve", "Square Koch Curve", "Dragon Curve", "C-Curve", "Sierpinski Triangle",
				 null, "Quit"
		};
		for (String cmd : itemList) {
			if (cmd == null) {
				menu.addSeparator();
			}
			else {
				JMenuItem item = new JMenuItem(cmd);
				item.addActionListener(this);
				menu.add(item);
			}
		}
		return menubar;
	}
	
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd.equals("Quit"))
			System.exit(0);
		else if (cmd.equals("Koch Curve")) {
			int height = (int)(400/Math.sqrt(3)); 
			int[] coords = { 50,250, 50+400/3,250, 250,350-height, 50+800/3,250, 450,250 };
			installExample(7,coords);
		}
		else if (cmd.equals("Square Koch Curve")) {
			int[] coords = { 50,250, 50+400/3,250, 50+400/3,250-400/3, 50+800/3,250-400/3, 50+800/3,250, 450,250 };
			installExample(6, coords);
		}
		else if (cmd.equals("Dragon Curve")) {
			int h = (int)(400/(2*Math.sqrt(3)));
			int[] coords = { 50,250, 250,250-h, 250,250+h, 450,250}; 
			installExample(8, coords);
		}
		else if (cmd.equals("C-Curve")) {
			int h = 120;
			int[] coords = {130,350, 250,350-h, 370,350}; 
			installExample(14, coords);
		}
		else if (cmd.equals("Sierpinski Triangle")) {
			int h = (int)(400*Math.sqrt(3)/4);
			int[] coords = { 50,400, 250,400, 150,400-h, 350,400-h, 250,400, 450,400}; 
			installExample(7, coords);
		}
	}
	
	private void installExample(int recursionLevel, int[] coordinates) {
		int pointCt = coordinates.length / 2;
		currentNumberOfPoints = pointCt;
		pointCountSelect.setSelectedIndex(pointCt - 3);
		input.install(coordinates);
		checkMaxRecursionLevel();
		display.setRecursionLevel(recursionLevel);
		recursionLevelSelect.setSelectedIndex(recursionLevel-1);
	}
}
