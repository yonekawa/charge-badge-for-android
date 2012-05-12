package jp.mog2dev.chargebadge.achivement;

import java.util.ArrayList;

import android.content.Context;

import jp.mog2dev.chargebadge.achivement.impl.FullChargeBiginnerAchivement;
import jp.mog2dev.chargebadge.achivement.impl.HungryAchivement;
import jp.mog2dev.chargebadge.achivement.impl.ReadyToGoAchivement;
import jp.mog2dev.chargebadge.achivement.impl.StartBiginnerAchivement;
import jp.mog2dev.chargebadge.achivement.impl.StopBiginnerAchivement;

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
       this.achivements.add(new ReadyToGoAchivement(context));
       this.achivements.add(new HungryAchivement(context));
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
