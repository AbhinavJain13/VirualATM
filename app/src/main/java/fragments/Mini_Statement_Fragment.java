package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapters.AdapterMiniStatement;
import Utils.MiniStatementData;
import com.example.ramakant.virualatm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15/03/2016.
 */
public class Mini_Statement_Fragment extends Fragment {

    RecyclerView recyclerView;
    List<MiniStatementData> data = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mini_statmnt,container,false);
        initializeView(rootView);
        initializeData();
        return  rootView;
    }

    public void initializeView(View rootView)
    {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    void initializeData()
    {
        MiniStatementData miniData = new MiniStatementData();
        miniData.setAccnnum("875394");
        miniData.setBalance("78687687");
        miniData.setDate("12 April 2015");
        miniData.setFlag("Dont Know");
        miniData.setTransacnamnt("65347583");
        miniData.setNotes("At Hospital");

        data.add(miniData);

        MiniStatementData miniData1 = new MiniStatementData();
        miniData1.setAccnnum("5834");
        miniData1.setBalance("7435438");
        miniData1.setDate("12 January 2016");
        miniData1.setFlag("Dont Know");
        miniData1.setTransacnamnt("7563874");
        miniData1.setNotes("At Railway Station");

        data.add(miniData1);

        AdapterMiniStatement adapter = new AdapterMiniStatement(getActivity(), data);
        recyclerView.setAdapter(adapter);
    }
}
