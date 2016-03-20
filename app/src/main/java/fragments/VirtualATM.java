package fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramakant.virualatm.NavigationDrawer;
import com.example.ramakant.virualatm.R;

import Utils.DataHub;
import networking.FundTransferToATM;
import networking.SharedPreference;

/**
 * Created by Ramakant on 3/19/2016.
 */
public class VirtualATM extends android.support.v4.app.Fragment {

    NavigationDrawer activity;
    private View rootView;
    private EditText edtPin;
    private Button btnYes;
    private TextView txtResult;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.virtualatm, container, false);
        initViewIds();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NavigationDrawer) activity;
        this.activity.mTitle = "ATM";
    }

    private void initViewIds() {
        txtResult = (TextView) rootView.findViewById(R.id.txtResult);
        edtPin = (EditText) rootView.findViewById(R.id.edtPin);
        btnYes = (Button) rootView.findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPin.getText().toString().equals("7045788997")) {
                    withdrawAmount();
                }
            }
        });
    }

    private void withdrawAmount() {
        showProgressDialog();
        String amount = SharedPreference.getInstance(getActivity()).getFromSharedPreference("AMOUNT", "0");
        String token = SharedPreference.getInstance(getActivity()).getFromSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
        new FundTransferToATM(getActivity(), token, DataHub.USER_ID, amount, DataHub.ACCOUNT_NO_CUST_ID_1, DataHub.ATM_ACCOUNT_NO, new FundTransferToATM.AfterFundTransferToATM() {
            @Override
            public void AfterMoneyTransfer(String destinationAccount, String transectionDate, String referenceNo, String transactionAmount, String payeename, String payeeId, String status) {
                dismissProgressDialog();
                txtResult.setVisibility(View.VISIBLE);
            }

            @Override
            public void unauthorizedUser(int code, String msg) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void volleyError(String error) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

    }
}
