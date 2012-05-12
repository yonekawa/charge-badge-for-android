package jp.mog2dev.chargebadge.notification;

import jp.mog2dev.chargebadge.BadgeDetailActivity;
import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class UnlockNotification
{
    public static void send(Context context, IAchivement achivement)
    {
        String body = String.format(context.getString(R.string.unlock_notification_format), achivement.getName());
        
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher, body, System.currentTimeMillis());
        
        Intent intent = new Intent(context, BadgeDetailActivity.class);
        intent.putExtra("key", achivement.getKey());
        PendingIntent contentIntent = PendingIntent.getActivity(context, achivement.getBadge(), intent, 0);
        notification.setLatestEventInfo(context, context.getString(R.string.app_name), body, contentIntent);
        notification.sound = Settings.System.DEFAULT_NOTIFICATION_URI;
        notificationManager.notify(achivement.getBadge(), notification);
    }
}
