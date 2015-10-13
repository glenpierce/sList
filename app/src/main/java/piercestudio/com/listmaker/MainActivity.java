package piercestudio.com.listmaker;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class MainActivity extends Activity
{
	String TAG = "buttonTestTag";

	int buttonCount = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Hide the status bar.
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LinearLayout linearLayoutInScrollView = (LinearLayout) findViewById(R.id.linearLayoutInScrollView);

		EditText editText = (EditText) findViewById(R.id.editText);
		editText.setText("asdf");

		Button addNewToDo = (Button) findViewById(R.id.addNewToDoButon);
		OnClickListener toDoOnClickListner = createNewToDoOnClickListner();
		addNewToDo.setOnClickListener(toDoOnClickListner);

	}

	public void createNewToDo()
	{
		buttonCount++;

		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
		View layoutViewToBeAdded = inflater.inflate(R.layout.layout_to_be_added_with_new_textview, null);

		LinearLayout linearLayoutToBeAddedFromEditText;
		linearLayoutToBeAddedFromEditText = new LinearLayout(getApplicationContext());
		linearLayoutToBeAddedFromEditText.setTag(Integer.toString(buttonCount));
		linearLayoutToBeAddedFromEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		TextView newToDo;
		newToDo = new TextView(getApplicationContext());
		newToDo = (TextView) layoutViewToBeAdded.findViewById(R.id.newToDoTextView);
		newToDo.setTag(Integer.toString(buttonCount));

		View layoutViewMain = inflater.inflate(R.layout.activity_main, null);

		EditText editText = (EditText) layoutViewMain.findViewById(R.id.editText);
		newToDo.setText(editText.getText().toString());
		editText.setText("");

		LinearLayout linearLayoutInScrollView = (LinearLayout) layoutViewMain.findViewById(R.id.linearLayoutInScrollView);

		linearLayoutInScrollView.addView(layoutViewToBeAdded);
	}



	public OnClickListener createNewToDoOnClickListner(){
		OnClickListener newOnClickListner = new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				createNewToDo();
			}
		};
		return newOnClickListner;
	}
}