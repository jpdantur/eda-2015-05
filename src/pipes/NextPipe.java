package pipes;

import java.awt.Point;

import pipes.Pipe.Direction;

public class NextPipe {

	Point next;
	Direction extreme;
	
	public NextPipe(Point next, Direction extreme){
		this.next = next;
		this.extreme = extreme;
	}

	public Point getNext() {
		return next;
	}

	public Direction getExtreme() {
		return extreme;
	}
}
