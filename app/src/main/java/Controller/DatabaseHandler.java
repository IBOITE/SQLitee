package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

import Model.Person;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler( Context context) {
        super(context,Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PEOPLE_TABLE="CREATE TABLE "+Util.TABLE_NAME+"( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_AGE+"TEXT "+Util.KEY_NAME+"TEXT, "+Util.KEY_LNAME+"TEXT "+")";
        db.execSQL(CREATE_PEOPLE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(db);

    }

    public void addPerson(Person person){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.KEY_AGE,String.valueOf(person.getAge()));
        contentValues.put(Util.KEY_NAME,person.getName());
        contentValues.put(Util.KEY_LNAME,person.getLname());
        database.insert(Util.TABLE_NAME,null,contentValues);
        database.close();
    }



    public Person getPerson(int id){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor =database.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_AGE,Util.TABLE_NAME,Util.KEY_LNAME}
                ,Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        if(cursor!=null)
            cursor.moveToFirst();
        Person person=new Person(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),cursor.getString(2),cursor.getString(3));
        return person;

    }

    public List<Person> getPeople(){
        SQLiteDatabase database=this.getReadableDatabase();
        List<Person> personList=new ArrayList<>();
        String getAll="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=database.rawQuery(getAll,null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast()==false){
//            Person person=new Person();
//            person.setId(Integer.parseInt(cursor.getString(0)));
//            person.setAge(Integer.parseInt(cursor.getString(1)));
//            person.setName(cursor.getString(2));
//            person.setLname(cursor.getString(3));
//            personList.add(person);
//            cursor.moveToNext();
//        }
        if(cursor.moveToFirst())
            do{
                Person person=new Person();
                person.setId(Integer.parseInt(cursor.getString(0)));
                person.setAge(Integer.parseInt(cursor.getString(1)));
                person.setName(cursor.getString(2));
                person.setLname(cursor.getString(3));
                personList.add(person);
            }while (cursor.moveToNext());
        return personList;
    }

    public int updatePerson(Person person){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.KEY_AGE,String.valueOf(person.getAge()));
        contentValues.put(Util.KEY_NAME,person.getName());
        contentValues.put(Util.KEY_LNAME,person.getLname());
        int result= database.update(Util.TABLE_NAME,contentValues,Util.KEY_ID+"=?",new String[]{String.valueOf(person.getId())});
        database.close();
        return result;

    }

    public void deletePerson(Person person){
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(Util.TABLE_NAME,Util.KEY_ID+"=?",new String[]{String.valueOf(person.getId())});
        database.close();
    }

    public int getNumPerson(){
        String getAll="SELECT * FROM "+Util.TABLE_NAME;
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery(getAll,null);
        return cursor.getCount();
    }

























}
