package networking;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ramakant.virualatm.DataHub;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ramakant on 3/12/2016.
 */
public class UserAuthentication {
    protected TokenReceived tokenReceived;
    protected Context context;

    public UserAuthentication(Context context, TokenReceived tokenReceived) {
        this.tokenReceived = tokenReceived;
        this.context = context;
    }

    public void userAuthentication(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    String token = jsonObject.getString(DataHub.AUTHENTICATION_TOKEN);
                    tokenReceived.sendToken(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tokenReceived.sendToken("error");
                Log.e("Volley", error.toString());
            }
        });

        VolleyApplication.getInstance().getRequestQueue().add(jsonArrayRequest);

    }

    public interface TokenReceived {
        void sendToken(String token);
    }
}
