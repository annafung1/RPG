
package rpg;
import java.util.Random;
import rpg.Monsters;
import java.io.*;
import java.util.ArrayList;

public class Map {
    protected  int choice;
    protected  ArrayList<Monsters> monstersList;
    protected int highest;
                
    public Map (int choice){
        this.choice = choice;
        this.monstersList = new ArrayList<>();
        this.highest = 3;
        readMonsterFile();
    }
    
    public void readMonsterFile(){
        // reading from Monsters file
        String line;
        Monsters monster;
        int level;
        int str;
        int hp;
        int mp;
        int def;
        int gold;
        int exp;
        
        // populate monstersList
        try {
            BufferedReader monsterFile = new BufferedReader(new FileReader("Monster.txt"));

            while ((line = monsterFile.readLine()) != null) {     
                System.out.println (line);
                String[] monsterVals = line.split("\\s+");
                
                level = Integer.parseInt(monsterVals[0]);
                str = Integer.parseInt(monsterVals[1]);
                hp = Integer.parseInt(monsterVals[2]);
                mp = Integer.parseInt(monsterVals[3]);
                def = Integer.parseInt(monsterVals[4]);
                gold = Integer.parseInt(monsterVals[5]);
                exp = Integer.parseInt(monsterVals[6]);

                monster = new Monsters(level, str, hp, mp, def, gold,exp);
                monstersList.add(monster);
            }
        }
        catch(Exception e) {
            System.out.println (e);
            e.getStackTrace();
        }
      }
    public Monsters move (){
        // samples a monster
        Random randNum= new Random ();
        int easyMob = randNum.nextInt(highest);
        return monstersList.get(easyMob);
    } 
     public static void main(String[] args) {
        System.out.println ("Debugging...");
        Map map1 = new Map(1);
        System.out.println ("Get a random monster...");
        System.out.println (map1.move ());
        System.out.println ("Get a random monster...");
        System.out.println (map1.move ());
        
    }
}


