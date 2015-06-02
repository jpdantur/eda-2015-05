package pipes;


public class Pipe1 extends Pipe{

	public Pipe1() {
		extreme1 = Direction.WEST;
		extreme2 = Direction.NORTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe1();
	}
	
	public int hashCode(){
		return 1;
	}
}
