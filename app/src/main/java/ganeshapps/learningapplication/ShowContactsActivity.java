package ganeshapps.learningapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ganesh on 3/12/16.
 */
public class ShowContactsActivity extends AppCompatActivity{

    SQLiteDatabase contactDB;
    TextView contactListEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcontacts);
        contactListEditText = (TextView) findViewById(R.id.contactListEditText);
        openOrCreatDB();

        Cursor cursor = contactDB.rawQuery("SELECT * FROM contacts",null);
        int idColumn = cursor.getColumnIndex("id");
        int nameColumn = cursor.getColumnIndex("name");
        int emailColumn = cursor.getColumnIndex("email");
        int phoneColumn = cursor.getColumnIndex("phone");

        cursor.moveToFirst();
        String contactList = "";
        if(cursor!=null && (cursor.getCount()>0))
        {
            do {

                contactList+=(cursor.getString(idColumn)+" : "+cursor.getString(nameColumn)+
                        " : "+cursor.getString(emailColumn)+" : "+cursor.getString(phoneColumn)+"\n");
            }while (cursor.moveToNext());

            contactListEditText.setText(contactList);
        }
        else {
        }
    }

    private void openOrCreatDB()
    {
        try{

            contactDB = this.openOrCreateDatabase("MyContacts",MODE_PRIVATE,null);
            contactDB.execSQL("CREATE TABLE IF NOT EXISTS contacts" +
                    "(id integer primary key AUTOINCREMENT, name VARCHAR, email VARCHAR,phone integer);");
        }
        catch (Exception ex)
        {
            Log.e("CONTACTS ERROR","Error Creating Database");
        }
    }
}
