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

	private LinearLayout linearLayoutInsideScrollView;
	private EditText editText;
	int buttonCount = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Hide the status bar.
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
		View layoutView = inflater.inflate(R.layout.activity_main, null);

		linearLayoutInsideScrollView = new LinearLayout(getApplication());
		linearLayoutInsideScrollView = (LinearLayout) layoutView.findViewById(R.id.layoutInsideScrollView);

		editText = new EditText(this);
		editText = (EditText) layoutView.findViewById(R.id.editText);
		editText.setText("asdf");

		Button addNewToDo = (Button) findViewById(R.id.addNewToDoButon);
		OnClickListener toDoOnClickListner = createNewToDoOnClickListner();
		addNewToDo.setOnClickListener(toDoOnClickListner);

	}

//	public void createKeyListners(){
//		View.OnKeyListener addToDo = new View.OnKeyListener()
//		{
//			@Override
//			public boolean onKey(View v, int keyCode, KeyEvent event)
//			{
//				if(event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//					createNewToDo();
//				}
//				return false;
//			}
//		};
//	}


	public void createNewToDo()
	{
		editText.setText("");
		Log.i(TAG, "Click");
		buttonCount++;
		Log.i(TAG, "Click! " + Integer.toString(buttonCount) + editText.getText().toString());

		LayoutInflater inflater = MainActivity.this.getLayoutInflater();
		View layoutView = inflater.inflate(R.layout.layout_to_be_added_with_new_textview, null);

		LinearLayout linearLayoutToBeAddedFromEditText;
		linearLayoutToBeAddedFromEditText = new LinearLayout(getApplicationContext());
		linearLayoutToBeAddedFromEditText.setTag(Integer.toString(buttonCount));
		linearLayoutToBeAddedFromEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		TextView newToDo;
		newToDo = new TextView(getApplicationContext());
		newToDo = (TextView) layoutView.findViewById(R.id.newToDoTextView);
		newToDo.setTag(Integer.toString(buttonCount));

		newToDo.setText(editText.getText().toString());
		editText.setText("");

		linearLayoutInsideScrollView.addView(layoutView);
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