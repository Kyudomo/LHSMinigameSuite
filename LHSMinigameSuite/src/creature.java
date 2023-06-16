import java.util.*;

public class creature {

   public int maxHP;
   public int HP;
   public int atk;
   public int def;  
   public int variance;
   
   public static Random rng = new Random();
   
   protected String hit(int atk, creature c) {
      int damage = atk + rng.nextInt(variance) - c.def;
      c.HP = c.HP - damage;
      
      String damageText = ("dealt " + damage + " damage ");
      return damageText;
   }

   public int getHP() {
      return HP;
   }
   public void setHP(int newHP) {
      HP = newHP;
   }
   public int getMaxHP() {
      return maxHP;
   }
}


// player object constructor
class Player extends creature {

   public int xp;
   public int level;
   public int hSpells;
   int[] levelReq = new int[10];
   
   
   
   public Player() {
      maxHP          = 10  ;
      HP             = 10  ;
      level          = 0   ;
      hSpells        = 3   ;
      xp             = 0   ;
      atk            = 0   ;
      def            = 0   ;
      variance       = 1   ;
      for (int x = 0; x < levelReq.length ; x++) {
         levelReq[x] = 5 * x;
      }
   }
   
   public String heal() {
      String healText;
      if (hSpells <= 0) {
         healText = ("you don't have any more heal spells!");
      } else if (HP >= maxHP) {
         healText = ("you are already at full HP!");
      } else {
         int healing = maxHP/2;
         if (HP + healing > maxHP) {
            healing = maxHP - HP;
            HP = maxHP;
            hSpells--;
         } else {
            HP = HP + healing;
            hSpells--;
         }
         healText = ("healed " + healing + "HP.");
      }
      return healText;
   }     
   
   public String Hit(monster m) {
      return super.hit(atk , m);
   }
   
   public int getHSpells() {
      return hSpells;
   }
   public void setHSpells(int morespells) {
      hSpells = hSpells + morespells;
   }
   
   
   
   public void setAtk(int newAtk) {
      atk = newAtk;
   }
   
   public void setDef(int newDef) {
      def = newDef;
   }
   
   public void setVariance(int newVar) {
      variance = newVar;
   }

//the methods checking and causing level up
   public int needXP() {
      return (levelReq[level + 1] - xp);
   }
   
   public void gainXP(int newXp) {
      xp = newXp + xp;
   }
   
   public boolean checkLevelUp() {
      boolean result;
      if ((levelReq[level + 1]) >= xp) {
         result = true;
         xp = xp - levelReq[level + 1];
         setLvl(level + 1);
      } else {
         result = false;
      }
      return result;
   }
   
   public void setLvl(int newLvl) {
      level = newLvl;
      lvlUp();
   }
   
   public void lvlUp() {
      maxHP = 10 + (10 * level);
      HP = HP + 10;
   }
   
   public String levelUP() {
      String lvlText;
      lvlText = ("Congratulations! You leveled up to level " + level);
      return lvlText;
   }
   
   
   
   
}


// monster object constructor
class monster extends creature {

   int rewardXP;

   public monster(int mH, int h, int A, int D, int V, int X) {
      maxHP = mH;
      HP = h;
      atk = A;
      def = D;
      variance = V;
      rewardXP = X;
      
   }
   
   public String Hit(Player p) {
      return super.hit(atk, p );
   }   

}
