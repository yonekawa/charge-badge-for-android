package jp.mog2dev.chargebadge.service;
import java.util.ArrayList;

import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;
import jp.mog2dev.chargebadge.notification.UnlockNotification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class BatteryMonitoringService extends Service
{
    public static final String UNLOCKED = "Battery Monitoring Service";
    private BatteryMonitoringBroadcastReceiver mBroadcastReceiver = new BatteryMonitoringBroadcastReceiver(this);
    
    @Override
    public IBinder onBind(Intent intent)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void onCreate()
    {
        super.onCreate();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(this.mBroadcastReceiver, filter);
    }
    
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(this.mBroadcastReceiver);
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "BatteryMonitoringService#onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    private class BatteryMonitoringBroadcastReceiver extends BroadcastReceiver
    {
        private boolean isReceving = false;
        private BatteryMonitoringService service;
        
        public BatteryMonitoringBroadcastReceiver(BatteryMonitoringService service)
        {
            super();
            this.service = service;
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
                    service.sendBroadcast(new Intent(BatteryMonitoringService.UNLOCKED));
                }
                this.isReceving = false;
            }
        }
    }

}
