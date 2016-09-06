package cn.oddcloud.www.oc_reload.Crt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wangyujie on 2016/9/4.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Create_collect="create table Data_collect ("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"time text,"
            +"site text,"
            +"content text"
            +")";
    public static final String Create_history_LOOKING="create table Data_HISTORY ("
            +"id integer primary key autoincrement,"
            +"title text,"
            +"time text,"
            +"site text,"
            +"content text"
            +")";
    private  Context mcontext;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_collect);
        db.execSQL(Create_history_LOOKING);
        Log.d("database_table","create is finish");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
