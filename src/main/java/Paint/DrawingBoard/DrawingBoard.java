/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 12:58 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard;

import Paint.DrawingBoard.Actions.SetMode;
import Paint.DrawingBoard.Listeners.DrawingMouseListener;
import com.sun.glass.events.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.InputEvent;

public class DrawingBoard extends JPanel  {
	public final static int OBSTACLE_MODE = 0;
	public final static int START_MODE = 1;
	public final static int END_MODE = 2;
	
	private final PaintGrid grid;
	private final int scaleFactor = 25;
	private final int scaledWidth, scaledHeight;
	private int currentMode;
	
	public DrawingBoard(int gridWidth, int gridHeight) {
		this.grid = new PaintGrid(gridWidth, gridHeight, scaleFactor);
		this.scaledWidth = grid.width * scaleFactor;
		this.scaledHeight = grid.height * scaleFactor;
		this.currentMode = 0;
		DrawingMouseListener mouseListener =
				new DrawingMouseListener(this, grid);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		addKeyboardActions();
	}
	
	public int getCurrentMode() {
		return this.currentMode;
	}
	
	public void setMode(int mode) {
		if (mode <= 2) {
			this.currentMode = mode;
		}
	}
	
	private void addKeyboardActions() {
		// Inputs
		InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK),
				"obstacle");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_NUMPAD1, InputEvent.CTRL_DOWN_MASK),
				"obstacle");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK),
				"start");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_NUMPAD2, InputEvent.CTRL_DOWN_MASK),
				"start");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK),
				"end");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_NUMPAD3, InputEvent.CTRL_DOWN_MASK),
				"end");

		
		// Actions
		ActionMap actionMap = getActionMap();
		actionMap.put("obstacle", new SetMode(this, OBSTACLE_MODE));
		actionMap.put("start", new SetMode(this, START_MODE));
		actionMap.put("end", new SetMode(this, END_MODE));
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(scaledWidth + 1, scaledHeight + 1);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		grid.paint(g);
	}
}
