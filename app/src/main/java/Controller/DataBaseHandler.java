package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Person;
import Model.Util;

public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE= "CREATE TABLE "+Util.TABLE_NAME+" ( "+Util.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+Util.KEY_NAME+" TEXT, "+Util.KEY_LNAME+" TEXT, "+Util.KEY_START_DATE+" TEXT, "
                +Util.KEY_END_DATE+" TEXT, "+Util.KEY_DESCRIPTION+" TEXT )";
        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    public void addPerson(Person person){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.KEY_NAME,person.getName());
        contentValues.put(Util.KEY_LNAME,person.getLastName());
        contentValues.put(Util.KEY_START_DATE,person.getStartDate());
        contentValues.put(Util.KEY_END_DATE,person.getEndDate());
        contentValues.put(Util.KEY_DESCRIPTION,person.getDescription());
        database.insert(Util.TABLE_NAME,null,contentValues);
        database.close();

    }

    public Person getPerson(int id){
        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_LNAME,Util.KEY_START_DATE,Util.KEY_END_DATE,
                        Util.KEY_DESCRIPTION}
                ,Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
            Person person=new Person(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                    cursor.getString(5));
            return person;

    }

    public List<Person>getAll(){
        SQLiteDatabase database=this.getReadableDatabase();
        List<Person> personList=new ArrayList<>();
        String getAll="SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor=database.rawQuery(getAll,null);
        if(cursor.moveToFirst())
            do{
                Person person=new Person();
                person.setId(cursor.getInt(0));
                person.setName(cursor.getString(1));
                person.setLastName(cursor.getString(2));
                person.setStartDate(cursor.getString(3));
                person.setEndDate(cursor.getString(4));
                person.setDescription(cursor.getString(5));
                personList.add(person);
            }while (cursor.moveToNext());
        return personList;
    }

    public int updatePerson(Person person){
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Util.KEY_NAME,person.getName());
        contentValues.put(Util.KEY_LNAME,person.getLastName());
        contentValues.put(Util.KEY_START_DATE,person.getStartDate());
        contentValues.put(Util.KEY_END_DATE,person.getEndDate());
        contentValues.put(Util.KEY_DESCRIPTION,person.getDescription());
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
