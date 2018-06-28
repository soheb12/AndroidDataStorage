package com.example.skyscraper.androiddatastorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
        fab = findViewById(R.id.floatingButton);
        fab.setImageResource(R.drawable.round_open_in_new_white_18dp);


    }

    public void saveInternalCache(View view)
    {
        String data = editText.getText().toString();
        File folder = getCacheDir();
        File myFile = new File(folder,"myFile1.txt");
        writeData(data,myFile);
    }

    public void saveExternalCache(View view)
    {
        String data = editText.getText().toString();
        File folder = getExternalCacheDir();
        File myFile = new File(folder,"myFile2.txt");

        writeData(data,myFile);
    }

    public void saveExternalPrivate(View view)
    {
        String data = editText.getText().toString();
        File folder = getExternalFilesDir("Sky");
        File myFile = new File(folder,"myFile3.txt");

        writeData(data,myFile);
    }

    public void saveSharedPreference(View view)
    {
        String data = editText.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("My Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPreferences.edit();
        editor.putString("myKey",data);
        editor.commit();

        message(data + " was stored in shared preferences");
    }

    public void fabClicked(View view)
    {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }


    public void message(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void writeData(String data,File myFile)
    {
        FileOutputStream fileOutputStream = null;
        try {

            fileOutputStream = new FileOutputStream(myFile);
            fileOutputStream.write(data.getBytes());
            message(data + " was written succesfully " + myFile.getAbsolutePath());

        } catch (FileNotFoundException e) {
            message("not found");
            e.printStackTrace();
        } catch (IOException e) {
            message("io exception");
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void hideKeyboard(View view) {

        UIUtil.hideKeyboard(this);
    }
}
