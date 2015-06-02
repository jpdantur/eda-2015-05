package pipes;

public class InitPipe4 extends Pipe{

	public InitPipe4() {
		extreme1 = Direction.WEST;
		extreme2 = null;
		exit = extreme1;

		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new InitPipe4();
	}
	
	public int hashCode(){
		return 11;
	}
}
