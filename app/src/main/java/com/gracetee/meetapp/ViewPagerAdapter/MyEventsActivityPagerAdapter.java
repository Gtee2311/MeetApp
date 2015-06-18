package com.gracetee.meetapp.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gracetee.meetapp.Fragment.AgendaFragment;
import com.gracetee.meetapp.Fragment.FloorMapFragment;
import com.gracetee.meetapp.Fragment.InfoFragment;
import com.gracetee.meetapp.Fragment.NetworkFragment;
import com.gracetee.meetapp.Fragment.NoticeFragment;

/**
 * Created by Grace on 13/6/2015.
 */

public class MyEventsActivityPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public MyEventsActivityPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Fragment item = new Fragment();

        switch(position){
            case 0:
                item = new InfoFragment();
                break;
            case 1:
                item = new NetworkFragment();
                break;
            case 2:
                item = new AgendaFragment();
                break;
            case 3:
                item = new FloorMapFragment();
                break;
            case 4:
                item = new NoticeFragment();
                break;
        }
        return item;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}