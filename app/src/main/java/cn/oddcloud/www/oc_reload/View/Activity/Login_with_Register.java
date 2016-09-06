package cn.oddcloud.www.oc_reload.View.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.View.Adapter.PageStateAdapter;
import cn.oddcloud.www.oc_reload.View.fragment.Login;
import cn.oddcloud.www.oc_reload.View.fragment.Register;


public class Login_with_Register extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private Toolbar toolbar;
    private Fragment register;
    private Fragment login;
    private TextView register_tv;
    private TextView login_tv;
    private ViewPager viewPager;
    private PageStateAdapter adapter;
    private List<Fragment> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_with__register);
        toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        toolbar.setNavigationIcon(R.drawable.nav);

        toolbar.setTitle("注册");
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTitle);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initView();
        intitFragment();
        initEvent();


    }

    private void initEvent() {

        register_tv.setOnClickListener(this);
        login_tv.setOnClickListener(this);

    }

    private void initView() {
        register_tv = (TextView) findViewById(R.id.register);
        login_tv = (TextView) findViewById(R.id.login);

    }

    private void intitFragment() {
        register = new Register();
        login = new Login();
        viewPager = (ViewPager) findViewById(R.id.viewpager_layout_login);
        //实例化list
        list = new ArrayList<>();
        //加载fragment到list
        list.add(register);
        list.add(login);
        adapter = new PageStateAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

        isSelect(0);
    }

    @Override
    public void onClick(View v) {
        hidebtnselect();
        switch (v.getId()) {
            case R.id.register:
                toolbar.setTitle("注册");
                viewPager.setCurrentItem(0);
                isSelect(0);
                break;
            case R.id.login:
                toolbar.setTitle("登录");
                viewPager.setCurrentItem(1);
                isSelect(1);
                break;
        }
    }

    private void isSelect(int position) {
        switch (position)
        {
            case 0:
                register_tv.setTextColor(Color.parseColor("#87000000"));
                register_tv.setBackgroundColor(Color.parseColor("#ee6F9E0F"));
                login_tv.setBackgroundColor(Color.parseColor("#FF6F9E0F"));
                break;
            case 1:
                login_tv.setTextColor(Color.parseColor("#87000000"));
                login_tv.setBackgroundColor(Color.parseColor("#ee6F9E0F"));
                register_tv.setBackgroundColor(Color.parseColor("#FF6F9E0F"));
                break;
        }

    }

    private void hidebtnselect() {
      register_tv.setTextColor(Color.parseColor("#ffffff"));
        login_tv.setTextColor(Color.parseColor("#ffffff"));
        register_tv.setBackgroundColor(Color.parseColor("#FF6F9E0F"));
        login_tv.setBackgroundColor(Color.parseColor("#FF6F9E0F"));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        hidebtnselect();
        isSelect(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
