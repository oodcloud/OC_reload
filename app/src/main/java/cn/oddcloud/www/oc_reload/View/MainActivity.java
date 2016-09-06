package cn.oddcloud.www.oc_reload.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import cn.oddcloud.www.oc_reload.R;
import cn.oddcloud.www.oc_reload.Utils.MsharedPreferences;
import cn.oddcloud.www.oc_reload.View.Activity.About_Us;
import cn.oddcloud.www.oc_reload.View.Activity.HorizontalCoordinatorNtbActivity;
import cn.oddcloud.www.oc_reload.View.Activity.Keeppage_Activity;
import cn.oddcloud.www.oc_reload.View.Activity.Login_with_Register;
import cn.oddcloud.www.oc_reload.View.Activity.Personal_Setting;
import cn.oddcloud.www.oc_reload.View.Activity.Seeing_Page_History;
import cn.oddcloud.www.oc_reload.View.Activity.SubjectArticleActivity;
import cn.oddcloud.www.oc_reload.View.Activity.User_detail_information;
import cn.oddcloud.www.oc_reload.View.fragment.RecommendArticlesFragment;
import cn.oddcloud.www.oc_reload.View.fragment.RecyclerViewFragment;
import cn.oddcloud.www.oc_reload.View.old_Fragment.Digital_fragment;
import cn.oddcloud.www.oc_reload.View.old_Fragment.IT_InformationFragment;
import cn.oddcloud.www.oc_reload.View.old_Fragment.PersonalCenterFragment;
import cn.oddcloud.www.oc_reload.View.old_Fragment.SiteArticles_fragment;
public class MainActivity extends DrawerActivity {
    private MaterialViewPager mViewPager;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private   TextView  drawer_phone;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 100:
                drawer_phone.setText("请登录");
                break;
            case 300:
                drawer_phone.setText(MsharedPreferences.Getphone(this));
                break;
            default:
                break;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setTitle("  奇云-专注IT");
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        toolbar = mViewPager.getToolbar();
//        toolbar.setNavigationIcon(R.id.);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        ImageView imageView= (ImageView) headerView.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=  MsharedPreferences.Getphone(MainActivity.this);
                if(phone.equals(""))
                {
                    Intent intent = new Intent(MainActivity.this, Login_with_Register.class);
                    startActivityForResult(intent,100);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, User_detail_information.class);
                    startActivityForResult(intent,200);
                }
            }
        });
          drawer_phone= (TextView) headerView.findViewById(R.id.drawer_phone);
        String phone=  MsharedPreferences.Getphone(this);
        if (!phone.equals(""))
        {

            drawer_phone.setText(phone);
        }
        else {

        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.theme) {
                    Intent intent=new Intent(MainActivity.this, SubjectArticleActivity.class);
                    startActivity(intent);
                } else if (id == R.id.site) {
                    Intent intent=new Intent(MainActivity.this, HorizontalCoordinatorNtbActivity.class);
                    startActivity(intent);
                } else if (id == R.id.liulan) {
                    Intent intent=new Intent(MainActivity.this, Seeing_Page_History.class);
                    startActivity(intent);
                } else if (id == R.id.collect) {
                    //收藏文章
                    Intent intent=new Intent(MainActivity.this, Keeppage_Activity.class);
                    startActivity(intent);
                } else if (id == R.id.setting) {
                    Intent intent=new Intent(MainActivity.this, Personal_Setting.class);
                    startActivity(intent);
                }else if (id == R.id.aboutus) {
                    Intent intent=new Intent(MainActivity.this, About_Us.class);
                    startActivity(intent);
                }


                return true;
            }
        });



//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 5) {
                    case 0:
                        return RecommendArticlesFragment.newInstance();
                    case 1:
                        return SiteArticles_fragment.newInstance();
                    case 2:
                        return PersonalCenterFragment.newInstance();
                    case 3:
                        return Digital_fragment.newInstance();
                    case 4:
                        return IT_InformationFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }
            @Override
            public int getCount() {
                return 5;
            }
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 5) {
                    case 0:
                        return "推荐文章";
                    case 1:
                        return "科技资讯";
                    case 2:
                        return "最新时事";
                    case 3:
                        return "数码产品";
                    case 4:
                        return "IT资讯";
                }
                return "";
            }
        });
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.green,
                            "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.blue,
                            "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.cyan,
                            "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                            R.color.red,
                            "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                }
                //execute others actions if needed (ex : modify your header logo)
                return null;
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }














}
