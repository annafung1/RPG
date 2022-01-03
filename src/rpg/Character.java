
package rpg;
import java.util.ArrayList;

public class Character {
    
     //fields

       protected int str;
       protected int mp ;
       protected int hp ;
       protected int gold;
       protected int def;
       protected int level; 
       protected int exp;
       protected int totalExp; 
             // protected int crit;
       
    //constuctor-------------------------

      public Character ( int level, int str, int mp, int hp, int def,int gold, int exp, int levelExp){
          
        this.level=level;
        this.str= str; 
        this.hp= hp;
        this.mp= mp;
        this.def= def; 
        this.gold= gold;
        this.exp=exp; 
        this.totalExp= levelExp; 
        //heroData.write (stats);
    
    }
     public ArrayList <Integer> Stats = new ArrayList <> ();

    //set stats-------------------------
    //required: level
    //modifies: player stats
    //effects:  If level is 01 then new character will have the default stats ; else every time character levels this method 
    //will be called and stats will be raised by a multiplier 
    
      public ArrayList setStats (int level){
       
       
               
            if (level>1){
                str= str +5;
                hp= hp+10;
                mp=mp+10;
                def=def+5;
                gold=gold+1000;
                
                
                
            }
                Stats.add (str); 
                Stats.add(hp); 
                Stats.add(mp); 
                Stats.add(def); 
                Stats.add(gold); 
                
      // System.out.println (" \n Your stats are:" )  ;
       return Stats;
       
        
    }
    //Required: player level
    //Modifies: the exp needed for player to level up
    //Effect: When this method is called it will calculate the new amount of exp player needs to levelup 
    public int LevelExp (int level){
      
             totalExp= level*50;   
             return totalExp;  
             
    }
    
    //Required: players exp 
    //modies: level of character
    //Effect: When exp is greater or equal to the amount needed to level up; player will 1) level up 2)increase stats due to level up  3)set new exp for next level
    public int Levelup (int exp){
        
                if (exp>=totalExp){
                   level++;
                   this.setStats (level);
                   LevelExp (level); 
                
                }
                return level;                 
    }
    
   @Override 
    public String toString (){
        System.out.println (Stats) ;
        return null;
        }
        
            
   
    
    public static void main(String[] args) { 
    
        
        
        //*******for TESTING PURPOSES ONLY **********************************************
        
        
     
     Character Player1 = new Character(0,0,0,0,0,0,0,0);
  
     System.out.println("Player's current stats at level 1 should be 10, 10, 100, 50, 5, 2000, 10:" +Player1.setStats(Player1.level));

     Player1.LevelExp (Player1.level);
     System.out.println("Player's level exp should be 50:" + Player1.LevelExp (Player1.level));
     
     int exp=101;
     
     System.out.println("You had added 101 exp. You should be able to level up to 2 now. " +  Player1.Levelup (exp));
     System.out.println("Player's level should now be 2:" + Player1.level);

     System.out.println("Player's level should now be exp should be 100 exp:" + Player1.LevelExp(Player1.level));
     
     
     //**Can't use line below bc level up already calls setStats so you are setting it twice! Also note: the line it updates is before the level 2 confirmation check ofc 
    // System.out.println("Player stats should be uhhh ummm 15, 15, 60, 110 etc." + Player1.setStats(Player1.level));
        Player1.toString (); 
         
     
    }
    
    
   
    
    
    //getter
    
    
  
    
}
