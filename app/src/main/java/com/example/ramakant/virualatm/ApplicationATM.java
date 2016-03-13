package com.example.ramakant.virualatm;

import android.app.Application;
import android.provider.ContactsContract;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/13/2016.
 */
public class ApplicationATM extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseOpenHelper.getInstance(this);
    }
}
