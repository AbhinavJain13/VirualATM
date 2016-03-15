package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Utils.CardItemData;

/**
 * Created by admin on 3/13/2016.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {

    public static String CardTable = "cardTable";
    public static String CardNumber = "cardNumber";
    public static String FromDateMonth= "fromDateMonth";
    public static String FromDateYear= "fromDateYear";
    public static String ToDateMonth= "toDateMonth";
    public static String ToDateYear= "toDateYear";
    public static String CVV = "cvv";
    public static String Name = "name";
    public static String databaseName = "ATMDatabaseTable";
    public static DatabaseOpenHelper dbHelper;


    String CreateDatabase = "CREATE TABLE IF NOT EXISTS "+CardTable +" (_id integer primary key autoincrement, "+
            CardNumber + " TEXT, "+
            FromDateMonth + " TEXT, "+
            FromDateYear + " TEXT, "+
            ToDateMonth + " TEXT, "+
            ToDateYear + " TEXT, "+
            CVV + " TEXT, "+
            Name + " TEXT );";


    public DatabaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

   public static DatabaseOpenHelper getInstance(Context context)
    {
        if(dbHelper == null)
        {
            dbHelper = new DatabaseOpenHelper(context,DatabaseOpenHelper.databaseName,null,1);
        }

        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(CreateDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

   public void insertCardData(String cardNumber, String fromDateMonth, String fromDateYear, String toDateMonth, String toDateYear, String cvv, String name)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CardNumber, cardNumber);
            values.put(FromDateMonth,fromDateMonth);
            values.put(FromDateYear,fromDateYear);
            values.put(ToDateMonth,toDateMonth);
            values.put(ToDateYear,toDateYear);
            values.put(CVV,cvv);
            values.put(Name, name);
            long rowInserted = db.insert(CardTable,null,values);
            Log.e("Row inserted", rowInserted + "  ");
        }

    }

    public void updateCardData(int rowId,String cardNumber, String fromDateMonth, String fromDateYear, String toDateMonth, String toDateYear, String cvv, String name)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CardNumber, cardNumber);
            values.put(FromDateMonth,fromDateMonth);
            values.put(FromDateYear,fromDateYear);
            values.put(ToDateMonth,toDateMonth);
            values.put(ToDateYear,toDateYear);
            values.put(CVV,cvv);
            values.put(Name, name);
            long rowUpdated = db.update(CardTable,values,"_id = ?",new String[] {String.valueOf(rowId)});
            Log.e("Row Updated", rowUpdated + "  ");
        }
    }

    public Cursor getCardData()
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
           return db.query(CardTable,null,null,null,null,null,null);
        }

        return null;
    }

    public CardItemData getDataFromRowid(Integer rowid)
    {
        if(dbHelper!=null)
        {
            SQLiteDatabase db = dbHelper.dbHelper.getWritableDatabase();
            Cursor cursor =  db.query(CardTable, null, "_id = ?", new String[]{String.valueOf(rowid)}, null, null, null);

            if(cursor!=null)
            {
                cursor.moveToFirst();
                CardItemData data = CardItemData.getListItem(cursor);
                return data;
            }

        }

        return null;
    }
}
