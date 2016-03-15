package Utils;

import android.database.Cursor;

import database.DatabaseOpenHelper;

/**
 * Created by admin on 3/13/2016.
 */
public class CardItemData
{

    String cardNumber, toMonth, toYear, fromMonth, fromYear, cvv, name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    Integer id;


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToYear() {
        return toYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CardItemData getListItem(Cursor cursor)
    {
         CardItemData data = new CardItemData();
        data.setCardNumber(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.CardNumber)));
        data.setCvv(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.CVV)));
        data.setFromMonth(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.FromDateMonth)));
        data.setToMonth(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.ToDateMonth)));
        data.setFromYear(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.FromDateYear)));
        data.setToYear(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.ToDateYear)));
        data.setName(cursor.getString(cursor.getColumnIndex(DatabaseOpenHelper.Name)));
        data.setId(cursor.getInt(cursor.getColumnIndex("_id")));

        return data;
    }

}