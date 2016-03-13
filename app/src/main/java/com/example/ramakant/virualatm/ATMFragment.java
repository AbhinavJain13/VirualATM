package com.example.ramakant.virualatm;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/12/2016.
 */


public class ATMFragment extends Fragment {

    NavigationDrawer mParentActivity;
    Button btnGeneratePin, btnSaveCard;
    TextView txtPinGenerated;
    EditText edtCardNumber, fromMonth, fromYear, toMonth, toYear, cvv, name;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_atm, container, false);
        initializeViews(rootView);

        return rootView;

    }

    void initializeViews(View rooView)
    {
        btnGeneratePin  = (Button) rooView.findViewById(R.id    .btnGeneratePin);
        txtPinGenerated = (TextView) rooView.findViewById(R.id.txtPinGenerated);
        btnSaveCard = (Button) rooView.findViewById(R.id.btnSaveCard);
        edtCardNumber = (EditText) rooView.findViewById(R.id.edtCardNumber);
        fromMonth = (EditText) rooView.findViewById(R.id.fromMonth);
        fromYear = (EditText) rooView.findViewById(R.id.fromYear);
        toMonth = (EditText) rooView.findViewById(R.id.toMonth);
        toYear = (EditText) rooView.findViewById(R.id.toYear);
        cvv = (EditText) rooView.findViewById(R.id.cvv);
        name = (EditText) rooView.findViewById(R.id.name);

        btnGeneratePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog dialogFrag = new CustomDialog();
                dialogFrag.updateContext(ATMFragment.this);
                dialogFrag.show(getChildFragmentManager(), "hak");
            }
        });

        btnSaveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Do you want to save the card ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseOpenHelper.getInstance(getActivity()).insertCardData(edtCardNumber.getText().toString(),fromMonth.getText().toString(),fromYear.getText().toString()
                        ,toMonth.getText().toString(), toYear.getText().toString(),cvv.getText().toString(),name.getText().toString());
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mParentActivity = (NavigationDrawer) context;
        mParentActivity.mTitle =  "ATM";
    }

    public void pinGenerated()
    {
        txtPinGenerated.setVisibility(View.VISIBLE);
        txtPinGenerated.setText("Pin Generated : 5437909890 ");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        mParentActivity.restoreActionBar();

    }

   public static class CustomDialog extends DialogFragment
    {
        Button btnGenePin;
        ATMFragment parentFragment;

        public CustomDialog()
        {

        }

        void updateContext(ATMFragment parentFragment)
        {
            this.parentFragment = parentFragment;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView  =inflater.inflate(R.layout.dialog_layout,container,false);
            getDialog().setTitle("Generate Pin");
            btnGenePin = (Button) rootView.findViewById(R.id.btnGenePin);

            btnGenePin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    parentFragment.pinGenerated();
                    CustomDialog.this.dismiss();
                }
            });

            return rootView;
        }
    }


}
