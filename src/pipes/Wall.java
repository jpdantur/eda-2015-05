package pipes;

import pipes.Pipe.Direction;

public class Wall extends Pipe{

	public Wall() {
		extreme1 = null;
		extreme2 = null;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Pipe getPipe() {
		// TODO Auto-generated method stub
		return new Wall();
	}
	
	public int hashCode(){
		return 20;
	}


}
