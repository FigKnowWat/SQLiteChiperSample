package by.iba_mobile.sqliteciphersample;

import android.content.Context;
import android.text.TextUtils;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import by.iba_mobile.sqliteciphersample.tables.PhoneContract;

/**
 * Created by Alex on 12.07.2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static DBHelper instance;
    private static final String DATABASE_NAME = "SQLiteCipherSample.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        onUpgrade(sqLiteDatabase, 0, 1);
        instance = this;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        execSQL(sqLiteDatabase, PhoneContract.getSql(i, i1));
    }

    private void execSQL(SQLiteDatabase db, final String sql) {
        if (sql == null) {
            return;
        }

        if (sql.contains("//")) {
            for (String command : sql.split("//")) {
                if (TextUtils.isEmpty(command)) {
                    continue;
                }
                db.execSQL(command);
            }
        } else {
            db.execSQL(sql);
        }
    }
}
