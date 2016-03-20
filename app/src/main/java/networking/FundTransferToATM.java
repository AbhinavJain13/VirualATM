package networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import Utils.DataHub;

/**
 * Created by Ramakant on 3/19/2016.
 */
public class FundTransferToATM {
    public static String TAG = FundTransferToATM.class.getSimpleName();
    private Context mContext;
    private String token;
    private String clientId;
    private String sourceAccount;
    private String DestinationAccountATM;

    private AfterFundTransferToATM afterFundTransferToATM;

    public FundTransferToATM(Context context, String token, String clientId, String amount, String sourceAccount, String destinationAccountATM, final AfterFundTransferToATM afterFundTransferToATM) {
        this.mContext = context;
        this.token = token;
        this.clientId = clientId;
        this.sourceAccount = sourceAccount;
        this.DestinationAccountATM = destinationAccountATM;
        this.afterFundTransferToATM = afterFundTransferToATM;

        String finalUrl = DataHub.FUND_TRANSFER_URL +
                DataHub.QUESTION_MARK_OPERATOR +
                DataHub.CLIENT_ID + DataHub.EQUAL_OPERATOR + clientId +
                DataHub.AND_OPERATOR +
                DataHub.AUTHENTICATION_TOKEN + DataHub.EQUAL_OPERATOR + token +
                DataHub.AND_OPERATOR +
                DataHub.SOURCE_ACCOUNT + DataHub.EQUAL_OPERATOR + sourceAccount +
                DataHub.AND_OPERATOR +
                DataHub.DESTINATION_ACCOUNT + DataHub.EQUAL_OPERATOR + destinationAccountATM +
                DataHub.AND_OPERATOR +
                DataHub.AMOUNT + DataHub.EQUAL_OPERATOR + 100;

        URL url = null;
        try {
            url = new URL(finalUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    int responseCode = jsonObject.getInt("code");
                    if (responseCode == 200) {
                        jsonObject = response.getJSONObject(1);
                        String account = jsonObject.getString(DataHub.DESTINATION_ACCOUNT_RESPONSE);
                        String date = jsonObject.getString(DataHub.TRANSACTION_DATE_RESPONSE);
                        String reference = jsonObject.getString(DataHub.REFERENCE_RESPONSE);
                        String amount = jsonObject.getString(DataHub.TRANSACTION_AMOUNT_RESPONSE);
                        String payeeName = jsonObject.getString(DataHub.PAYEE_NAME_RESPONSE);
                        String payeeId = jsonObject.getString(DataHub.PAYEE_ID_RESPONSE);
                        String status = jsonObject.getString(DataHub.STATUS_RESPONSE);

                        //call back to balance information fragment
                        afterFundTransferToATM.AfterMoneyTransfer(account, date, reference, amount, payeeName, payeeId, status);
                    } else if (responseCode == 401) {
                        String msg = jsonObject.getString("message");
                        String description = jsonObject.getString("description");
                        Toast.makeText(mContext, msg + ": " + description, Toast.LENGTH_LONG).show();
                        //call back to balance information fragment
                        afterFundTransferToATM.unauthorizedUser(responseCode, msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                afterFundTransferToATM.volleyError(error.toString());
            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(jsonArrayRequest);
    }

    public interface AfterFundTransferToATM {
        void AfterMoneyTransfer(String destinationAccount, String transectionDate, String referenceNo, String transactionAmount, String payeename, String payeeId, String status);

        void unauthorizedUser(int code, String msg);

        void volleyError(String error);
    }
}
