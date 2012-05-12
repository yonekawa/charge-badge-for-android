package jp.mog2dev.chargebadge.notification;

import jp.mog2dev.chargebadge.R;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class UnlockNotification
{
    public static void send(Context context, IAchivement achivement)
    {
        String body = String.format(context.getString(R.string.unlock_notification_format), achivement.getName());
        
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(android.R.drawable.btn_default, body, System.currentTimeMillis());
        
        Intent intent = new Intent(Intent.ACTION_VIEW);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        notification.setLatestEventInfo(context, context.getString(R.string.app_name), body, contentIntent);
        notificationManager.notify(R.string.app_name, notification);
    }
}
