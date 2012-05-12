package jp.mog2dev.chargebadge.battery;

import android.os.BatteryManager;

public class BatteryInfo
{
    private int status = BatteryManager.BATTERY_STATUS_DISCHARGING;
    private int fromStatus = BatteryManager.BATTERY_STATUS_DISCHARGING;
    private long chargeStartedAt = System.currentTimeMillis();
    private long lastChargedAt = System.currentTimeMillis();
    protected int health;
    protected int level;
    protected int temperature;
    protected int plugged;
    
    private static BatteryInfo instance;
    private BatteryInfo()
    {
        instance = null;
    }
    public static BatteryInfo getInstance()
    {
        if (instance == null)
        {
            synchronized(BatteryInfo.class) {
            if (instance == null) {
              instance = new BatteryInfo();
              //instance.readPreferences(context);
            }
          }
        }
        return instance;
    }
    
    public int getStatus()
    {
        return this.status;
    }
    public long getFromStatus()
    {
        return this.fromStatus;
    }
    public long getChargeStartedAt()
    {
        return this.chargeStartedAt;
    }
    public long getLastChargedAt()
    {
        return this.lastChargedAt;
    }
    public int getHealth()
    {
        return this.health;
    }
    public void setHealth(int health)
    {
        this.health = health;
    }
    public int getLevel()
    {
        return this.level;
    }
    public void setLevel(int level)
    {
        this.level = level;
    }
    public int getTemperature()
    {
        return this.temperature;
    }
    public void setTemperature(int temperature)
    {
        this.temperature = temperature;
    }
    public int getPlugged()
    {
        return this.plugged;
    }
    public void setPlugged(int plugged)
    {
        this.plugged = plugged;
    }
    
    public void changeState(int to)
    {
        if (this.status == to) {
            this.fromStatus = this.status;
            this.status = to;
            return;
        }
        
        if (this.status == BatteryManager.BATTERY_STATUS_DISCHARGING && to == BatteryManager.BATTERY_STATUS_CHARGING)
        {
            // Start charge.
            this.chargeStartedAt = System.currentTimeMillis();
        }
        else if (this.status == BatteryManager.BATTERY_STATUS_CHARGING && to == BatteryManager.BATTERY_STATUS_DISCHARGING)
        {
            // Stop charge.
            this.lastChargedAt = System.currentTimeMillis();
        }

        this.fromStatus = this.status;
        this.status = to;
    }
}
