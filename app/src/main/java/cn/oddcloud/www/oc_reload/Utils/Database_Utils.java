package cn.oddcloud.www.oc_reload.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.oddcloud.www.oc_reload.Crt.DatabaseHelper;

/**
 * Created by wangyujie on 2016/9/4.
 */
public class Database_Utils {
    private  DatabaseHelper databaseHelper;
    private  SQLiteDatabase sqlitedatabase;
    private Context context;
    public void opendb(Context context)
    {
        this.context=context;
        databaseHelper = new DatabaseHelper(context,"oddcloud.db",null,1);
        sqlitedatabase = databaseHelper.getWritableDatabase();
    }
    //关闭数据库连接
    public void closedb(Context context)
    {
        if(sqlitedatabase.isOpen())
        {
            sqlitedatabase.close();
        }
    }
    //插入表数据
    public void insert (String table_name,ContentValues values)
    {
        opendb(context);
        sqlitedatabase.insert(table_name, null, values);
        closedb(context);
    }
    //更新数据
    public int updatatable(String table_name,ContentValues values,int ID)
    {
        opendb(context);
        return sqlitedatabase.update(table_name, values, " Type_ID = ? ", new String[]{String.valueOf(ID)});
    }
    //删除表数据
    public void delete(String table_name)
    {
        opendb(context);
        try{

            sqlitedatabase.delete(table_name, null, null);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            closedb(context);
        }
    }

    public JSONArray DeptArray(int i)
    {
        JSONArray Items = new JSONArray();
        try{
            opendb(context);
            String sql ="";


            switch (i)
            {
                case 0:
                    sql = "SELECT * FROM Data_collect"; //保存文章
                    break;
                case 1:
                    sql="SELECT * FROM Data_HISTORY";  //浏览记录
            }
            Cursor c = sqlitedatabase.rawQuery(sql, null);
            if(c!=null)
            {
                while(c.moveToNext())
                {
                    JSONObject item = new JSONObject();
                    item.put("title", c.getString(c.getColumnIndex("title")));
                    item.put("time", c.getString(c.getColumnIndex("time")));
                    item.put("site", c.getString(c.getColumnIndex("site")));
                    item.put("content", c.getString(c.getColumnIndex("content")));
                    Items.put(item);
                }
                c.close();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            closedb(context);
        }
        return Items;
    }
}
