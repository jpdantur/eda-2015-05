package pipes;

public abstract class Pipe {

	protected Direction extreme1;
    protected Direction extreme2;
    protected Direction entrance = null;
	protected Direction exit = null;
	
	
    public Direction getEntrance() {
		return entrance;
	}

    public abstract Pipe getPipe();

	public void setEntrance(Direction entrance) {
		this.entrance = entrance;
		if(this.entrance != extreme1)
			exit = extreme1;
		else
			exit = extreme2;
	}


	public Direction getExit() {
		return exit;
	}


	public void setExit(Direction exit) {
		this.exit = exit;
	}


//    private int quantity;
    




	public enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    };
	
    
    public boolean isCompatible(Direction d){
        if(extreme1.equals(d) || extreme2.equals(d))
            return true;
        return false;
    }
    
    public String toString(){
    	return String.valueOf(this.hashCode());
    }
	
    public boolean equals(Object other){
    	return this.hashCode() == other.hashCode();
    }
}
