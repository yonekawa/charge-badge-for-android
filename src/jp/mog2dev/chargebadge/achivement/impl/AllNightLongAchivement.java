package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class AllNightLongAchivement extends AbstractAchivement
{
    public AllNightLongAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "all_night_long_achivement";
        this.badge = R.drawable.badge09;
        this.lockedBadge = R.drawable.badge09_lock;
        this.detailBadge = R.drawable.badge09_large;
        this.name = context.getString(R.string.achivement_all_night_long);
        this.description = context.getString(R.string.achivement_all_night_long_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getLastChargedAt();
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING 
                && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING
                && elasped > (24 * 60 * 60 * 1000); // 24 hours
    }

}
