package piercestudio.com.listmaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.view.WindowManager;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by User on 10/13/15.
 */
public class AddItem extends Activity
{
	String TAG = "asdf";
	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item_layout);

		editText = (EditText) findViewById(R.id.editText);

		Button addButton = (Button) findViewById(R.id.addNewToDoButon);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

		Intent intent = getIntent();
		final ArrayList<String> array = intent.getStringArrayListExtra("key");

		addButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent intent = new Intent(AddItem.this, MainActivity.class);
				array.add(editText.getText().toString());
				intent.putStringArrayListExtra("key", array);
				startActivity(intent);

			}
		});
	}
}
