package networking;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ramakant on 3/12/2016.
 */
public class SharedPreference {

    private static String PREFERENCE_KEY = "Virtual_ATM";
    private static SharedPreference sharedPreference;
    private SharedPreferences sharedPref;

    private SharedPreference(Context context) {
        if (null == sharedPref)
            sharedPref = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    public static SharedPreference getInstance(Context context) {
        if (sharedPreference == null) sharedPreference = new SharedPreference(context);
        return sharedPreference;
    }

    public void putInSharedPreference(String key, String value) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public String getFromSharedPreference(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);//"invalid_value"
    }

    private SharedPreferences.Editor getEditor() {
        SharedPreferences.Editor editor = sharedPref.edit();
        return editor;
    }
}
