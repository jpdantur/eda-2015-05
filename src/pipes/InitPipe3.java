package pipes;

public class InitPipe3 extends Pipe{

	public InitPipe3() {
		
		extreme1 = Direction.EAST;
		extreme2 = null;
		exit = extreme1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new InitPipe3();
	}
	
	
	public int hashCode(){
		return 10;
	}
}
