package jp.mog2dev.chargebadge;

import java.util.ArrayList;

import com.example.android.actionbarcompat.ActionBarActivity;

import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import jp.mog2dev.chargebadge.broadcast.BatteryMonitoringBroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.GridView;

public class BadgeListActivity extends ActionBarActivity {

    private BatteryMonitoringBroadcastReceiver mBroadcastReceiver = new BatteryMonitoringBroadcastReceiver();
    
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
        ArrayList<IAchivement> achivements = AchivementManager.getInstance(getApplicationContext()).getAchivements();
        AchivementAdapter adapter = new AchivementAdapter(getApplicationContext(), this, android.R.layout.simple_list_item_1, achivements);
 
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onStop();
        unregisterReceiver(mBroadcastReceiver);
    }
    
    public void pushDetailActivity(String key)
    {
        Intent intent = new Intent(this, BadgeDetailActivity.class);
        intent.putExtra("key", key);
        this.startActivity(intent);
    }
}