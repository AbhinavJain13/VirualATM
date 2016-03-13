package networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ramakant.virualatm.DataHub;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ramakant on 3/13/2016.
 */
public class BankAccountSummary {

    public static String TAG = BankAccountDetails.class.getSimpleName();
    private BankAccountDetails bankAccountDetails;
    private Context mContext;
    private String token;
    private String clientId;
    private String accountNumber;
    private String customerId;

    public BankAccountSummary(Context context, String token, String clientId, String accountNumber, String customerId, final BankAccountDetails bankAccountDetails) {
        this.mContext = context;
        this.token = token;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.bankAccountDetails = bankAccountDetails;

        String finalUrl = DataHub.BANK_ACCOUNT_SUMMARY_URL +
                DataHub.QUESTION_MARK_OPERATOR +
                DataHub.CLIENT_ID + DataHub.EQUAL_OPERATOR + DataHub.USER_ID +
                DataHub.AND_OPERATOR +
                DataHub.AUTHENTICATION_TOKEN + DataHub.EQUAL_OPERATOR + token +
                DataHub.AND_OPERATOR +
                DataHub.CUSTOMER_ID + DataHub.EQUAL_OPERATOR + customerId +
                DataHub.AND_OPERATOR +
                DataHub.ACCOUNT_NO + DataHub.EQUAL_OPERATOR + accountNumber;

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
                        //call back to balance information fragment
                        jsonObject = response.getJSONObject(1);
                        bankAccountDetails.accountDetails(jsonObject.getString(DataHub.BALANCE), jsonObject.getString(DataHub.ACCOUNT_NO),
                                jsonObject.getString(DataHub.ACCOUNT_TYPE),
                                jsonObject.getString(DataHub.PRODUCT_DISC),
                                jsonObject.getString(DataHub.PRODUCT_TYPE), jsonObject.getString(DataHub.SUB_PRODUCT_TYPE),
                                jsonObject.getString(DataHub.CUSTOMER_ID), jsonObject.getString(DataHub.ACCOUNT_STATUS),
                                jsonObject.getString(DataHub.MOBILE_NUMBER), jsonObject.getString(DataHub.PRODUCT_CATEGORY));
                    } else if (responseCode == 401) {
                        String msg = jsonObject.getString("message");
                        String description = jsonObject.getString("description");
                        Toast.makeText(mContext, msg + ": " + description, Toast.LENGTH_LONG).show();
                        //call back to balance information fragment
                        bankAccountDetails.unauthorizedUser(responseCode, msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                bankAccountDetails.volleyError(error.toString());
            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(jsonArrayRequest);
    }

    interface BankAccountDetails {
        void accountDetails(String balance, String accountNumber, String accountType, String product_desc, String productType, String subProductType,
                            String custId, String accountStatus, String mobileNo, String productCategory);

        void unauthorizedUser(int code, String msg);

        void volleyError(String error);
    }


}
