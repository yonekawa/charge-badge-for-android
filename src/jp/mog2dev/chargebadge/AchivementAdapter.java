package jp.mog2dev.chargebadge;

import java.util.List;

import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;

public class AchivementAdapter extends ArrayAdapter<IAchivement> implements OnClickListener
{
    private BadgeListActivity activity;
    public AchivementAdapter(Context context, BadgeListActivity activity, int textViewResourceId, List<IAchivement> objects) {
        super(context, textViewResourceId, objects);
        this.activity = activity;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IAchivement item = (IAchivement)getItem(position);
        if (null == convertView) {
            ImageButton imageButton = new ImageButton(this.getContext());
            imageButton.setTag(item.getKey());
            int id = item.isUnlocked() ? item.getBadge() : item.getLockedBadge();
            Drawable drawable = this.getContext().getResources().getDrawable(id);
            imageButton.setImageDrawable(drawable);
            imageButton.setBackgroundColor(Color.TRANSPARENT);
            if (item.isUnlocked())
            {
                imageButton.setOnClickListener(this);
            }
            convertView = imageButton;
        }
        return convertView;
    }
    
    public void onClick(View v)
    {
        String key = (String)v.getTag();
        this.activity.pushDetailActivity(key);
    }
}
