package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class ReadyToGoAchivement extends AbstractAchivement
{
    public ReadyToGoAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "ready_to_go_achivement";
        this.badge = R.drawable.badge04;
        this.lockedBadge = R.drawable.badge04_lock;
        this.name = context.getString(R.string.achivement_ready_to_go);
        this.description = context.getString(R.string.achivement_ready_to_go_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        return battery.getFromStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING
                && battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING
                && battery.getLevel() >= 90;
    }
}
