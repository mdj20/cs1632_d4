


public class TestClass implements Comparable<TestClass>  {
	
	private int id;	 // value to be compared 
	private int value;  // unique identifier, this will be used as a 2nd metric so the test can assert sort()'s stability.
	
	
	TestClass(int v , int setId){
		value = v;
		id = setId;
	}
	
	public int id(){
		return id;
	}
	
	public int value(){
		return value;
	}
	

	@Override
	public int compareTo(TestClass tc) {
		// TODO Auto-generated method stub
		
		if (this.value < tc.value() ){
			return -1;
		}
		else if  (this.value > tc.value()){
			return 1;
		}
		else {
			return 0;
		}
	}
	

}
