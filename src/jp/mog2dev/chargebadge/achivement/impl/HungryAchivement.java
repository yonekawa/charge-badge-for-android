package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class HungryAchivement extends AbstractAchivement
{
    public HungryAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "hungry_achivement";
        this.badge = R.drawable.badge05;
        this.lockedBadge = R.drawable.badge05_lock;
        this.name = context.getString(R.string.achivement_hungry);
        this.description = context.getString(R.string.achivement_hungry_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING
                && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING
                && battery.getLevel() <= 10;
    }
}
