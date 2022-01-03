
package rpg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;



public class RPG_Screen extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("RPG made by Anna Fung"); 
 
        //scene 1
        VBox menu= new VBox ();
        Label Intro = new Label ("Welcome to my rpg!");
        Intro.setTextFill(Color.web("#a32e00")); //color in hex; maroon/mahogany ; https://www.colorhexa.com/a32e00
        menu.getChildren().add(Intro); 
        Scene startScene= new Scene (menu,400, 200);
        
        Label saveLabel= new Label (""); 
        
        //scene 2
        Label textLabel= new Label ("");
        VBox switchVbox = new VBox (); 
        Scene scene2= new Scene( switchVbox, 500, 300);
        switchVbox.getChildren().addAll(textLabel);
        TextField text= new TextField();
        
        //scene 3
        Label textLabel3= new Label ("");
        VBox switchVbox3 = new VBox (); 
        Scene scene3= new Scene( switchVbox3, 500, 300,Color.BLUE);
        
        switchVbox3.getChildren().addAll(textLabel3);
        Button fight= new Button ("Fight!!");
        Button submit = new Button ("submit");
        
       
        Button Continue = new Button ("Continue"); 
        Continue.setOnAction(value1 -> { 
                primaryStage.setScene(scene2);
                primaryStage.show();
                switchVbox.setAlignment(Pos.CENTER);
                saveLabel.setText("Please select which map you would like to explore!");
                textLabel.setText ("Sunny Meadows (Recommended level: 1-10) ; Please enter 1");

               
                text.setPrefWidth(100);
                text.setMaxWidth(100);       
                switchVbox.getChildren().add(text);
   
                Button returnMenu= new Button ("Back to Start Menu");
                    returnMenu.setOnAction(val -> {
                     primaryStage.setScene(startScene);
                 });
                    
                
                switchVbox.getChildren().add(submit);
                switchVbox.getChildren().add(returnMenu);
                switchVbox.setAlignment(Pos.CENTER);
                primaryStage.show();

                submit.setOnAction(action -> { 

                    String choice= text.getText();
                    int convertChoice;
                    convertChoice = Integer.parseInt (choice);
                    Map map = new Map(convertChoice);

                   
                    switchVbox3.setAlignment(Pos.CENTER);
                    switchVbox3.getChildren ().add(fight);
                    switchVbox3.getChildren().add(returnMenu);
                    primaryStage.setScene(scene3);
                    primaryStage.show();
                    
                    
                fight.setOnAction(action1 -> { 
                Monsters monster;

                    // create character
                    Character character =  new Character(1, 2, 100, 50, 10, 2000, 0, 50);

                    // sample a monster
                    monster = map.move();

                    // fight monster
                    double monsterHp = monster.hp;
                    double characterHp = character.hp;
                    double initMonHp;
                    double initCharHp;
                    double damage;
                    double goldDrop= monster.gold;
                    double goldTotal= character.gold; 

                    while (monsterHp > 0.) {
                        initMonHp = monsterHp;
                        initCharHp = characterHp;

                        // person attacks monster
                        damage = character.str - monster.def*0;
                        monsterHp = monsterHp - damage;
                        if (monsterHp < 0) {
                            monsterHp = 0;
                        }
                        Label monHp= new Label ("You did " +  damage + " damage! " +  "Monster HP " + initMonHp + " -> " + monsterHp);
                        switchVbox3.getChildren ().add(monHp);

                        if (monsterHp <= 0) {
                             Label battleComplete= new Label ("You win!");
                             switchVbox3.getChildren ().add(battleComplete);
                         }
                        else {
                            damage = monster.str - character.def*0;
                            characterHp = characterHp - damage ;
                            Label heroHp= new Label ("Monster did " +  damage + " damage. " + "Hero HP " + initCharHp + " -> " + characterHp);
                            switchVbox3.getChildren ().add(heroHp);
                            }
                    }
                    });
             });                                               
    });
    Button newSave =new Button ("New Save");
    newSave.setOnAction(value2 -> { //can't use e; used in exception handling 
        Monsters.monsterFile(); //creates new Monster Text File
        
        Monsters.main();
        
         try { 
                File heroFile = new File ("Hero.txt");
                    if (heroFile.createNewFile()){
                        
                        Stage saveStage = new Stage();
                        saveStage.setTitle ("New Save Creation");
                        VBox saveMenu= new VBox ();
                        saveLabel.setText("New Save file created! \n");
                        
                        Label heroName = new Label ();
                        heroName.setText ("Please enter a name for your Character:");
                        
                        text.setPrefWidth(100);
                        text.setMaxWidth(100);
                        
                        Button submit1= new Button ("Submit");
                        submit.setOnAction(value3 -> {
                        try {
                            FileWriter heroData = new FileWriter ("Hero.txt");
                            heroName.setText("Your character name is: " + text.getText());
                            saveLabel.setText("Character Created! \n");
                            String saveName;
                            saveName= text.getText();
                            heroData.append(saveName);
                            heroData.write ("\n");
                            
                            Character Player1 = new Character(0,0,0,0,0,0,0,0);
                            Player1.level=1;
                            Player1.setStats (Player1.level);
                            heroData.append ("1,0,0,0,0,0,0,0,0"); 
                            heroData.close ();       
                         }
         
                        catch (IOException e){
                            System.out.println ("Error: File not saved!"); 
                            e.printStackTrace(); //java throw exception method which tells you what line error at and what it is 
                        } 
                         
                         });
  
                       Button exit= new Button ("Go back To Main Menu");
                       exit.setOnAction(value4 -> {
                              saveStage.close(); 
                       });
                        saveMenu.getChildren().add(saveLabel);
                        saveMenu.getChildren().add(heroName);
                        saveMenu.getChildren().add(text);
                        saveMenu.getChildren().add(submit);
                        saveMenu.getChildren().add(exit);
                        saveMenu.setAlignment(Pos.CENTER);
                        Scene saveScene= new Scene (saveMenu,400, 200);
                        saveStage.setScene (saveScene);
                        saveStage.show();
                           }
                    else{
                         saveLabel.setText("Existing Save File detected! Please delete save file first!");
                         }  
         }
         catch (IOException e) {
                saveLabel.setText("Error: File not saved!");
                e.printStackTrace(); // method in Java is a tool used to handle exceptions and errors; prints the details and what line the error hath occured 
           }
       }); 
    
        Button deleteSave =new Button ("Delete Save");
        deleteSave.setOnAction(value5 -> {
     
        File heroFile = new File ("Hero.txt");
        if (heroFile .delete()) { 
            saveLabel.setText("Existing Save File deleted!");
          } else {
            saveLabel.setText("Could not delete file! No save detected!");
          } 
     }); 
    
    
    Button exitButton= new Button ("Exit");
    exitButton.setOnAction(value6 -> {
    System.exit(0);
    }); 
    
    menu.getChildren().add(saveLabel); 
    menu.getChildren().add (Continue);
    menu.getChildren().add (newSave);
    menu.getChildren().add (deleteSave);
    menu.getChildren().add (exitButton);
    menu.setAlignment(Pos.CENTER);

    primaryStage.setScene (startScene);
    primaryStage.show(); 
    }
    public static void main(String[] args) {
    Application.launch(args);
    }
}
