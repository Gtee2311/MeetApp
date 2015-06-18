package com.gracetee.meetapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.gracetee.meetapp.Custom.CustomActivity;
import com.gracetee.meetapp.R;
import com.gracetee.meetapp.Utils.Const;
import com.gracetee.meetapp.Utils.Utils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * The Class RegisterActivity is the Activity class that shows user registration screen
 * that allows user to register itself on Parse server for this ChatActivity app.
 */
public class RegisterActivity extends CustomActivity
{
    //use the default shared preference file
    private SharedPreferences preferenceSettings;
    private SharedPreferences.Editor preferenceEditor;

    //constant for operating mode
    private static final int PREFERENCE_MODE_PRIVATE = 0;

	/** The username EditText. */
	private EditText user;

	/** The password EditText. */
	private EditText pwd;

	/** The email EditText. */
	private EditText email;

	/* (non-Javadoc)
	 * @see com.gracetee.meetapp.Custom.CustomActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		setTouchNClick(R.id.btnReg);

		user = (EditText) findViewById(R.id.user);
		pwd = (EditText) findViewById(R.id.pwd);
		email = (EditText) findViewById(R.id.email);
	}

	/* (non-Javadoc)
	 * @see com.gracetee.meetapp.Custom.CustomActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		super.onClick(v);

		String u = user.getText().toString();
		String p = pwd.getText().toString();
		String e = email.getText().toString();
		if (u.length() == 0 || p.length() == 0 || e.length() == 0)
		{
			Utils.showDialog(this, R.string.err_fields_empty);
			return;
		}
		final ProgressDialog dia = ProgressDialog.show(this, null,
				getString(R.string.alert_wait));

		final ParseUser pu = new ParseUser();
		pu.setEmail(e);
		pu.setPassword(p);
		pu.setUsername(u);
		pu.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(ParseException e)
			{
				dia.dismiss();
				if (e == null)
				{
                    //saving user in the default preference file
                    preferenceSettings = getPreferences(PREFERENCE_MODE_PRIVATE);
                    preferenceEditor = preferenceSettings.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(pu);
                    preferenceEditor.putString("LoginUserObj", json);
                    preferenceEditor.commit();

                    Const.user = pu;
					startActivity(new Intent(RegisterActivity.this, ChatActivity.class));
					setResult(RESULT_OK);
					finish();
				}
				else
				{
					Utils.showDialog(
							RegisterActivity.this,
							getString(R.string.err_singup) + " "
									+ e.getMessage());
					e.printStackTrace();
				}
			}
		});

	}
}
