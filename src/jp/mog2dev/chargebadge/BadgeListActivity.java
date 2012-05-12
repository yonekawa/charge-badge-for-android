package jp.mog2dev.chargebadge;

import java.util.ArrayList;

import com.example.android.actionbarcompat.ActionBarActivity;

import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import jp.mog2dev.chargebadge.service.BatteryMonitoringService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.widget.GridView;

public class BadgeListActivity extends ActionBarActivity {

    private GridView view;
    private UnlockBroadcastReceiver unlockReceiver = new UnlockBroadcastReceiver(this);
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setupAchivementsGrid();
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(BatteryMonitoringService.UNLOCKED);
        registerReceiver(this.unlockReceiver, filter);
        
        Intent intent = new Intent(this, BatteryMonitoringService.class);
        startService(intent);
    }
    
    private void setupAchivementsGrid()
    {
        ArrayList<IAchivement> achivements = AchivementManager.getInstance(getApplicationContext()).getAchivements();
        AchivementAdapter adapter = new AchivementAdapter(getApplicationContext(), this, android.R.layout.simple_list_item_1, achivements);
 
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
        this.view = gridView;
    }
    
    public void pushDetailActivity(String key)
    {
        Intent intent = new Intent(this, BadgeDetailActivity.class);
        intent.putExtra("key", key);
        this.startActivity(intent);
    }
    
    public void invalidateView()
    {
        new Handler().post(new InvalidateRunnable(this.view));
    }
    
    private class InvalidateRunnable implements Runnable
    {
        private GridView view;
        public InvalidateRunnable(final GridView view)
        {
            this.view = view;
        }
        
        @Override
        public void run() {
            this.view.invalidateViews();
        }
    }
    
    private class UnlockBroadcastReceiver extends BroadcastReceiver
    {
        private BadgeListActivity activity;
        public UnlockBroadcastReceiver(BadgeListActivity activity)
        {
            super();
            this.activity = activity;
        }
        
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            if (!action.equals(BatteryMonitoringService.UNLOCKED)) {
                return;
            }
            this.activity.invalidateView();
        }
    }

}