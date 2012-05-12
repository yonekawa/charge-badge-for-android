package jp.mog2dev.chargebadge;

import java.util.ArrayList;

import jp.mog2dev.chargebadge.achivement.AchivementManager;
import jp.mog2dev.chargebadge.achivement.IAchivement;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.android.actionbarcompat.ActionBarActivity;

public class BadgeDetailActivity extends ActionBarActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        ArrayList<IAchivement> achivements = AchivementManager.getInstance(getApplicationContext()).getAchivements();
        
        IAchivement target = null;
        for (IAchivement achivement : achivements)
        {
            if (achivement.getKey().equals(key))
            {
                target = achivement;
                break;
            }
        }
        
        ImageView badge = (ImageView) this.findViewById(R.id.badge);
        //badge.setImageDrawable(this.getResources().getDrawable(target.getDetailBadge()));
        badge.setImageDrawable(this.getResources().getDrawable(target.getBadge()));
    }
}