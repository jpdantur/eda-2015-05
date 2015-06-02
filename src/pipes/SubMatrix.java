package pipes;

import java.util.HashMap;
import java.util.Map;

public class SubMatrix {

	Pipe[][] subMat;
	Map<Pipe,Integer> map;
	
	
	public SubMatrix(Pipe p1, Pipe p2, Pipe p3, Pipe p4){
		subMat = new Pipe[2][2];
		subMat[0][0] = p1;
		subMat[0][1] = p2;
		subMat[1][0] = p3;
		subMat[1][1] = p4;
		map = new HashMap<>();
		
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 2; j++){
				if(subMat[i][j] != null && subMat[i][j].hashCode() != 20){
					if(!map.containsKey(subMat[i][j])){
						map.put(subMat[i][j], 1);
					}
					else{
						map.put(subMat[i][j], map.get(subMat[i][j]) + 1);
					}
				}
			}
	}
	
	
	public int hashCode(){
		int ret = 0;
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 2; j++){
				ret *= 10;
				ret += 9;
				ret *= 10;
				ret += (subMat[i][j] == null) ? 0 : subMat[i][j].hashCode();	
			}
		return ret;
	}
	
	public boolean equals(Object other){
		return this.hashCode() == other.hashCode();
	}
	
	Pipe[][] getSubMat(){
		return subMat;
	}
	
	Map<Pipe, Integer> getMap(){
		return map;
	}
	
}
