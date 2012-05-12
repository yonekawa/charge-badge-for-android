package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class LongSleeperAchivement extends AbstractAchivement
{
    public LongSleeperAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "long_sleeper_achivement";
        this.badge = R.drawable.badge06;
        this.lockedBadge = R.drawable.badge06_lock;
        this.detailBadge = R.drawable.badge06_large;
        this.name = context.getString(R.string.achivement_long_sleeper);
        this.description = context.getString(R.string.achivement_long_sleeper_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getChargeStartedAt();
        return battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING && elasped > (12 * 60 * 60 * 1000); // 12 hours
    }

}
