package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class TwentyFourAchivement extends AbstractAchivement
{
    public TwentyFourAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "24_achivement";
        this.badge = R.drawable.badge07;
        this.lockedBadge = R.drawable.badge07_lock;
        this.name = context.getString(R.string.achivement_24);
        this.description = context.getString(R.string.achivement_24_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getChargeStartedAt();
        return battery.getStatus() == BatteryManager.BATTERY_STATUS_CHARGING && elasped > (24 * 60 * 60 * 1000); // 12 hour
    }

}
