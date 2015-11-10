//Maria Levandoski CS1632 Deliverable 4
//generate a minimum of 100 different random arrays of different sizes
//test at least 3 different properties of sorting them
package cs1632;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class Deliverable4 {
  @SuppressWarnings("unchecked")

  private static int[] testArray;
  private static int[] dupTestArray;

  public Deliverable4(int[] testArray){
    this.testArray = testArray;
    this.dupTestArray = testArray;
  }

  @Parameters
  public static Collection<Object[]> arrays(){
    Collection<Object[]> arrays = new ArrayList<Object[]>();
    for (int i = 0; i < 100; i++) {
      int[] newArray = magic();
      Object[] arrayObj = new Object[] { newArray};
      arrays.add(arrayObj);
    }
    return arrays;
  }

  //creates randomly sized array of random ints
  private static int[] magic(){
    Random generator = new Random();
    int SIZE = generator.nextInt(32) + 1; //capped size to not run out of memory in JVM
    int[] newArray = new int[SIZE];
    for (int i = 0; i < newArray.length; i++){
      newArray[i] = generator.nextInt();
    }
    return newArray;
  }

  //Property 1: Size of arrays
  //Output array is the same size as passed-in array
  @Test
  public void testSize() {
    int sizeOrig = testArray.length;
    Arrays.sort(testArray);
    int sizeSorted = testArray.length;
    assertEquals(sizeOrig,sizeSorted);
  }

  //Property 2: Monotonically increasing
  //Values in output array always increasing or staying the same
  @Test
  public void testMonoInc() {
    int left = -1;
    int right = -1;
    for (int i = 0; i < testArray.length - 1; i++){
      left = testArray[i];
      right = testArray[i+1];
    }
    assertTrue("Left index" + left + "should not be greater than right index" + right, left <= right);
  }

  //Property 3: Purity
  //Running sort twice on the same input array should always result in the same output array
  @Test
  public void testPurity(){
    dupTestArray = testArray;
    Arrays.sort(testArray);
    Arrays.sort(dupTestArray);
    assertArrayEquals(testArray,dupTestArray);
  }

  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("cs1632.Deliverable4");
  }
}
