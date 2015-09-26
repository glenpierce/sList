package piercestudio.com.listmaker;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;

import android.widget.LinearLayout;


public class MainActivity extends Activity
{


	LinearLayout listLayout;
	int buttonCount;
	float startingX, endingX;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listLayout = (LinearLayout) findViewById(R.id.listLayout);

		Button firstButton = new Button(this);
		firstButton = (Button) listLayout.findViewWithTag("1");
		firstButton.setOnClickListener(addButton);

		buttonCount = 1;


	}

	OnClickListener addButton = new OnClickListener()
	{

		public void onClick(View v)
		{
			buttonCount++;
			Button newButton = new Button(MainActivity.this);
			newButton.setTag(Integer.toString(buttonCount));
			newButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			listLayout.addView(newButton);

			FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
			UserEntryDialogFragment userEntryDialogFragment = new UserEntryDialogFragment();
			UserEntryDialogFragment.setButton(newButton);

			userEntryDialogFragment.show(fragmentTransaction, getString(R.string.userEntryDialogFragmentTag));

			newButton.setOnClickListener(addButton);
			newButton.setOnTouchListener(onTouchListener);
		}

	};

	View.OnTouchListener onTouchListener = new View.OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{

			int maskedAction = event.getActionMasked();

			switch (maskedAction)
			{

				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				{
					startingX = event.getX();
					break;
				}
				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				{
					if (startingX - event.getX() > 200){
						((ViewGroup)v.getParent()).removeView(v);
					}
				}

			}
			return false;
		}
	};
}