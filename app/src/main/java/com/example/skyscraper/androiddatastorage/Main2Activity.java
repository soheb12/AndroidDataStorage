package com.example.skyscraper.androiddatastorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.edittext);
        fab = findViewById(R.id.floatingButton);

        fab.setImageResource(R.drawable.round_home_white_18dp);

    }


    public void loadInternalCache(View view)
    {
        File folder = getCacheDir();
        File myFile = new File(folder,"myFile1.txt");

        String data = readData(myFile);
        if(data != null && data != "")
        {
            editText.setText(data);
        }else{
            editText.setText("No Data Was Found");
        }

    }

    public void loadExternalCache(View view)
    {
        File folder = getExternalCacheDir();
        File myFile = new File(folder,"myFile2.txt");

        String data = readData(myFile);
        if(data != null && data != "")
        {
            editText.setText(data);
        }else{
            editText.setText("No Data Was Found");
        }

    }

    public void loadExternalPrivate(View view)
    {

        File folder = getExternalFilesDir("Sky");
        File myFile = new File(folder,"myFile3.txt");

        String data = readData(myFile);
        if(data != null && data != "")
        {
            editText.setText(data);
        }else{
            editText.setText("No Data Was Found");
        }

    }

    public void loadSharedPreferences(View view)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("My Data", Context.MODE_PRIVATE);
        String data = sharedPreferences.getString("myKey","");

        if(data == "")
        {
            data = "No Data Found";
        }

        editText.setText(data);
    }

    public void fabClicked(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void message(String s)
    {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public String readData(File myFile)
    {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myFile);

            int read = -1;
            StringBuffer buffer = new StringBuffer();

            while((read=fileInputStream.read())!=-1)
            {
                buffer.append((char)read); //file contains byte values
            }

            return buffer.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void hideKeyboard(View view) {
        UIUtil.hideKeyboard(this);
    }


}
