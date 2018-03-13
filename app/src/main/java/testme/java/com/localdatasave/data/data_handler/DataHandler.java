package testme.java.com.localdatasave.data.data_handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import testme.java.com.localdatasave.data.DataBaseConstants;
import testme.java.com.localdatasave.data.student.model.Student;

/**
 * Created by achau on 07-03-2018.
 */

public class DataHandler extends SQLiteOpenHelper {

    private static final String CREATE_STUDENT_TABLE = "CREATE_TABLE " + DataBaseConstants.STUDENT_TABLE + "("
            + DataBaseConstants.ROLL_NO + "INTEGER PRIMARY KEY"
            + DataBaseConstants.NAME + "TEXT"
            + DataBaseConstants.CURRENT_CLASS + "INTEGER"
            + DataBaseConstants.SEX + "INTEGER";

    public DataHandler(Context context) {
        super(context, DataBaseConstants.DATABASE_NAME, null, DataBaseConstants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseConstants.STUDENT_TABLE);

        //create new tables
        onCreate(db);
    }


    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Inserting values in the row

        ContentValues values = new ContentValues();
        values.put(DataBaseConstants.ROLL_NO, student.getRoll_no());
        values.put(DataBaseConstants.NAME, student.getName());
        values.put(DataBaseConstants.SEX, student.getRoll_no());
        values.put(DataBaseConstants.CURRENT_CLASS, student.getCurrent_class());

        //Inserting row into the database

        db.insert(DataBaseConstants.STUDENT_TABLE, null, values);
        db.close();

    }

    public Student getContact(int rollNo) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(DataBaseConstants.STUDENT_TABLE, new String[]{DataBaseConstants.ROLL_NO, DataBaseConstants.NAME,
                DataBaseConstants.CURRENT_CLASS, DataBaseConstants.SEX}, DataBaseConstants.ROLL_NO + "=?=", new String[]{String.valueOf(rollNo)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(cursor.getString(0),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)), Boolean.parseBoolean(cursor.getString(3)));

        return student;

    }

    public List<Student> getAllContacts() {
        List<Student> studentList = new ArrayList<>();

        String query = "SELECT * FROM " + DataBaseConstants.STUDENT_TABLE;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setRoll_no(Integer.parseInt(cursor.getString(0)));
                student.setCurrent_class(Integer.parseInt(cursor.getString(1)));
                student.setName(cursor.getString(2));
                student.setSex(Boolean.getBoolean(cursor.getString(3)));

                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;

    }

    public void updateContact(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataBaseConstants.ROLL_NO, student.getRoll_no());
        values.put(DataBaseConstants.NAME, student.getName());
        values.put(DataBaseConstants.SEX, student.getRoll_no());
        values.put(DataBaseConstants.CURRENT_CLASS, student.getCurrent_class());

        sqLiteDatabase.update(DataBaseConstants.STUDENT_TABLE, values, DataBaseConstants.ROLL_NO + " = ?",
                new String[]{String.valueOf(student.getRoll_no())});
    }

    public void deleteContact(Student student) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DataBaseConstants.STUDENT_TABLE, DataBaseConstants.ROLL_NO + " = ?",
                new String[]{String.valueOf(student.getRoll_no())});
    }

    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + DataBaseConstants.STUDENT_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();

    }


}
