package piercestudio.com.listmaker;

import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class MainActivity extends Activity
{

	EditText editText;
	ArrayList arrayList;
	ArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editText);
		Button button = (Button) findViewById(R.id.addNewToDoButon);
		ListView listView = (ListView) findViewById(R.id.listView);
		arrayList = new ArrayList<String>();

		adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);

		// Here, you set the data in your ListView
		listView.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{

//				arrayList.add(editText.getText().toString());
				adapter.add(editText.getText().toString());
				editText.setText("");
			}
		});
	}
}