package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramakant.virualatm.NavigationDrawer;
import com.example.ramakant.virualatm.R;

/**
 * Created by User on 14/03/2016.
 */
public class NeedHelpFragment extends Fragment {

    NavigationDrawer activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rooView = inflater.inflate(R.layout.need_help,container,false);

        return  rooView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NavigationDrawer)activity;
        this.activity.mTitle = "Need Help";
        this.activity.restoreActionBar();
    }
}
