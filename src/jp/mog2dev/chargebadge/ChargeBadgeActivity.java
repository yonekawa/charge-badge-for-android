package jp.mog2dev.chargebadge;

import java.util.ArrayList;

import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import jp.mog2dev.chargebadge.achivement.impl.StartBiginnerAchivement;
import jp.mog2dev.chargebadge.achivement.impl.StopBiginnerAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;
import jp.mog2dev.chargebadge.notification.UnlockNotification;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class ChargeBadgeActivity extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.setupAchivementsGrid();
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(mBroadcastReceiver, filter);
    }
    
    private void setupAchivementsGrid()
    {
        ArrayList<String> list = new ArrayList<String>();

        ArrayList<IAchivement> achivements = AchivementManager.getInstance(getApplicationContext()).getAchivements();
        for (IAchivement achivement : achivements)
        {
            if (achivement.isUnlocked()) {
                list.add("Unlocked!");
            } else {
                list.add("Lock");
            }
        }
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(), android.R.layout.simple_list_item_1, list);
 
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        unregisterReceiver(mBroadcastReceiver);
    }
    
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        private boolean isReceving = false;
        
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
                
                ArrayList<IAchivement> achivements = AchivementManager.getInstance(getApplicationContext()).getAchivements();
                for (IAchivement achivement : achivements)
                {
                    if (!achivement.isUnlocked() && achivement.isUnlockable(battery)) {
                        achivement.unlock();
                        UnlockNotification.send(context, achivement);
                    }
                }
                
                this.isReceving = false;
            }
        }
    };
}