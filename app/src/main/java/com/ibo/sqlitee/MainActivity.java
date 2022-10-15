package com.ibo.sqlitee;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import Controller.DatabaseHandler;
import Model.Person;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new DatabaseHandler(this);
        db.addPerson(new Person(26,"ibo","whabeh"));
        db.addPerson(new Person(32,"shadi","whabeh"));
        db.addPerson(new Person(30,"abo","latakani"));
        db.addPerson(new Person(24,"mhd","sheo"));

//        List<Person> personList=db.getPeople();
//        for(Person person:personList){
//            String myInfo="ID: "+person.getId()+" Name: "+person.getName()+" lastName : "+person.getLname()+" Age : "+person.getAge();
//            Log.d("people",myInfo);
//        }
        Person p=db.getPerson(1);
        String myInfo="ID: "+p.getId()+" Name: "+p.getName()+" lastName : "+p.getLname()+" Age : "+p.getAge();
        Log.d("people",myInfo);

        Person p2=db.getPerson(1);
        p2.setName("ahmad");
        p2.setLname("alwai");
        p2.setAge(22);
        int newdata=db.updatePerson(p2);
        String myInfo2="ID: "+p.getId()+" Name: "+p.getName()+" lastName : "+p.getLname()+" Age : "+p.getAge();
        Log.d("people",myInfo2);

        Person p3=db.getPerson(2);
        db.deletePerson(p3);

        int numPer=db.getNumPerson();




    }
}