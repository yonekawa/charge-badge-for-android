package jp.mog2dev.chargebadge.achivement;

import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import jp.mog2dev.chargebadge.battery.BatteryInfo;

public abstract class AbstractAchivement implements IAchivement
{
    protected Context context;

    protected String key;
    protected String name;
    protected int badge;
    protected int lockedBadge;
    protected String description;
    protected Date unlocked;

    @Override
    public void setContext(Context context)
    {
        this.context = context;
    }
    @Override
    public String getKey()
    {
        return this.key;
    }
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public int getBadge()
    {
        return this.badge;
    }
    @Override
    public int getLockedBadge()
    {
        return this.lockedBadge;
    }
    @Override
    public String getDescription()
    {
        return this.description;
    }
    
    @Override
    public boolean isUnlocked()
    {
        SharedPreferences pref = this.context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return pref.getBoolean(this.key, false);
    }

    @Override
    public void unlock()
    {
        SharedPreferences pref = this.context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        Editor e = pref.edit();
        e.putBoolean(this.key, true);
        e.commit();
    }
    
    public abstract boolean isUnlockable(BatteryInfo battery);
}
