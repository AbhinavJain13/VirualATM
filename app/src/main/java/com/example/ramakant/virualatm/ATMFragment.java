package com.example.ramakant.virualatm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by admin on 3/12/2016.
 */


public class ATMFragment extends Fragment {

    NavigationDrawer mParentActivity;
    Button btnGeneratePin;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_atm, container, false);
        initializeViews(rootView);

        return rootView;

    }

    void initializeViews(View rooView)
    {
        btnGeneratePin  = (Button) rooView.findViewById(R.id.btnGeneratePin);
        btnGeneratePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              CustomDialog dialogFrag = new CustomDialog();
                dialogFrag.show(getChildFragmentManager(),"hak");
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mParentActivity = (NavigationDrawer) context;
        mParentActivity.mTitle =  "ATM";
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        mParentActivity.restoreActionBar();

    }

   public static class CustomDialog extends DialogFragment
    {
        Button btnGenePin;

        public CustomDialog()
        {

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

                }
            });

            return rootView;
        }
    }


}
