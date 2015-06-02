package pipes;

public class Pipe3 extends Pipe{

	public Pipe3() {
		extreme1 = Direction.EAST;
		extreme2 = Direction.SOUTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe3();
	}
	
	public int hashCode(){
		return 3;
	}
}
