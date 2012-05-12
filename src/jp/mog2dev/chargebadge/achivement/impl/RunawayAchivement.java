package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class RunawayAchivement extends AbstractAchivement
{
    public RunawayAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "runaway_achivement";
        this.badge = R.drawable.badge11;
        this.lockedBadge = R.drawable.badge11_lock;
        this.name = context.getString(R.string.achivement_runaway);
        this.description = context.getString(R.string.achivement_runaway_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getLastChargedAt();
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING 
                && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING
                && elasped > (72 * 60 * 60 * 1000); // 72 hours
    }

}
