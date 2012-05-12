package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class SpeedStarAchivement extends AbstractAchivement
{
    public SpeedStarAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "speed_star_achivement";
        this.badge = R.drawable.badge08;
        this.lockedBadge = R.drawable.badge08_lock;
        this.name = context.getString(R.string.achivement_speed_star);
        this.description = context.getString(R.string.achivement_speed_star_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        long elasped = System.currentTimeMillis() - battery.getChargeStartedAt();
        return battery.getStatus() == BatteryManager.BATTERY_STATUS_DISCHARGING && elasped >= (10 * 60 * 1000) && elasped <= (30 * 60 * 1000);
    }

}
