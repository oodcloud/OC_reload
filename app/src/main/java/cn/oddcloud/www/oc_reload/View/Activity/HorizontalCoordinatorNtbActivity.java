package cn.oddcloud.www.oc_reload.View.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toolbar;

import com.gigamole.navigationtabbar.ntb.NavigationTabBar;

import java.util.ArrayList;

import cn.oddcloud.www.oc_reload.Model.SQLLoad;
import cn.oddcloud.www.oc_reload.R;


public class HorizontalCoordinatorNtbActivity extends Activity {

    String site = null;
    String sitelinks=null;
    //站点名称
    ArrayList<String> list=new ArrayList();
    // //站点链接
    ArrayList<String> sitelink=new ArrayList();
  Handler handler;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_horizontal_coordinator_ntb);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_ntb);
        if (toolbar!=null)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                setActionBar(toolbar);
            }
        }
        ActionBar actionBar=getActionBar();
        if (actionBar!=null)
        {
          actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        initUI();
    }

    private void initUI() {
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        viewPager.setOffscreenPageLimit(1);
        final PagerAdapter pagerAdapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return 9;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = LayoutInflater.from(
                        getBaseContext()).inflate(R.layout.item_vp_list, null, false);

                final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(
                                getBaseContext(), LinearLayoutManager.VERTICAL, false
                        )
                );

                RecycleAdapter recycleAdapter=new RecycleAdapter(position);
                recycleAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recycleAdapter);


                container.addView(view);

                return view;
            }
        };


        viewPager.setAdapter(pagerAdapter);

        final String[] colors = getResources().getStringArray(R.array.default_preview);

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);

        navigationTabBar.setBgColor(getResources().getColor(android.R.color.holo_green_light));


        navigationTabBar.setIsSwiped(true);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site1),
                        Color.parseColor(colors[0]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site2),
                        Color.parseColor(colors[1]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site3),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site4),
                        Color.parseColor(colors[3]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site5),
                        Color.parseColor(colors[4]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site6),
                        Color.parseColor(colors[3]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site7),
                        Color.parseColor(colors[2]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site8),
                        Color.parseColor(colors[1]))
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.site9),
                        Color.parseColor(colors[4]))
                        .build()
        );

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager,0);

        //IMPORTANT: ENABLE SCROLL BEHAVIOUR IN COORDINATOR LAYOUT
        navigationTabBar.setBehaviorEnabled(true);

        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(final NavigationTabBar.Model model, final int index) {

            }

            @Override
            public void onEndTabSelected(final NavigationTabBar.Model model, final int index) {
//                model.hideBadge();
            }
        });
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                navigationTabBar.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        final CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.parent);
        findViewById(R.id.fab).setVisibility(View.GONE);
// findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                for (int i = 0; i < navigationTabBar.getModels().size(); i++) {
//                    final NavigationTabBar.Model model = navigationTabBar.getModels().get(i);
//                    navigationTabBar.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            final String title = String.valueOf(new Random().nextInt(15));
//                            if (!model.isBadgeShowed()) {
//                                model.setBadgeTitle(title);
//                                model.showBadge();
//                            } else model.updateBadgeTitle(title);
//                        }
//                    }, i * 100);
//                }
//
//                coordinatorLayout.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        final Snackbar snackbar = Snackbar.make(navigationTabBar, "Coordinator NTB", Snackbar.LENGTH_SHORT);
//                        snackbar.getView().setBackgroundColor(Color.parseColor("#9b92b3"));
//                        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text))
//                                .setTextColor(Color.parseColor("#423752"));
//                        snackbar.show();
//                    }
//                }, 1000);
//            }
//        });

    }

    public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
        ArrayList<String> list=new ArrayList<>();
        // //站点链接
        ArrayList<String> sitelink=new ArrayList<>();
        public RecycleAdapter(int position) {

            SQLLoad sqlLoad = new SQLLoad();
            final SQLiteDatabase db = sqlLoad.openDatabase(HorizontalCoordinatorNtbActivity.this);

            // 创建observable

            list.clear();
            sitelink.clear();

            Cursor cursor = db.rawQuery("select * from feed where cid=?", new String[]{""+(position+1)});
//取出数据库中的URL到数据中然后写一个点击事件，没点击一次intent跳转到rss页面中
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                site = cursor.getString(cursor.getColumnIndex("fname"));
                sitelinks = cursor.getString(cursor.getColumnIndex("url"));
                list.add(site);
                sitelink.add(sitelinks);
            }
            //这是一个TextView，把得到的数据库中的name显示出来.
            Log.d("SQL", list.toString());
            Log.d("SQL", sitelink.toString());
            cursor.close();
            db.close();

        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.txt.setText(""+list.get(position));
            Log.d("txt_loader","i"+holder.txt.getText());
            final String link=sitelink.get(position);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(HorizontalCoordinatorNtbActivity.this,Site_Website_Page_list.class);
                    intent.putExtra("link",link);
                    intent.putExtra("title",holder.txt.getText());
                   startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView txt;
            public View view;
            public ViewHolder(final View itemView) {
                super(itemView);
                view=itemView;
                txt = (TextView) itemView.findViewById(R.id.txt_vp_item_list);
            }
        }
    }
}
