package ganeshapps.learningapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

/**
 * Created by ganesh on 3/12/16.
 */
public class AddContactActivity extends AppCompatActivity{

    SQLiteDatabase contactDB = null;
    EditText nameEditText , emailEditText , phoneEditText , contactListEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);
        openOrCreatDB();
        nameEditText = (EditText) findViewById(R.id.name);
        emailEditText = (EditText) findViewById(R.id.email);
        phoneEditText = (EditText) findViewById(R.id.phonenumber);
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

    public void addContactDetails(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        int phone ;
        try{
            phone= Integer.parseInt(phoneEditText.getText().toString());
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            throw  ex;
        }

        contactDB.execSQL("INSERT INTO contacts(name,email,phone) VALUES ('" +
                name + "','" + email + "'," + phone + ");");
    }
}
