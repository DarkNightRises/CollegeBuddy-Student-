package majorproject.kone.in.collegebudy.activity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.adapter.CustomPagerAdapter;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private ViewPager dashboard_pager;

    //Variable declaration
    private CustomPagerAdapter customPagerAdapter;
    private PagerSlidingTabStrip tabStrip;      //Strip that shows title for pager of dashboard
    private Intent intent;      //intent for drawer click activity switch



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialiseViewPager();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        intent = null;
        // Handle navigation view item clicks in drawer.
        int id = item.getItemId();
        if( id == R.id.nav_home){

        }
        if (id == R.id.nav_attendance) {

            intent = new Intent(NavigationActivity.this,CheckAttendance.class);
            startActivity(intent);
        } else if (id == R.id.nav_query) {
            intent = new Intent(NavigationActivity.this,Query.class);
            startActivity(intent);
        } else if (id == R.id.nav_notificaton) {
            intent = new Intent(NavigationActivity.this,NotificationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_write_review) {
            intent = new Intent(NavigationActivity.this,WriteReview.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put");
            startActivity(Intent.createChooser(i,"Share via"));
        } else if (id == R.id.nav_feedback) {
            intent = new Intent(NavigationActivity.this,Feedback.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //View Pager initialisation with its tab strip
    public void initialiseViewPager(){
        dashboard_pager = (ViewPager) findViewById(R.id.pager_dashboard);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),NavigationActivity.this);
        dashboard_pager.setAdapter(customPagerAdapter);
        tabStrip.setViewPager(dashboard_pager);
    }
}
