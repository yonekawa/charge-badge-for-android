package jp.mog2dev.chargebadge.achivement.impl;

import android.content.Context;
import android.os.BatteryManager;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.AbstractAchivement;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public class FireAchivement extends AbstractAchivement
{
    public FireAchivement(Context context)
    {
        super();
        this.context = context;
        this.key = "fire_achivement";
        this.badge = R.drawable.badge12;
        this.lockedBadge = R.drawable.badge12_lock;
        this.detailBadge = R.drawable.badge12_large;
        this.name = context.getString(R.string.achivement_fire);
        this.description = context.getString(R.string.achivement_fire_description);
    }
    
    @Override
    public boolean isUnlockable(BatteryInfo battery)
    {
        return battery.getHealth() == BatteryManager.BATTERY_HEALTH_OVERHEAT;
    }

}
