package jp.mog2dev.chargebadge.broadcast;

import java.util.ArrayList;

import jp.mog2dev.chargebadge.BadgeListActivity;
import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;
import jp.mog2dev.chargebadge.notification.UnlockNotification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BatteryMonitoringBroadcastReceiver extends BroadcastReceiver
{
    private boolean isReceving = false;
    private BadgeListActivity activity;
    
    public BatteryMonitoringBroadcastReceiver(BadgeListActivity activity)
    {
        super();
        this.activity = activity;
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            return;
        }
        
        if (this.isReceving) {
            return;
        }
        synchronized(this)
        {
            if (this.isReceving) {
                return;
            }
            this.isReceving = true;
            
            BatteryInfo battery = BatteryInfo.getInstance();
            battery.changeState(intent.getIntExtra("status", 0));
            battery.setLevel(intent.getIntExtra("level", 0));
            battery.setHealth(intent.getIntExtra("health", 0));
            battery.setPlugged(intent.getIntExtra("plugged", 0));
            battery.setTemperature(intent.getIntExtra("temperature", 0));
            
            boolean isUnlockedSomething = false;
            ArrayList<IAchivement> achivements = AchivementManager.getInstance(context).getAchivements();
            for (IAchivement achivement : achivements)
            {
                if (!achivement.isUnlocked() && achivement.isUnlockable(battery)) {
                    achivement.unlock();
                    isUnlockedSomething = true;
                    UnlockNotification.send(context, achivement);
                }
            }
            
            if (isUnlockedSomething) {
                this.activity.invalidateView();
            }
            this.isReceving = false;
        }
    }
}
