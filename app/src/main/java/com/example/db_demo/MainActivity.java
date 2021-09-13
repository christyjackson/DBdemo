package com.example.db_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button viewall;
    Button add;
    EditText et_name, et_regno;
    ListView lv_studentlist;
    DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.btn_add);
        viewall = (Button)findViewById(R.id.btn_ViewAll);
        et_name = (EditText)findViewById(R.id.et_Name);
        et_regno = findViewById(R.id.et_Regno);
        lv_studentlist =findViewById(R.id.lv_student);
        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel;
                try {
                    studentModel = new StudentModel(et_name.getText().toString(),Integer.parseInt(et_regno.getText().toString()),1);
                    Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }

                catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error Creating Student", Toast.LENGTH_SHORT).show();
                    studentModel = new StudentModel("error", 0, -1);
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean success = dataBaseHelper.addOne(studentModel);
                Toast.makeText(MainActivity.this, "success = " + success, Toast.LENGTH_SHORT).show();

            }
        });





        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                //Next create an List and capture the list items from getEeryone method which we created in DataBaseHelper.java
                List<StudentModel> everyone = dataBaseHelper.getveryone();
                ArrayAdapter studentArrayAdapter = new ArrayAdapter<StudentModel>(MainActivity.this,android.R.layout.simple_list_item_1,everyone);
                lv_studentlist.setAdapter(studentArrayAdapter);

                //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        lv_studentlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                StudentModel clickedStudent = (StudentModel) parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteOne(clickedStudent);
                Toast.makeText(MainActivity.this, "Item Deleted" + clickedStudent, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
