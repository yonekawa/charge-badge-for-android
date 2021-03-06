package jp.mog2dev.chargebadge.achivement;

import java.util.ArrayList;

import android.content.Context;
import jp.mog2dev.chargebadge.achivement.impl.*;

public class AchivementManager
{
   private ArrayList<IAchivement> achivements = new ArrayList<IAchivement>();
   
   private static AchivementManager instance;
   private AchivementManager(){}
   public static AchivementManager getInstance(Context context)
   {
       if (instance == null)
       {
           synchronized(AchivementManager.class) {
           if (instance == null) {
             instance = new AchivementManager();
             instance.createAchivemetns(context);
           }
         }
       }
       return instance;
   }
   
   private void createAchivemetns(Context context)
   {
       this.achivements = new ArrayList<IAchivement>();
       this.achivements.add(new StartBiginnerAchivement(context));
       this.achivements.add(new StopBiginnerAchivement(context));
       this.achivements.add(new FullChargeBiginnerAchivement(context));
       this.achivements.add(new HungryAchivement(context));
       this.achivements.add(new ReadyToGoAchivement(context));
       this.achivements.add(new LongSleeperAchivement(context));
       this.achivements.add(new TwentyFourAchivement(context));
       this.achivements.add(new SpeedStarAchivement(context));
       this.achivements.add(new AllNightLongAchivement(context));
       this.achivements.add(new SuperManAchivement(context));
       this.achivements.add(new RunawayAchivement(context));
       this.achivements.add(new FireAchivement(context));
   }
   
   public ArrayList<IAchivement> getAchivements()
   {
       return this.achivements;
   }
   
   public IAchivement findAchivement(String key)
   {
       for (IAchivement achivement : this.achivements)
       {
           if (achivement.getKey().equals(key))
           {
               return achivement;
           }
       }
       return null;
   }
}
