package cn.oddcloud.www.oc_reload.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import cn.oddcloud.www.oc_reload.R;


/**
 * Created by florentchampigny on 27/05/2016.
 */
public class DrawerActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
   private TextView drawer_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
//    public void header_onclick(View view){
//        String phone=  MsharedPreferences.Getphone(this);
//        if(phone.equals(""))
//        {
//            Intent intent = new Intent(DrawerActivity.this, Login_with_Register.class);
//            startActivity(intent);
//        }
//        else {
//            Intent intent = new Intent(DrawerActivity.this, User_detail_information.class);
//            startActivity(intent);
//        }
//    }


    @Override
    protected void onStart() {
        super.onStart();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
//        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawer.addDrawerListener(mDrawerToggle);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) ||
            super.onOptionsItemSelected(item);
    }


}
