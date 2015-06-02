package pipes;


public class Pipe4 extends Pipe{

	public Pipe4() {
		extreme1 = Direction.WEST;
		extreme2 = Direction.SOUTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe4();
	}
	
	public int hashCode(){
		return 4;
	}
}
