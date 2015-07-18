package by.iba_mobile.sqliteciphersample.tables;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 12.07.2015.
 */
public class PhoneContract {
    private static String TAG = PhoneContract.class.getSimpleName();
    public static final String TABLE_NAME = "PhoneContract";

    public interface Columns extends BaseColumns {
        String NUMBER = "Number";
        String NAME = "Name";
    }

    public static final String[] PROJECTION = {Columns._ID,
            Columns.NUMBER, Columns.NAME,};

    public static final Map<String, String> PROJECTION_MAP = new HashMap<String, String>();

    static {
        PROJECTION_MAP.put(Columns._ID, Columns._ID);
        PROJECTION_MAP.put(Columns.NUMBER, Columns.NUMBER);
        PROJECTION_MAP.put(Columns.NAME, Columns.NAME);
    }

    public static final Uri CONTENT_URI = Uri.parse("content://Tereschuk" + "/" + TABLE_NAME);

    private static final String MIME_TYPE = "vnd.Tereschuk" + "."
            + TABLE_NAME;
    public static final String CONTENT_TYPE_LIST = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/" + MIME_TYPE;
    public static final String CONTENT_TYPE_ITEM = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/" + MIME_TYPE;

    public static String getSql(final int oldVersion, final int newVersion) {
        if (oldVersion == 0) {
            return getCreateSql();
        }
        return getUpgradeSql();
    }

    public static String getCreateSql() {
        StringBuilder builder = new StringBuilder();

        builder.append("DROP TABLE IF EXISTS ").append(TABLE_NAME);

        builder.append("//");
        builder.append("CREATE TABLE ").append(TABLE_NAME).append(" (");
        builder.append(Columns._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        builder.append(Columns.NUMBER).append(" TEXT COLLATE LOCALIZED, ");
        builder.append(Columns.NAME).append(" TEXT COLLATE LOCALIZED ");
        builder.append(")");

        return builder.toString();
    }

    public static String getUpgradeSql() {
        return getCreateSql();
    }

}
