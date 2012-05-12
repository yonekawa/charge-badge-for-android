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
        this.name = context.getString(R.string.achivement_start_biginner);
        this.description = context.getString(R.string.achivement_start_biginner_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        return battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING && battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING;
    }
}
