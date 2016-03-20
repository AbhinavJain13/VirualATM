package fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ramakant.virualatm.NavigationDrawer;
import com.example.ramakant.virualatm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Utils.DataHub;
import Utils.MiniStatementData;
import adapters.AdapterMiniStatement;
import networking.MiniStatement;
import networking.SharedPreference;

/**
 * Created by User on 15/03/2016.
 */
public class Mini_Statement_Fragment extends Fragment {

    RecyclerView recyclerView;
    List<MiniStatementData> data = new ArrayList<>();
    NavigationDrawer activity;
    private ProgressDialog progressDialog;

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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (NavigationDrawer)activity;
        this.activity.mTitle = "Mini Statement";
        this.activity.restoreActionBar();
    }
    void initializeData()
    {
        showProgressDialog();
        String token = SharedPreference.getInstance(getActivity()).getFromSharedPreference(DataHub.AUTHENTICATION_TOKEN, "invalid_value");
        new MiniStatement(getActivity(), token, DataHub.USER_ID, DataHub.ACCOUNT_NO_CUST_ID_1, new MiniStatement.MiniStatementInfo() {
            @Override
            public void miniStatementInfo(String transactionDate, String closingBalance, String accountNumber, String creditDebitFlag, String transactionAmount, String remark) {
            }

            @Override
            public void unauthorizedUser(int code, String msg, String desc) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), msg + ": " + desc, Toast.LENGTH_LONG).show();
            }

            @Override
            public void volleyError(String error) {
                dismissProgressDialog();
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void miniStatement(JSONArray response) throws JSONException {
                for (int i = 1; i < response.length(); i++) {
                    JSONObject jsonObject = response.getJSONObject(i);
                    MiniStatementData miniData = new MiniStatementData();
                    miniData.setAccnnum(jsonObject.getString(DataHub.ACCOUNT_NO));
                    miniData.setBalance(jsonObject.getString(DataHub.CLOSING_BALANCE));
                    miniData.setDate(jsonObject.getString(DataHub.TRANSACTION_DATE));
                    miniData.setFlag(jsonObject.getString(DataHub.CREDIT_DEBIT_FLAG));
                    miniData.setTransacnamnt(jsonObject.getString(DataHub.TRANSACTION_AMOUNT));
                    miniData.setNotes(jsonObject.getString(DataHub.REMARK));
                    data.add(miniData);
                }
                AdapterMiniStatement adapter = new AdapterMiniStatement(getActivity(), data);
                recyclerView.setAdapter(adapter);
                dismissProgressDialog();
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
