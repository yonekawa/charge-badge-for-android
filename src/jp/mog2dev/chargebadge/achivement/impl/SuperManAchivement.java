package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class SuperManAchivement extends AbstractAchivement
{
    public SuperManAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "super_man_achivement";
        this.badge = R.drawable.badge10;
        this.lockedBadge = R.drawable.badge10_lock;
        this.name = context.getString(R.string.achivement_super_man);
        this.description = context.getString(R.string.achivement_super_man_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getLastChargedAt();
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING 
                && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING
                && elasped > (48 * 60 * 60 * 1000); // 48 hours
    }

}
