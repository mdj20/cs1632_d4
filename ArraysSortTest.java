/*
 * 
 * 	Matthew Jeffreys
 * 	CS1632 Deliverable 4
 * 	Arrays.sort() property test
 * 	Bill Laboon
 * 
 */




import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArraysSortTest {
	
	static Object[] arrays;
	static Random rand;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		  //set random time
		  rand = new Random(System.currentTimeMillis());
				  
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
	}

	@Test
	public void testSortedOrder(){
		
		resetArrays();
	
		// set actual results array
		boolean actual[] = new boolean[arrays.length];
		
		// for each array, in arrays...
		for (int i = 0 ; i < arrays.length ; i ++){

			// pull array from arrays
			TestClass tc[] = (TestClass[]) arrays[i];
			
			//sort array
			Arrays.sort(tc);
			
		}
	
		// test each array
		for (int i = 0 ; i < arrays.length ; i++){
			
			// cast object as TestCLass array
			TestClass tc[] = (TestClass[]) arrays[i];
			
			// run test and return result  (see test below)
			actual[i] = inOrder(tc);
			
			
		}
		
		//
		for ( boolean b : actual ){

			assertTrue(b);

		}
	}

	
	
	@Test 
	public void testStablity(){
			
		resetArrays();
		
		// results of test
		boolean results[] = new boolean[arrays.length];
		Arrays.fill(results, true);
		
		//set up arrays for stability testing
		
		for (int i = 0 ; i < arrays.length ; i++){
			
			//get individual array
			TestClass array[] = (TestClass[]) arrays[i];
			
			// determine duplicate location
			int size = array.length;
			int interval = (size-1)/5;
			
			//determine duplicate value and id
			int dup = array[1].value();
			int lastid = array[size-1].id()+1;
			
			System.out.println("Duplicated: "+dup);
			
			//set duplicate values, keeping with increasing id numbers (duplicate values different IDs)
			array[interval] = new TestClass(dup,lastid++);
			array[interval*2] = new TestClass(dup,lastid++);
			array[size-interval] = new TestClass(dup,lastid++);
			array[size-1] = new TestClass(dup,lastid);
			
			//sort
			Arrays.sort(array);
			
			// find duplicate values in array
			int temp = -5;
			int index = 0;
			
			//via linear search 
			if (array[index].value()!=dup){
				
				while (temp!=dup){
					temp = array[++index].value();
				}
			
			}
			
			// at first index of duplicate value, the next 4 should be the same value but have diffrent ids
			for(int j = index ; j < index+4 ; j++){
				
				// if next four TestCases have either different values or same id, then stability test failed
				if (!(array[j].value() == array[j+1].value() && array[j].id() != array[j+1].id())){
					
					results[i] = false;
				
				}
			}
			
		}
		
		//assert all arrays pass test
		for (boolean b : results){
			assertTrue(b);
		}
	}
	
	@Test 
	public void testElementConsistency(){
		
		resetArrays();
		
		// results of test
		boolean results[] = new boolean[arrays.length];
		Arrays.fill(results, true);
		
		// test for each array
		
		for (int i = 0 ; i < arrays.length ; i++){
			
			
			
			// get specific array
			TestClass array[] = (TestClass[]) arrays[i];
			
			
			HashSet<Integer> setBefore = new HashSet<Integer>();
			HashSet<Integer> setAfter = new HashSet<Integer>();
			
			// add all TestCLass ids to setBefore 
			for (TestClass tc : array){
				setBefore.add(tc.id());
			}
			
			// Sort
			Arrays.sort(array);
			
			// add all TestCLass ids to setAfter
			for (TestClass tc : array){
				setAfter.add(tc.id());
			}
			
			// test number of corect values. if not, set result[i] to false.
			if (setBefore.size() != setAfter.size()){
				results[i] = false;
			}
			
			//test if each TestClass id is found in both sets, if not set result[i] to false.
			for (TestClass tc : array){
				
				if( !setBefore.contains(tc.id()) || !setAfter.contains(tc.id())){
					results[i] = false;
				}
			}
			
			
		}
		
		
		for (boolean b : results){
			assertTrue(b);
		}
		
		
	}
	
	
	private static void resetArrays(){
		
		  // setup 100 arrays of test classes each of varying length
		  ArrayList<Object> objectArrayList = new ArrayList<Object>();
		  
		  // create 100 arrays of test classes 
		  for ( int i = 0 ; i < 100 ; i++){
			  
			  // choose size of array
			  int size = rand.nextInt(991)+10;
		
			  TestClass tempArray[] = new TestClass[size];
			  
			  for (int j = 0 ; j < size ; j++){
				  
				  tempArray[j] = new TestClass(rand.nextInt(),j);
				
				  
			  }
			  
			  // add each array to container;
			  objectArrayList.add(tempArray);
			  
		  }
		  
		  // create array from arrayList
		  arrays = objectArrayList.toArray();
		
	}
	
	
	// called by testSortedOrder tests and individual array and returns pass result
	private boolean inOrder(TestClass tc[]){
		
		// set result as pass initialy
		boolean correct =  true; 
			
		// test order of all but last TestClass in array.
		for( int i = 0 ; i < tc.length-1 ; i++ ){
			
			// if order is correct set true
			if (tc[i].value() > tc[i+1].value()){
				correct = false;
			}
		
		}
			
		// return pass result
		return correct;
		
	}
	
	
	
}
