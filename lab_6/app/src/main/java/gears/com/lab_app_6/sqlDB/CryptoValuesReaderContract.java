package gears.com.lab_app_6.sqlDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Yashodhan on 19-Feb-18, for Lab_app_6
 */

public class CryptoValuesReaderContract extends SQLiteOpenHelper {

    private static final String DB_NAME = "crypto.db";
    private static final int VERSION = 1;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CryptoValuesEntry.TABLE_NAME + " (" +
                    CryptoValuesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CryptoValuesEntry.COLUMN_NAME_CRYPTO_NAME+ " TEXT," +
                    CryptoValuesEntry.COLUMN_NAME_CRYPTO_SYMBOL + " TEXT,"+
                    CryptoValuesEntry.COLUMN_NAME_CRYPTO_PRICE + " FLOAT" +
                    " )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CryptoValuesEntry.TABLE_NAME;


    public CryptoValuesReaderContract(Context context) {
        super(context, DB_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }

    public void addValues(){

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public static class CryptoValuesEntry implements BaseColumns{
        public static final String TABLE_NAME = "cryptocurrencies";
        public static final String COLUMN_NAME_CRYPTO_NAME = "crypto_name";
        public static final String COLUMN_NAME_CRYPTO_SYMBOL = "crypto_symbol";
        public static final String COLUMN_NAME_CRYPTO_PRICE = "crypto_price";

    }
}
