package com.gracetee.meetapp.Custom;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import com.gracetee.meetapp.R;
import com.gracetee.meetapp.Utils.TouchEffect;

/**
 * This is a common activity that all other activities of the app can extend to
 * inherit the common behaviors like implementing a common interface that can be
 * used in all child activities.
 */
public class CustomActivity extends FragmentActivity implements OnClickListener
{

	/**
	 * Apply this Constant as touch listener for views to provide alpha touch
	 * effect. The view must have a Non-Transparent background.
	 */
	public static final TouchEffect TOUCH = new TouchEffect();

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void setContentView(int layoutResID)
	{
		super.setContentView(layoutResID);
		//setupActionBar();
	}

	/**
	 * This method will setup the top title bar (Action bar) content and display
	 * values. It will also setup the Custom background theme for ActionBar. You
	 * can override this method to change the behavior of ActionBar for
	 * particular Activity
	 */
	/*protected void setupActionBar()
	{
        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        mToolbar.setDisplayShowTitleEnabled(true);
        mToolbar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        mToolbar.setDisplayUseLogoEnabled(true);
        mToolbar.setLogo(R.drawable.icon);
        mToolbar.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.actionbar_bg));
        mToolbar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setHomeButtonEnabled(true);
	}*/

	/**
	 * Sets the touch and click listener for a view with given id.
	 * 
	 * @param id
	 *            the id
	 * @return the view on which listeners applied
	 */
	public View setTouchNClick(int id)
	{

		View v = setClick(id);
		if (v != null)
			v.setOnTouchListener(TOUCH);
		return v;
	}

	/**
	 * Sets the click listener for a view with given id.
	 * 
	 * @param id
	 *            the id
	 * @return the view on which listener is applied
	 */
	public View setClick(int id)
	{

		View v = findViewById(id);
		if (v != null)
			v.setOnClickListener(this);
		return v;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{

	}
}
