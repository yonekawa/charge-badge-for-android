package jp.mog2dev.chargebadge.battery;

import android.os.BatteryManager;

public class BatteryInfo
{
    private int status = BatteryManager.BATTERY_STATUS_DISCHARGING;
    private int fromStatus = BatteryManager.BATTERY_STATUS_DISCHARGING;
    private long chargeStartedAt = System.currentTimeMillis();
    private long lastChargedAt = System.currentTimeMillis();
    private long chargingTime = 0;
    protected int health;
    protected int level;
    protected int temperature;
    protected int plugged;
    
    private long chargeDistanceTime = 0;
    
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
    public long getChargingTime()
    {
        return this.chargingTime;
    }
    public long getChargeDistanceTime()
    {
        return this.chargeDistanceTime;
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
        this.chargeDistanceTime = 0;
        this.chargingTime = 0;
        
        int from = this.status;
        if (from == to) {
            return;
        }
        
        if (from == BatteryManager.BATTERY_STATUS_DISCHARGING && to == BatteryManager.BATTERY_STATUS_CHARGING)
        {
            // Start charge.
            this.chargeDistanceTime = System.currentTimeMillis() - this.lastChargedAt;
        }
        else if (from == BatteryManager.BATTERY_STATUS_CHARGING && to == BatteryManager.BATTERY_STATUS_DISCHARGING)
        {
            // Stop charge.
            this.chargingTime = System.currentTimeMillis() - this.chargeStartedAt;
        }
        this.status = to;
        this.fromStatus = from;
    }
}
