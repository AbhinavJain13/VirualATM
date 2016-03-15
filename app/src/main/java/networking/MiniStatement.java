package networking;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import Utils.DataHub;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ramakant on 3/13/2016.
 */
public class MiniStatement {

    public static String TAG = MiniStatement.class.getSimpleName();
    private MiniStatementInfo miniStatementInfo;
    private Context mContext;
    private String token;
    private String clientId;
    private String accountNumber;

    public MiniStatement(Context context, String token, String clientId, String accountNumber, final MiniStatementInfo miniStatementInfo) {
        this.mContext = context;
        this.token = token;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.miniStatementInfo = miniStatementInfo;

        String finalUrl = DataHub.MINI_STATEMENT_URL +
                DataHub.QUESTION_MARK_OPERATOR +
                DataHub.CLIENT_ID + DataHub.EQUAL_OPERATOR + DataHub.USER_ID +
                DataHub.AND_OPERATOR +
                DataHub.AUTHENTICATION_TOKEN + DataHub.EQUAL_OPERATOR + token +
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
                        jsonObject = response.getJSONObject(1);
                        //call back to balance information fragment
                        miniStatementInfo.miniStatementInfo(jsonObject.getString(DataHub.TRANSACTION_DATE),
                                jsonObject.getString(DataHub.CLOSING_BALANCE),
                                jsonObject.getString(DataHub.ACCOUNT_NO),
                                jsonObject.getString(DataHub.CREDIT_DEBIT_FLAG),
                                jsonObject.getString(DataHub.TRANSACTION_AMOUNT),
                                jsonObject.getString(DataHub.REMARK));
                    } else if (responseCode == 401) {
                        String msg = jsonObject.getString("message");
                        String description = jsonObject.getString("description");
                        Toast.makeText(mContext, msg + ": " + description, Toast.LENGTH_LONG).show();
                        //call back to balance information fragment
                        miniStatementInfo.unauthorizedUser(responseCode, msg);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                miniStatementInfo.volleyError(error.toString());
            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(jsonArrayRequest);
    }

    interface MiniStatementInfo {
        void miniStatementInfo(String transactionDate, String closingBalance, String accountNumber, String creditDebitFlag, String transactionAmount, String remark);

        void unauthorizedUser(int code, String msg);

        void volleyError(String error);
    }

}
