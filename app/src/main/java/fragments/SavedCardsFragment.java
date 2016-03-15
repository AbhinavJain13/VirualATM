package fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ramakant.virualatm.R;
import adapters.SavedCardsAdapter;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/13/2016.
 */
public class SavedCardsFragment extends Fragment {

    RecyclerView recyclerView;
    boolean isTick = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_saved_cards,container,false);
         initializeView(rootView);
        return rootView;
    }

    public void initializeView(View rootView)
    {

        Bundle bundle = getArguments();
        isTick = bundle.getBoolean("isTick");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        Cursor cursor = DatabaseOpenHelper.getInstance(getActivity()).getCardData();

        SavedCardsAdapter adapter = new SavedCardsAdapter(SavedCardsFragment.this,cursor, isTick);
        recyclerView.setAdapter(adapter);
    }

    public void startEditFragment(Integer rowId)
    {

        EditFragment editFragment = new EditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rowId", rowId);
        editFragment.setArguments(bundle);

        FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.container, editFragment)
                .commit();

    }

    public void startATMFragment(Integer rowId)
    {

        ATMFragment atmFragment = new ATMFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("rowId",rowId);
        atmFragment.setArguments(bundle);

        FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
        fragmentManager1.beginTransaction()
                .replace(R.id.container, atmFragment)
                .commit();

    }
}
