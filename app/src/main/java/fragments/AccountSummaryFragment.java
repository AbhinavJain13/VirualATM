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
import networking.BankAccountSummary;
import networking.SharedPreference;

/**
 * Created by User on 14/03/2016.
 */
public class AccountSummaryFragment extends Fragment {

    NavigationDrawer activity;
    private TextView mAccountNumber;
    private TextView mBalance;
    private TextView mAccount;
    private TextView mAccountType;
    private TextView mCustId;
    private TextView mAccountStatus;
    private TextView mMobile;
    private TextView mCategory;
    private View rootView;
    private ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.account_summary, container, false);
        initViewIds();
        accountSummary();
        return rootView;
    }

    private void initViewIds() {
        mAccountNumber = (TextView) rootView.findViewById(R.id.txtAccountNum);
        mBalance = (TextView) rootView.findViewById(R.id.txtbalance);
        mAccount = (TextView) rootView.findViewById(R.id.txtAccount);
        mAccountType = (TextView) rootView.findViewById(R.id.txtAccountType);
        mCustId = (TextView) rootView.findViewById(R.id.txtCustomerId);
        mAccountStatus = (TextView) rootView.findViewById(R.id.txtAccountStatus);
        mMobile = (TextView) rootView.findViewById(R.id.txtMobileNumber);
        mCategory = (TextView) rootView.findViewById(R.id.txtCategory);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NavigationDrawer)activity;
        this.activity.mTitle = "Account Summary";
        this.activity.restoreActionBar();
    }

    private void accountSummary() {
        showProgressDialog();
        String token = SharedPreference.getInstance(getActivity()).getFromSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
        new BankAccountSummary(getActivity(), token, DataHub.USER_ID, DataHub.ACCOUNT_NO_CUST_ID_1, DataHub.CUST_ID_1, new BankAccountSummary.BankAccountDetails() {
            @Override
            public void accountDetails(String balance, String accountNumber, String accountType, String product_desc, String productType, String subProductType, String custId, String accountStatus, String mobileNo, String productCategory) {
                mAccountNumber.setText(accountNumber);
                mBalance.setText(balance);
                mAccountType.setText(accountType);
                mAccount.setText(product_desc);
                mCustId.setText(custId);
                mAccountStatus.setText(accountStatus);
                mMobile.setText(mobileNo);
                mCategory.setText(productCategory);
                dismissProgressDialog();
            }

            @Override
            public void unauthorizedUser(int code, String s, String msg) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), s + ": " + msg, Toast.LENGTH_LONG).show();
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
