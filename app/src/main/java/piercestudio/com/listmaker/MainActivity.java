package piercestudio.com.listmaker;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;
import android.os.SystemClock;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends Activity
{

	LinearLayout listLayout;
	int buttonCount;
	float onDownX;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listLayout = (LinearLayout) findViewById(R.id.listLayout);

		// Hide the status bar.
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

		TextView createButton = new TextView(this);
		createButton = (TextView) listLayout.findViewWithTag("1");
		createButton.setOnClickListener(addButton);

		buttonCount = 1;
	}

	OnClickListener addButton = new OnClickListener()
	{

		public void onClick(View v)
		{
			buttonCount++;

			LinearLayout newLinearLayout;
			newLinearLayout = new LinearLayout(getApplicationContext());
			LayoutInflater inflater = MainActivity.this.getLayoutInflater();
			View layoutView = inflater.inflate(R.layout.new_linear_layout, null);
			newLinearLayout = (LinearLayout) layoutView.findViewById(R.id.newlinearlayout);

			TextView textView = (TextView) layoutView.findViewById(R.id.textview);

			newLinearLayout.setTag(Integer.toString(buttonCount));
			newLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			listLayout.addView(newLinearLayout);

			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			UserEntryDialogFragment userEntryDialogFragment = new UserEntryDialogFragment();
			userEntryDialogFragment.initialize(textView);

			userEntryDialogFragment.show(fragmentTransaction, getString(R.string.userEntryDialogFragmentTag));
			userEntryDialogFragment.setTextView(textView);

			newLinearLayout.setOnTouchListener(onTouchListener);
		}

	};

	View.OnTouchListener onTouchListener = new View.OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{

			Long downTime = 0L;
			int maskedAction = event.getActionMasked();

			switch (maskedAction)
			{

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				{
					Log.i("asdf", "up");
					Log.i("asdf", String.valueOf(SystemClock.currentThreadTimeMillis()));
					if (event.getX() - onDownX > 200){
						((ViewGroup)v.getParent()).removeView(v);
					}
					break;
				}
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				{
					Log.i("asdf", "down");
					onDownX = event.getX();
					downTime = System.currentTimeMillis();
					break;
				}
			}
			return true;
		}
	};
}