package jp.mog2dev.chargebadge.achivement;

import android.content.Context;
import jp.mog2dev.chargebadge.battery.BatteryInfo;

public interface IAchivement
{
    public void setContext(Context context);
    public String getKey();
    public String getName();
    public int getBadge();
    public int getLockedBadge();
    public int getDetailBadge();
    public String getDescription();
    public String getUnlockedDateString();
    
    public boolean isUnlockable(BatteryInfo battery);
    public boolean isUnlocked();
    public void unlock();
}
