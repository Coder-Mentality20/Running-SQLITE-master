package com.example.running_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     DatabaseHelper myDb;
     EditText editTextId,editTextName,editTextCity,editTextEmail,editTextNaissance;
     Button btnAdd,btnViewAll,btnUpdateData,btnDeleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editTextId = (EditText)findViewById(R.id.editId);
        editTextName = (EditText)findViewById(R.id.editFullName);
        editTextCity = (EditText)findViewById(R.id.editCity);
        editTextEmail = (EditText)findViewById(R.id.editEmail);
        editTextNaissance = (EditText)findViewById(R.id.editBornPlace);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnViewAll = (Button)findViewById(R.id.btnAllData);
        btnUpdateData = (Button)findViewById(R.id.btnUpdate);
        btnDeleteData = (Button)findViewById(R.id.btnDelete);
        addData();
        viewAll();
        updateData();
        deleteData();
    }
      public void addData()
       {
         btnAdd.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editTextName.getText().toString(),editTextCity.getText().toString(),editTextEmail.getText().toString(),editTextNaissance.getText().toString());
                  if(isInserted==true)
               {
                   Toast.makeText(MainActivity.this,"Data Inserted!",Toast.LENGTH_LONG).show();
               }
                else
                     Toast.makeText(MainActivity.this,"Data Not Inserted!",Toast.LENGTH_LONG).show();
           }
        });
    }
    public void viewAll()
    {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing Found!");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("ID :" + res.getString(0)+"\n"+"NAME :" + res.getString(1)+"\n"+"CITY :" + res.getString(2)+"\n"+"EMAIL :" + res.getString(3)+"\n"+"BORN :" + res.getString(4)+"\n\n");

                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void updateData()
    {
      btnUpdateData.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              boolean isUpdated = myDb.updateData(editTextId.getText().toString(),editTextName.getText().toString(),editTextCity.getText().toString(),editTextEmail.getText().toString(),editTextNaissance.getText().toString());
              if(isUpdated == true)
              {
                  Toast.makeText(MainActivity.this,"Data Updated!",Toast.LENGTH_LONG).show();
              }
              else
                  Toast.makeText(MainActivity.this,"Data Not Updated!",Toast.LENGTH_LONG).show();
          }
      });
    }
    public void deleteData()
    {
      btnDeleteData.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
              if(deletedRows > 0)
              {
                  Toast.makeText(MainActivity.this,"Data Deleted!",Toast.LENGTH_LONG).show();
              }
              else
                  Toast.makeText(MainActivity.this,"Data Not Deleted!",Toast.LENGTH_LONG).show();
          }
      });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}