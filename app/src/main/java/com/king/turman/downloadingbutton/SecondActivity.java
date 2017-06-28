package com.king.turman.downloadingbutton;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by diaoqf on 2017/6/28.
 */

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_second);

        File fileDir = getExternalCacheDir();
        File file = new File(fileDir, "student.txt");

        findViewById(R.id.tx).setOnClickListener(v->{
            Toast.makeText(this,TestClass.name,Toast.LENGTH_SHORT).show();

            Student student = new Student();
            student.setAge(10);
            student.setName("lebron");


            ObjectOutputStream objectOutputStream = null;
            try {
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                objectOutputStream.writeObject(student);
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        findViewById(R.id.read).setOnClickListener(v->{

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));

                Student student = (Student) objectInputStream.readObject();
                Toast.makeText(this, student.getName()+","+student.getAge(),Toast.LENGTH_SHORT).show();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });
    }
}
