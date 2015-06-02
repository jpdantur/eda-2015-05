package pipes;

public class Pipe6 extends Pipe{

	public Pipe6() {
		extreme1 = Direction.WEST;
		extreme2 = Direction.EAST;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe6();
	}
	
	public int hashCode(){
		return 6;
	}
}
