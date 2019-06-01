package com.example.examandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.acl.Group;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String FILE_NAME = "note1.txt";
    public final static String FILE_FATH = "sdcard/demo";

    EditText etName, etFileContent;
    Button btnWrite;
    Button btnRead;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        String nameFile =  etName.getText().toString();
        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }
    public void anhXa(){
        etName = (EditText) findViewById(R.id.etName);
        etFileContent = (EditText) findViewById(R.id.etFileContent);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnRead = (Button) findViewById(R.id.btnRead);
    }

    private void onSaveInternal(){
        String data = this.etFileContent.getText().toString();
        try {

            // Mo 1 luong file
            FileOutputStream out = this.openFileOutput(FILE_NAME, MODE_PRIVATE);
            // ghi du lieu
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this,"File saved!",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    private void onReadInternal(){
        try {
            FileInputStream in = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null){
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onSaveExternal(){
        File directory = new File(FILE_FATH);
        File file = new File(FILE_FATH+FILE_NAME);
        try {
            if (!directory.exists()){
                directory.mkdir();
            }
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            out.flush();
            out.close();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void onReadExternal(){
        File file = new File(FILE_FATH+FILE_NAME);
        try {
            FileInputStream in =new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer sb = new StringBuffer();
            String line = reader.readLine();
            while (line != null){
                sb.append(line);
                line = reader.readLine();
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int idChecked = radioGroup.getCheckedRadioButtonId();
        switch (v.getId()){
            case R.id.btnWrite :
                if (idChecked == R.id.rbI){
                    onSaveInternal();
                }else {
                    onSaveExternal();
                }
            case R.id.btnRead :
                if (idChecked == R.id.rbI){
                    onReadInternal();
                }else {
                    onReadExternal();
                }
            default:
                break;
        }

    }
}
