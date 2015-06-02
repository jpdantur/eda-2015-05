package pipes;


public class Pipe2 extends Pipe{

	public Pipe2() {
		extreme1 = Direction.EAST;
		extreme2 = Direction.NORTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe2();
	}
	
	public int hashCode(){
		return 2;
	}
}
