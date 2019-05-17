package com.example.myactivities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


   private TextInputLayout txtusername , txtpassord ;
   private Button login , register;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        txtusername = findViewById(R.id.username);
        txtpassord = findViewById(R.id.password);

        register =(Button)findViewById(R.id.register);
        login =(Button)findViewById(R.id.login);

        openHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent   intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();

            }
        });

    }
    public boolean validteusername()
    {
        String fname = txtusername.getEditText().getText().toString();

        if (fname.isEmpty())
        {
            txtusername.setError("ENTER USERNAME");
            return false;
        }
        else {
            txtusername.setError(null);
            return true;
        }
    }
    public boolean validtepassword()
    {
        String fpass = txtpassord.getEditText().getText().toString();

        if (fpass.isEmpty())
        {
            txtpassord.setError("ENTER USERNAME");
            return false;
        }
        else {
            txtpassord.setError(null);
            return true;
        }
    }

    private void submit() {
        if (!validteusername() | !validtepassword())
        {
            return;
        }

        db = openHelper.getWritableDatabase();
        String username = txtusername.getEditText().getText().toString().trim();
        String password = txtpassord.getEditText().getText().toString().trim();
        Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.C0L_2 + " =? AND " + DatabaseHelper.COL_3 + " =? " ,  new String[]{username,password});
        if (c != null){
            if (c.getCount() > 0){
                Toast.makeText(this, "login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            } else if (c.getCount() == 0){
                Toast.makeText(this, " invalid username or password", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
