package pipes;


public class Pipe5 extends Pipe{

	public Pipe5() {
		extreme1 = Direction.SOUTH;
		extreme2 = Direction.NORTH;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Pipe5();
	}
	
	public int hashCode(){
		return 5;
	}
}
