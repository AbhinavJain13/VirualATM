package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import Utils.CardItemData;
import com.example.ramakant.virualatm.R;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/13/2016.
 */
public class EditFragment extends Fragment {

    EditText edtCardNumber, fromMonth, fromYear, toMonth, toYear, cvv, name;
    Integer rowId;

    Button btnSaveCard, btnGeneratePin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_atm, container, false);

        Bundle bundle = getArguments();
        rowId  = bundle.getInt("rowId");

       initializeView(rootView);

        return rootView;
    }

    public void initializeView(View rooView)
    {
        btnSaveCard = (Button) rooView.findViewById(R.id.btnSaveCard);
        btnGeneratePin  = (Button) rooView.findViewById(R.id    .btnGeneratePin);
        btnGeneratePin.setVisibility(View.GONE);
        edtCardNumber = (EditText) rooView.findViewById(R.id.edtCardNumber);
        fromMonth = (EditText) rooView.findViewById(R.id.fromMonth);
        fromYear = (EditText) rooView.findViewById(R.id.fromYear);
        toMonth = (EditText) rooView.findViewById(R.id.toMonth);
        toYear = (EditText) rooView.findViewById(R.id.toYear);
        cvv = (EditText) rooView.findViewById(R.id.cvv);
        name = (EditText) rooView.findViewById(R.id.name);

       CardItemData itemData =  DatabaseOpenHelper.getInstance(getActivity()).getDataFromRowid(rowId);

        edtCardNumber.setText(itemData.getCardNumber());
        toMonth.setText(itemData.getToMonth());
        toYear.setText(itemData.getToYear());
        fromMonth.setText(itemData.getFromMonth());
        fromYear.setText(itemData.getFromYear());
        cvv.setText(itemData.getCvv());
        name.setText(itemData.getName());

        btnSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseOpenHelper.getInstance(getActivity()).updateCardData(rowId,edtCardNumber.getText().toString(), fromMonth.getText().toString(), fromYear.getText().toString()
                        , toMonth.getText().toString(), toYear.getText().toString(), cvv.getText().toString(), name.getText().toString());

                SavedCardsFragment savedCardsFragment = new SavedCardsFragment();
                Bundle bundle = new Bundle();
                bundle.putBoolean("isTick", false);
                savedCardsFragment.setArguments(bundle);
                FragmentManager fragmentManager1 = getActivity().getSupportFragmentManager();
                fragmentManager1.beginTransaction()
                        .replace(R.id.container, savedCardsFragment)
                        .commit();
            }
        });


    }


}
