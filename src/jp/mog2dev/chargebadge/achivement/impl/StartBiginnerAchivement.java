package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class StartBiginnerAchivement extends AbstractAchivement
{
    public StartBiginnerAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "start_biginner_achivement";
        this.badge = R.drawable.badge01;
        this.lockedBadge = R.drawable.badge01_lock;
        this.name = context.getString(R.string.achivement_start_biginner);
        this.description = context.getString(R.string.achivement_start_biginner_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING;
    }
}
