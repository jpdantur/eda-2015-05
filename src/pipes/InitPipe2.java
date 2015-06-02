package pipes;

public class InitPipe2 extends Pipe{

	public InitPipe2() {
		extreme1 = Direction.NORTH;
		extreme2 = null;
		exit = extreme1;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new InitPipe2();
	}
	
	public int hashCode(){
		return 9;
	}
}
