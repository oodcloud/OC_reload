package cn.oddcloud.www.oc_reload.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import cn.oddcloud.www.oc_reload.R;


public class Personal_Setting extends FragmentActivity {
  private Button content_back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal__setting);


        Toolbar toolbar= (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("个人中心");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        content_back_btn= (Button) findViewById(R.id.content_back);

        content_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal_Setting.this,FeedBack.class);
                startActivity(intent);
            }
        });
    }
}
