package com.lirong.coordinatorlayout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigation;
    private MainActivity context;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setToolbar();
        setDrawerLayout();
        initNavigationView();
        initCollapsingToolbarLayout();
    }

    private void setToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);
    }

    private void setDrawerLayout(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 實作 drawer toggle 並放入 toolbar
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setScrimColor(Color.parseColor("#3c000000"));
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void initNavigationView() {
        navigation = (NavigationView) findViewById(R.id.navigation);
        View headerView = navigation.inflateHeaderView(R.layout.nav_header);
        ImageView hearder = (ImageView) headerView.findViewById(R.id.header_img);
        BitmapDrawable drawable = (BitmapDrawable) hearder.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        hearder.setImageBitmap(toRoundCorner(bitmap, bitmap.getWidth() / 2));
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navItem1:
                        Toast.makeText(context, "clic home button", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navItem2:
                        break;
                    case R.id.navItem3:
                        break;
                }
                return false;
            }
        });
    }

    private void initCollapsingToolbarLayout(){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Material Design");
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, float pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);

        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
