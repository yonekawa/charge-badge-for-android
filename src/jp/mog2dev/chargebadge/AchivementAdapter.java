package jp.mog2dev.chargebadge;

import java.util.List;

import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class AchivementAdapter extends ArrayAdapter<IAchivement>
{
    public AchivementAdapter(Context context, int textViewResourceId, List<IAchivement> objects) {
        super(context, textViewResourceId, objects);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IAchivement item = (IAchivement)getItem(position);
        if (null == convertView) {
            ImageView imageView = new ImageView(this.getContext());
            int drawable = item.isUnlocked() ? item.getBadge() : item.getLockedBadge();
            imageView.setImageDrawable(this.getContext().getResources().getDrawable(drawable));
            convertView = imageView;
        }
        return convertView;
    }

}
