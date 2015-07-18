package by.iba_mobile.sqliteciphersample;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import net.sqlcipher.database.SQLiteDatabase;

import by.iba_mobile.sqliteciphersample.R;
import by.iba_mobile.sqliteciphersample.tables.PhoneContract;

public class MainActivity extends Activity implements View.OnClickListener {

    private final String pass = "passwTest";
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase.loadLibs(this);
        dbHelper = new DBHelper(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_get).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void get() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase(pass);

        Cursor cursor = getPhones(sqLiteDatabase);
        cursor.moveToPosition(-1);
        if (cursor.moveToNext()) {
            ((EditText) findViewById(R.id.text_number)).setText(cursor.getString(cursor.getColumnIndex(PhoneContract.Columns.NUMBER)));
            ((EditText) findViewById(R.id.text_name)).setText(cursor.getString(cursor.getColumnIndex(PhoneContract.Columns.NAME)));
        }
        sqLiteDatabase.close();
    }

    private Cursor getPhones(SQLiteDatabase db) {
        Cursor cursor = db.query(PhoneContract.TABLE_NAME, null, null, null, null,
                null, null);
        startManagingCursor(cursor);
        return cursor;
    }

    private void add() {
        SQLiteDatabase db = dbHelper.getWritableDatabase(pass);
        ContentValues values = new ContentValues();

        values.put(PhoneContract.Columns.NAME, ((EditText) findViewById(R.id.text_name)).getText().toString());
        values.put(PhoneContract.Columns.NUMBER, ((EditText) findViewById(R.id.text_number)).getText().toString());
        db.insert(PhoneContract.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                add();
                break;

            case R.id.btn_get:
                get();
                break;
        }
    }
}
