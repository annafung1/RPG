
package rpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;  //handle exception ofc
import java.io.FileWriter;   //writes to text file imp. 
import java.util.Scanner;  //for scanning user input/what they type

public class RPG {

public static void deleteSave () {
 File heroFile = new File ("Hero.txt");
  if (heroFile .delete()) { 
      System.out.println("Deleted save:" + heroFile .getName());
    } else {
      System.out.println("Could not delete file!");
    } 
  } 
     

}
