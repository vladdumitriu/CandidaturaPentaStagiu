package puddleProblem;

public class Volume{
	private int start;
	private int end;
	private int value;
	Volume(int start, int end, int value){
		this.start=start;
		this.end=end;
		this.value=value;
	}
	
	public int getStart() {
		return start;
	}
	public int getEnd() {
		return end;
	}
	public int getValue() {
		return value;
	}
	
}
