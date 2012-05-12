package jp.mog2dev.chargebadge;

import java.util.List;

import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.content.Context;
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
    public AchivementAdapter(Context context, int textViewResourceId, List<IAchivement> objects) {
        super(context, textViewResourceId, objects);
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
        String tag = (String)v.getTag();
        if (tag.equals("achivement_"))
        Log.d("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC", (String)v.getTag());
    }
}
