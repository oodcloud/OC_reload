package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Adapter.TopicArticleGridAdapter;

public class SubjectArticleActivity extends Activity {
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_themenew);
        Toolbar toolbar= (Toolbar) findViewById(R.id.feedback_toolbar);
        toolbar.setTitle("主题文章");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gridView=(GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new TopicArticleGridAdapter(this));
    }
}
