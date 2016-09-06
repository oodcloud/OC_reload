package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import cn.oddcloud.www.oc_reload.R;


public class About_Us extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);



        Toolbar toolbar= (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("个人设置");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
