package fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramakant.virualatm.NavigationDrawer;
import com.example.ramakant.virualatm.R;

import Utils.DataHub;
import networking.BalanceEnquiry;
import networking.SharedPreference;

/**
 * Created by User on 14/03/2016.
 */
public class BalanceInfo extends Fragment {

    NavigationDrawer activity;
    private TextView txtAccountNumber;
    private TextView txtBalance;
    private TextView txtAccountType;
    private TextView txtBalanceTime;
    private ProgressDialog progressDialog;
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.balance_info, container, false);
        initViewIds();
        fillUIData();
        return rootView;

    }

    private void fillUIData() {
        showProgressDialog();
        String token = SharedPreference.getInstance(getActivity()).getFromSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
        new BalanceEnquiry(getActivity(), token, DataHub.USER_ID, DataHub.ACCOUNT_NO_CUST_ID_1, new BalanceEnquiry.BalanceOfYourAccount() {
            @Override
            public void balanceInfo(String balance, String accountNumber, String accountType, String balanceTime) {
                txtAccountNumber.setText(accountNumber);
                txtBalance.setText(balance);
                txtAccountType.setText(accountType);
                txtBalanceTime.setText(balanceTime);
                dismissProgressDialog();
            }

            @Override
            public void unauthorizedUser(int code, String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
                dismissProgressDialog();
            }

            @Override
            public void volleyError(String error) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void initViewIds() {
        txtAccountNumber = (TextView) rootView.findViewById(R.id.txtAccountNumber);
        txtBalance = (TextView) rootView.findViewById(R.id.txtBalance);
        txtAccountType = (TextView) rootView.findViewById(R.id.txtAccountType);
        txtBalanceTime = (TextView) rootView.findViewById(R.id.txtBalanceTime);
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NavigationDrawer)activity;
        this.activity.mTitle = "Balance Info";
        this.activity.restoreActionBar();
    }
}
