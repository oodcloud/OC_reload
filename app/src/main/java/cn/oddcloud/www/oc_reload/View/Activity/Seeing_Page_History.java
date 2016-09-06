package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.oddcloud.www.oc_reload.Crt.DatabaseHelper;
import cn.oddcloud.www.oc_reload.Model.Liulan_Page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.Database_Utils;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import cn.oddcloud.www.oc_reload.View.Adapter.MArrayAdapter;


public class Seeing_Page_History extends FragmentActivity implements Toolbar.OnMenuItemClickListener{
    private ListView listView;
    //    private MArrayAdapter adapter;
    private List<HashMap<String, String>> list;

    /**
     * 定义数据库
     *
     * @param savedInstanceState
     */
    private SQLiteDatabase db;
    private DatabaseHelper sqLiteOpenHelper;
    private Database_Utils database_utils;
    private Cursor cursor;
    public static final String TAG = "DaoExample";
    public MArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeing__page__history);
        Toolbar toolbar= (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("浏览历史");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList();
        listView = (ListView) findViewById(R.id.list_keep);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        database_utils = new Database_Utils();
        database_utils.opendb(Seeing_Page_History.this);
        JSONArray jsonArray= database_utils.DeptArray(1);
        Log.d("jsonArray"," "+jsonArray.length());
        if (jsonArray.length()!=0)
        {
            HashMap<String, String> map;
            for (int i=0;i<jsonArray.length();i++)
            {
                map = new HashMap<>();
                try {

                    JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                    map.put("title", jsonObject.getString("title"));
                    map.put("time", jsonObject.getString("time"));
                    map.put("site", jsonObject.getString("site"));
                    list.add(map);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            database_utils.closedb(this);
            adapter = new MArrayAdapter(this, R.layout.list_item_card_small, list,1);
            listView.setAdapter(adapter);
        }
        else {
            Log.d("Check"," is do");
            final ProgressDialog dialog = ProgressDialog.show(this, "加载中...", "正在加载，请稍后！");
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(true);
            BmobQuery<Liulan_Page> query = new BmobQuery<Liulan_Page>();
            query.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
            query.order("-updatedAt");
            query.findObjects(new FindListener<Liulan_Page>() {
                @Override
                public void done(List<Liulan_Page> object, BmobException e) {
                    if(e==null){
                        Log.d("Check"," is do1p"+object.size());
                        HashMap<String, String> map;
                        for (int i=0;i<object.size();i++)
                        {
                            dialog.dismiss();
                            map = new HashMap<>();
                            map.put("title", object.get(i).getTitle());
                            map.put("time", object.get(i).getTime());
                            map.put("site",object.get(i).getSite());
                            list.add(map);
                        }

                        adapter = new MArrayAdapter(Seeing_Page_History.this, R.layout.list_item_card_small, list,1);
                        listView.setAdapter(adapter);

                        Log.d("Check"," is do"+new Gson().toJson(list));
                    }else{
                        dialog.dismiss();
                        Toast.makeText(Seeing_Page_History.this,"您还没有浏览文章文章",Toast.LENGTH_SHORT).show();
                    }
                }

            });

        }


    }







    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        list.clear();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
