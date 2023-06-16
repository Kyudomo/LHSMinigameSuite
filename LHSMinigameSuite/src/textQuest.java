import java.util.*;
import java.lang.Thread;

//Note to self, try a text listener for options like checking health or other stats
//Also add a help command

public class textQuest extends textQuestgui {


// the name inputed by the player to be used when talking with the narrator
   public static String playerName;
   
   
// a scanner for user inputs
   public static Scanner UI = new Scanner(System.in);
   public static String in;
   
// object that contains player's stats   
   static Player user = new Player();
   
   
   
   
// main method   
   public static void main(String args[]) {
          
      opening();
      training();
      forest1();
      
      
      
   }  
   
// method for the first forest enviornment zone
   public static void forest1() {
      
      for (int i = 0; i<=5; i++) {
         System.out.println();
      }
      
      System.out.println("You step outside and as the sun shines in your eyes the smell of the forest fills your nose.");
      System.out.println("A small path winds through the dense trees ahead to the north.");
      System.out.println();
      System.out.println("Alright the town is just down the path a ways. Follow it and you should be just fine!     >north");
      in = UI.nextLine();
      
      while ((!(in.equals("east"))) && (!(in.equals("west"))) && (!(in.equals("north")))) {
         enterValid();
      }   
      while (in.equals("east") || in.equals("west")) {
         System.out.println("The forest is too dense to continue");
         in = UI.nextLine();
      } 
      while (!(in.equals("north"))) {
         enterValid();
      }

      System.out.println("As you march through the dense forest you see a rustling on the side of the road ahead of you.");  
      System.out.println(">attack  >ignore  >sneak");
      in = UI.nextLine();
      monster goblin = new monster(8,8,2,1,3,4);
      
      while ((!(in.equals("attack"))) && (!(in.equals("ignore"))) && (!(in.equals("sneak")))) {
         enterValid();
      }
      
      if (in.equals("attack")) {
         System.out.println("A goblin jumps out of the trees in front of you!");
         combat(goblin , "goblin");
      } else if (in.equals("ignore")) {
         System.out.println("A goblin pounces on you from the bush");
         goblin.Hit(user);
         combat(goblin , "goblin");
         System.out.println();
         System.out.println("You manage to kill the goblin and you move along from the corpse");
      } else if (in.equals("sneak")) {
         System.out.println("you quietly approach a goblin that doesn't notice you.");
         System.out.println("you would be able to sneak past or attack if you'd like     >sneak past  >attack");
         in = UI.nextLine();
         while ((!(in.equals("sneak past"))) && (!(in.equals("attack")))) {
            enterValid();
         } 
         if (in.equals("sneak past")) {
            System.out.println("you manage to sneak past the goblin and make it further down the path");
         } else if(in.equals("attack")) {
            System.out.println("you manage to take down the goblin silently, without taking any damage");
            goblin.setHP(0);
         } 
      }   
      System.out.println();
      System.out.println();
      System.out.println("A short ways down the path you come to a fork in the road.");
      System.out.println("A rickety and weathered road sign points ahead, the left labeled as gaftburg, and the right is too old to read.");
      System.out.println(">left  >right");
      in = UI.nextLine();
      while ((!(in.equals("left"))) && (!(in.equals("right")))) {
         enterValid();
      }
      if (in.equals ("left")) {
         forest1Left();
      } else if (in.equals("right")) {
         forest1Right();
      }   
   }   
   
   
// extensions of options for forest part 1
   public static void forest1Left() {
      
      
      
   }
   public static void forest1Right() {
   
      System.out.println("R: Alright well thats a bit of an interesting choice considering we're going to the town");
      System.out.println();
      
      try {
         Thread.sleep(1000);
      } catch(Exception e) {
         System.out.println(e);
      }
      
      System.out.println("As you continue down the path the forest grows thicker, and it gets darker by the minute as you move deeper.");
      System.out.println("As you push through the woods, the path becomes patchy and hard to follow.");
      System.out.println("R: You could probably continue this way and it would still make it back to the town, but I'm not sure how long it would take.");
      
   
      
   }
   
   
   
// training sequence to teach combat after the opening
// used as a baseline for the combat system from both designer and player perspectives   
   public static void training() {
      System.out.println();
   
      try {
         Thread.sleep(3500);
      } catch(Exception e) {
         System.out.println(e);
      }      
   
      System.out.println("You wake up in a cozy little house. Theres a crackling fire and a training dummy in the corner");
      System.out.println();
      System.out.println("Alright! Before you get going I'm just going to teach you about combat.");
      System.out.println("I think there shouldn't be any issues,");
      System.out.println("but I don't really feel like finding another player if something goes wrong.");
      System.out.println();
      System.out.println("Take a walk over to the dummy.     >forward");
      in = UI.nextLine();
      while (!(in.equals("forward"))) {
         enterValid();
      }
      System.out.println("Alright I made this dummy for a little practice. Grab the stick over on the ground and take a shot!     >grab");
      in = UI.nextLine();
      while (!(in.equals("grab"))) {
         enterValid();
      }
      user.setAtk(2);
      user.setVariance(2);
      
      monster trainDummy = new monster(7,7,1,0,2,4);
      
      combat(trainDummy , "Training Dummy");
      
      System.out.println("R: Nice job defeating that training dummy!");
      System.out.println();
      
      if (user.getHP() != user.getMaxHP()) {
         System.out.println("R: You should heal a little bit.");
         System.out.println("R: Here I'll give you a healing spell to use"); 
         user.setHSpells(1);
         System.out.println("You got a heal spell     >heal");
         in = UI.nextLine();
         while (!(in.equals("heal"))) {
            enterValid();
         }
         if (in.equals("heal")) {
            System.out.println(user.heal());
         } 
         System.out.println();
      }
      
      System.out.println("I have some more gear for you here");
      System.out.println();
      System.out.println("a simple helmet and a dagger spawn in front of you      >grab");
      System.out.println();
      in = UI.nextLine();
      
      while (!(in.equals("grab"))) {
         enterValid();
      }
      if (in.equals ("grab")) {
         user.setAtk(3);
         user.setVariance(3);
         user.setDef(1);
      }
      
      System.out.println("That knife will help you deal a bit more damage and the helmet will reduce the damage from incoming attacks.");
      System.out.println();
      System.out.println("Alrighty then well I think you're all good to go!");
      System.out.println("Feel free to step outside                   >outside");
      in = UI.nextLine();
      while (!(in.equals("outside"))) {
         enterValid();
      }
   }

  
     
   
   
// opening sequence to the game before spawning the player

   public static void opening() {
   
      System.out.println("Welcome to Text Quest!");
      System.out.println();
      System.out.println("What is your name adventurer?");
      System.out.println();
      
      playerName = UI.nextLine();
      System.out.println();
      System.out.format("R: Nice to meet you %s. I'm Rotarran!", playerName);
      System.out.println();
      System.out.format("R: Say %s, would you be willing to run an errand for me?     >yes  >no", playerName);
      System.out.println();
      in = UI.nextLine();
      
      while ((!(in.equals("no"))) && (!(in.equals("yes")))) {
         enterValid();
      }
      
      if(in.equals("no")) {
         System.out.println();
         System.out.println("R: Sorry I didn't catch that.");
         System.out.println("R: Do you want to help run an errand for me?     >yes  >no");
         in = UI.nextLine();
         
         while ((!(in.equals("no"))) && (!(in.equals("yes")))) {
            enterValid();
         }
         
         if(in.equals("no")) {
            System.out.println();
            System.out.println("R: What was that?");
            System.out.println("R: Were you saying you would run an errand for me?     >yes  >no");
            in = UI.nextLine();
            
         } else if (in.equals("yes")) {
            openingYes();
         }
         
         while ((!(in.equals("no"))) && (!(in.equals("yes")))) {
            enterValid();
         }
          
         if(in.equals("no")) {
            System.out.println();
            System.out.println("R: no?     >no");
         }
         in = UI.nextLine();
         
         while ((!(in.equals("no")))) {
            enterValid();
         }
          
         if(in.equals("no")) {
            System.out.println(" ,______________________________________");   
            System.out.println("|_________________,----------._ [____]  \"\"-,__  __....-----=====");    
            System.out.println("               (_(||||||||||||)___________/   \"\"                |");
            System.out.println("                  `----------'        [ ))\"\"-,                  |");
            System.out.println("                                       \"\"    `,  _,--....___    |");
            System.out.println("                                               `/           \"\"\"\"");
            System.out.println();
            try {
               Thread.sleep(2500);
            } catch(Exception e) {
               System.out.println(e);
            }
            System.out.println();         
            System.out.println("R: So you wanted to run an errand for me right?     >yes");
            in = UI.nextLine();
            
            while (!(in.equals("yes"))) {
               enterValid();
            }
            
            if (in.equals("yes")) {
               openingYes();
            } 
         } 
         
      } else if (in.equals("yes")) {
         openingYes();
      }
   }
   
   //when the player says yes to helping narrator
   public static void openingYes() {
      System.out.println();
      System.out.println("R: Glad to hear it! I just need you to run to the nearby town and help out a friend of mine.");
      for (int i=0; i<5; i++) {
         System.out.println();
      }
   }
   
   
   
   
   
   
// a method to be called when the user enters an invalid input   
   
   public static void enterValid() {
      System.out.println("Please enter a valid input");
      in = UI.nextLine();
   }



// method to call when player dies
    
   public static void playerDead() {
      System.out.println("R: Well that's unfortunate");
      System.out.format("R: It seems you've died %s" , playerName);
      System.out.println("R: Feel free to play again as it seems I need a new adventurer...");
   } 
   
   

// combat method for when fighting a monster potentially adding more options later   

   public static void combat(monster m , String name) {
      for (int x = m.getHP(); x >= 0; x = m.getHP()) {
      
         System.out.println();
         System.out.println(">hit  >heal");
         in = UI.nextLine();
         
         while ((!(in.equals("heal"))) && (!(in.equals("hit")))) {
            enterValid();
         }
         
         if (in.equals("heal")) {
            System.out.println(user.heal());
         } else if (in.equals("hit")) {
            System.out.print("You ");
            System.out.print(user.Hit(m));
            System.out.println("!");
            System.out.println();
         } 
         try {
            Thread.sleep(500);
         } catch(Exception e) {
            System.out.println(e);
         }
         System.out.print(name + " ");   
         System.out.print(m.Hit(user));
         System.out.println("to you!");
         
         if (user.getHP() <= 0) {
            playerDead();
            m.setHP(0);
         }
      }
      user.gainXP(m.rewardXP);
      System.out.println();
      System.out.println("You gained " + m.rewardXP + " xp!");
      
      user.gainXP(m.rewardXP);
      if (user.checkLevelUp() == true) {
         user.levelUP();
      }
      System.out.println(user.needXP() + " xp needed to get to level " + (user.level + 1));
      System.out.println();
   }
}   
