package pipes;

public class InitPipe1 extends Pipe{

	public InitPipe1() {
		extreme1 = Direction.SOUTH;
		extreme2 = null;
		exit = extreme1;

		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new InitPipe1();
	}

	public int hashCode(){
		return 8;
	}
	
}
