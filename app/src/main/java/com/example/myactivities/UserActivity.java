package com.example.myactivities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    private Button search;
    SQLiteOpenHelper mydb;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        search = (Button)findViewById(R.id.search);
        mydb = new DatabaseHelper(this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            db = mydb.getReadableDatabase();
                Cursor c = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME , null);
              if (c.getCount() == 0)

              {
                  showmessage("error","nodata available");
              }
              StringBuffer buffer = new StringBuffer();
              while (c.moveToNext())
              {
                  buffer.append("id :" + c.getString(0) + "\n");
                  buffer.append("username: " + c.getString(1) + "\n");
                  buffer.append("password : " + c.getString(2) + "\n");
              }
              showmessage("registered" , buffer.toString());

            }
        });
    }

    private void showmessage( String title , String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
