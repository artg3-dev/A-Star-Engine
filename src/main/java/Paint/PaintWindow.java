/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 11:20 AM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint;

import Paint.DrawingBoard.DrawingBoard;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class PaintWindow implements Runnable{
	private final JFrame frame;
	
	public PaintWindow() {
		frame = new JFrame("A* Paint");
	}
	
	@Override
	public void run() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}
	
	private void createComponents(Container container) {
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		DrawingBoard canvas = new DrawingBoard(20, 20);
		container.add(canvas, c);
	}
}
