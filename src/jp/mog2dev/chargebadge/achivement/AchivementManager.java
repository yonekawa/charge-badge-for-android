package jp.mog2dev.chargebadge.achivement;

import java.util.ArrayList;

import android.content.Context;

import jp.mog2dev.chargebadge.achivement.impl.FullChargeBiginnerAchivement;
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
   }
   
   public ArrayList<IAchivement> getAchivements()
   {
       return this.achivements;
   }
}
