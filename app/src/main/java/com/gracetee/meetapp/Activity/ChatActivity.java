package com.gracetee.meetapp.Activity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v4.view.ViewPager;

import com.gracetee.meetapp.Fragment.ContactFragment;
import com.gracetee.meetapp.NavigationDrawer.NavigationDrawerCallbacks;
import com.gracetee.meetapp.NavigationDrawer.NavigationDrawerFragment;
import com.gracetee.meetapp.R;
import com.gracetee.meetapp.ViewPagerAdapter.ChatPagerAdapter;
import com.gracetee.meetapp.Utils.SlidingTabLayout;
import com.parse.ParseUser;

public class ChatActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks,ContactFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private ViewPager mPager;
    private ChatPagerAdapter mAdapter;
    private SlidingTabLayout mTabs;
    private CharSequence Titles[]={"Contact","Chat"};
    private int Numboftabs =2;

    /** The user. */
    public static ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Creating The Toolbar and setting it as the Toolbar for the activity
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        mAdapter =  new ChatPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        // Assigning the Sliding Tab Layout View
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        mTabs.setViewPager(mPager);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("John Doe", "johndoe@doe.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));

        updateUserStatus(true);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        updateUserStatus(false);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    /**
     * Update user status.
     *
     * @param online
     *            true if user is online
     */
    private void updateUserStatus(boolean online)
    {
        user.put("online", online);
        user.saveEventually();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    public void onFragmentInteraction(Uri uri){};

}
