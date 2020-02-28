/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 12:58 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard;

import Paint.DrawingBoard.CursorActions.SetDefaultCursor;
import Paint.DrawingBoard.CursorActions.SetEndCursor;
import Paint.DrawingBoard.CursorActions.SetStartCursor;
import Paint.DrawingBoard.Listeners.DrawingMouseListener;
import Paint.Engine.PaintGrid;
import com.sun.glass.events.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Graphics;

public class DrawingBoard extends JPanel  {
	private final PaintGrid grid;
	private final int scaleFactor = 25;
	private final int scaledWidth, scaledHeight;
	
	public DrawingBoard(int gridWidth, int gridHeight) {
		this.grid = new PaintGrid(gridWidth, gridHeight, scaleFactor);
		this.scaledWidth = grid.width * scaleFactor;
		this.scaledHeight = grid.height * scaleFactor;
		DrawingMouseListener mouseListener =
				new DrawingMouseListener(this, grid);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		addKeyboardActions();
	}
	
	private void addKeyboardActions() {
		// Sets up actions
		SetDefaultCursor defaultCursor = new SetDefaultCursor(this);
		SetStartCursor startCursor = new SetStartCursor(this);
		SetEndCursor endCursor = new SetEndCursor(this);
		
		// Inputs
		InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_CONTROL, 0, false),
				"controlPress");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_CONTROL, 0, true),
				"controlRelease");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_ALT, 0, false),
				"altPress");
		inputMap.put(KeyStroke.getKeyStroke(
				KeyEvent.VK_ALT, 0, true),
				"altRelease");
		
		// Actions
		ActionMap actionMap = getActionMap();
		actionMap.put("controlPress", startCursor);
		actionMap.put("controlRelease", defaultCursor);
		actionMap.put("altPress", endCursor);
		actionMap.put("altRelease", defaultCursor);
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
