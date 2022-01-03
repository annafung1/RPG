
package rpg;

import java.io.File;
import java.io.IOException;
import java.io.*;

public class Monsters {
    
//fields  
 protected int level;    
 protected int str;
 protected int hp ;
 protected int mp ;
 protected int def; 
 protected int gold;
 protected int exp;
 int randomNumber; 

 public Monsters (int level, int str, int hp,int mp, int def,int gold, int exp){
     
     this.level=level;
     this.str=str;
     this.hp=hp;
     this.mp=mp;
     this.def=def;
     this.gold=gold;
     this.exp=exp;
 }
 
 public static void monsterFile (){
    
   try { File monsterFile = new File ("Monster.txt");   
            if (monsterFile.createNewFile()){
                System.out.println ("Saved");
            }
            else{System.out.println ("New Save file created! \n");
            }  
    }
    catch (IOException e) {
            System.out.println ("Error: File not saved!");
            e.printStackTrace();   
    }
}

@Override 
public String toString (){
    return level +  " " + str +  " " + hp+   " " + mp +  " " +def  +  " " +gold + " "+exp;
}

    
public static void main() {
    Monsters slime= new Monsters(0, 1, 5, 1, 3, 5,2); 
    Monsters pig= new Monsters (1, 2, 10, 4, 2,10,5);
    Monsters chicken = new Monsters (2,4,15, 8, 5, 20,10);
    Monsters fish = new Monsters (3,5,15, 8, 5, 25,20);  
       
    slime.toString();
    pig.toString();
    chicken.toString();
    fish.toString();
    
    try {
      FileWriter writeMon = new FileWriter("Monster.txt");
      writeMon.write(slime.toString()+"\n");
      writeMon.write (pig.toString()+"\n");
      writeMon.write (chicken.toString()+"\n");
      writeMon.write (fish.toString()+"\n");
      writeMon.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    }
 
}
   
