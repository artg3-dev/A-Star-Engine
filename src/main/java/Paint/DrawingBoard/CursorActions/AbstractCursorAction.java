/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 1:28 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.CursorActions;

import Paint.DrawingBoard.DrawingBoard;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public abstract class AbstractCursorAction extends AbstractAction {
	private final DrawingBoard canvas;
	
	public AbstractCursorAction(DrawingBoard canvas) {
		this.canvas = canvas;
	}
	
	abstract void setCursor();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor();
	}
}
