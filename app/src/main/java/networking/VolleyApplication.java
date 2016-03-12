package networking;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ramakant on 3/12/2016.
 */
public class VolleyApplication extends Application {

    private static VolleyApplication sInstance;

    private RequestQueue mRequestQueue;

    public synchronized static VolleyApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());

        sInstance = this;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}
