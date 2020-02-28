/*------------------------------------------------------------------------------
 @Author Art Garcia
 Copyright (c) 2/2020
 Project: AStarEngine
 Last Modified: 2/28/20, 1:22 PM
 License: GNU Lesser General Public License v3.0
 -----------------------------------------------------------------------------*/

package Paint.DrawingBoard.CursorActions;

import Paint.DrawingBoard.DrawingBoard;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class SetStartCursor extends AbstractCursorAction {
	
	public SetStartCursor(DrawingBoard canvas) {
		super(canvas);
	}
	
	@Override
	void setCursor() {
		System.out.println("Start");
	}
}
