package pipes;

import pipes.Pipe.Direction;

public class Pipe7 extends Pipe{

	private Direction extreme3;
    private Direction extreme4;
	
	public Pipe7() {
		extreme1 = Direction.SOUTH;
		extreme2 = Direction.NORTH;
		// TODO Auto-generated constructor stub
		
		extreme3 = Direction.EAST;
		extreme4 = Direction.WEST;
	}
	
	public boolean isCompatible(Direction d){
        return true;
    }

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe7();
	}
	
	public void setEntrance(Direction entrance) {
		this.entrance = entrance;
		if(this.entrance == extreme1)
			exit = extreme2;
		else if(this.entrance == extreme2)
			exit = extreme1;
		else if(this.entrance == extreme3)
			exit = extreme4;
		else if(this.entrance == extreme4)
			exit = extreme3;
	}
	
	public int hashCode(){
		return 7;
	}
}
