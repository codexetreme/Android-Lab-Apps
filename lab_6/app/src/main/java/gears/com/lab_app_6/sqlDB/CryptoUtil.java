package gears.com.lab_app_6.sqlDB;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import gears.com.lab_app_6.CryptoItem;

/**
 * Created by Yashodhan on 20-Feb-18, for Lab_app_6
 */

public class CryptoUtil {

    public static final int READ_FROM_DB = 0;
    public static final int WRITE_TO_DB = 1;

    private CryptoValuesReaderContract helper;
    private SQLiteDatabase db;
    public CryptoUtil(CryptoValuesReaderContract helper,int read_write_flag){
        this.helper = helper;
        switch (read_write_flag){
            case READ_FROM_DB:
                db = this.helper.getReadableDatabase();
                break;
            case WRITE_TO_DB:
                db = this.helper.getWritableDatabase();
                break;
        }
    }

    public List<CryptoItem> getAllCryptos(){

        String[] projection = {
                CryptoValuesReaderContract.CryptoValuesEntry._ID,
                CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_NAME,
                CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_SYMBOL,
                CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_PRICE

                };
        String sortOrder = CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_NAME + " ASC";

        Cursor cursor = db.query(
                CryptoValuesReaderContract.CryptoValuesEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
                );

        List<CryptoItem> cryptos = new ArrayList<>();
        while (cursor.moveToNext()){
            String name = cursor.getString( cursor.getColumnIndex(CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_NAME));
            String sym = cursor.getString( cursor.getColumnIndex(CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_SYMBOL));
            float price = cursor.getFloat( cursor.getColumnIndex(CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_PRICE));
            cryptos.add(new CryptoItem(name,sym,price));
        }
        cursor.close();
        return cryptos;
    }


    public void insertValues(CryptoItem item){
        String query = "insert into " + CryptoValuesReaderContract.CryptoValuesEntry.TABLE_NAME +"("
                +CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_NAME +","
                + CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_SYMBOL+ ","
                + CryptoValuesReaderContract.CryptoValuesEntry.COLUMN_NAME_CRYPTO_PRICE+  ") "+ " values( " + item.toString() + ")";
        db.execSQL(query);

    }

}
