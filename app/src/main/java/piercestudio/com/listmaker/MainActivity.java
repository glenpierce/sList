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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewParent;


public class MainActivity extends Activity
{

	LinearLayout listLayout;
	int buttonCount;
	float onDownX;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Hide the status bar.
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listLayout = (LinearLayout) findViewById(R.id.listLayout);

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
			textView.setTag(Integer.toString(buttonCount));

			newLinearLayout.setTag(Integer.toString(buttonCount));
			Log.i("asdf", newLinearLayout.getTag().toString());
			newLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			setTextViewText(textView);

			listLayout.addView(newLinearLayout);


//			newLinearLayout.setOnTouchListener(onTouchListener);
			textView.setOnTouchListener(onTouchListenerHeadings);
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
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				{
					Log.i("asdf", "down");
					onDownX = event.getX();
					downTime = System.currentTimeMillis();
					break;
				}

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				{
					Log.i("asdf", "up");
					if (event.getX() - onDownX > 200){
						((ViewGroup)v.getParent()).removeView(v);
						break;
					}
					if(System.currentTimeMillis() - downTime > 200){
//						addNewItem((LinearLayout) v);
						addNewItemFromView(v);
					}

					break;
				}
			}
			return true;
		}
	};

	View.OnTouchListener onTouchListenerHeadings = new View.OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{

			Long downTime = 0L;
			int maskedAction = event.getActionMasked();

			switch (maskedAction)
			{
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_POINTER_DOWN:
				{
					Log.i("asdf", "down");
					onDownX = event.getX();
					downTime = System.currentTimeMillis();
					break;
				}

				case MotionEvent.ACTION_UP:
				case MotionEvent.ACTION_POINTER_UP:
				{
					Log.i("asdf", "up");
					if (event.getX() - onDownX > 200){
						Log.i("asdf", "deleting header" + v.getTag());
						Log.i("asdf", ((ViewGroup) v.getParent()).findViewWithTag(v.getTag()).getTag().toString());
						((ViewGroup)v.getParent().getParent()).removeView((ViewGroup)v.getParent());
						break;
					}
					if(System.currentTimeMillis() - downTime > 200)
					{
//						addNewItem((LinearLayout) v);
						addNewItemFromView(v);
					}

					break;
				}
			}
			return true;
		}
	};

//	public void addNewItem(LinearLayout v){
//		TextView newTextView;
//		newTextView = new TextView(getApplicationContext());
//		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
//		View layoutView = inflater.inflate(R.layout.new_list_item, null);
//		TextView textView = (TextView) layoutView.findViewById(R.id.new_item);
//
//		setTextViewText(textView);
//
//		v.addView(layoutView);
//		textView.setOnTouchListener(onTouchListener);
//	}

	public void addNewItemFromView(View v){
		TextView newTextView;
		newTextView = new TextView(getApplicationContext());
		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
		View layoutView = inflater.inflate(R.layout.new_item_layout, null);
		TextView textView = (TextView) layoutView.findViewById(R.id.new_item_textview);
		LinearLayout newItem = (LinearLayout) layoutView.findViewById(R.id.new_item);

		setTextViewText(textView);

		((ViewGroup)v.getParent()).addView(layoutView);

		layoutView.setOnTouchListener(onTouchListener);
	}

	public void setTextViewText(TextView textView){
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		UserEntryDialogFragment userEntryDialogFragment = new UserEntryDialogFragment();
		userEntryDialogFragment.initialize(textView);

		userEntryDialogFragment.show(fragmentTransaction, getString(R.string.userEntryDialogFragmentTag));
		userEntryDialogFragment.setTextView(textView);
	}
}