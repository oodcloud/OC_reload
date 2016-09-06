package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.oddcloud.www.oc_reload.Model.Collect_Page;
import cn.oddcloud.www.oc_reload.Model.Liulan_Page;
import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;

public class User_detail_information extends Activity {
    private TextView mphone;
    private TextView liulanpagenub;
    private TextView user_qq;
    private TextView collect_page;
    private Button btn_loginout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_information);
        Toolbar toolbar= (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("个人中心");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        InitView();
        InitEvent();

    }

    private void InitEvent() {
        mphone.setText(MsharedPreferences.Getphone(this));
        BmobQuery<Collect_Page> query = new BmobQuery<Collect_Page>();
        query.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
        query.order("-updatedAt");
        query.findObjects(new FindListener<Collect_Page>() {
            @Override
            public void done(List<Collect_Page> object, BmobException e) {
                if(e==null){
                    collect_page.setText(""+object.size());
                }else{
                    collect_page.setText("无");
                }
            }

        });

        BmobQuery<Liulan_Page> querys = new BmobQuery<Liulan_Page>();
        querys.addWhereEqualTo("phone", MsharedPreferences.Getphone(this));
        querys.order("-updatedAt");
        querys.findObjects(new FindListener<Liulan_Page>() {
            @Override
            public void done(List<Liulan_Page> object, BmobException e) {
                if(e==null){
                    liulanpagenub.setText(""+object.size());
                }else{
                    liulanpagenub.setText("无");
                }
            }

        });

        btn_loginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsharedPreferences.Setphone(User_detail_information.this,"");
               User_detail_information.this.setResult(100);
                User_detail_information.this.finish();
            }
        });



    }

    private void InitView() {
        mphone= (TextView) findViewById(R.id.phone_user);
        liulanpagenub= (TextView) findViewById(R.id.liulanpage);
        user_qq= (TextView) findViewById(R.id.user_qq);
        collect_page= (TextView) findViewById(R.id.collect_page);
        btn_loginout= (Button) findViewById(R.id.btn_loginout);

    }
}
