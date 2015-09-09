package piercestudio.com.listmaker;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {


    LinearLayout listLayout;
    int buttonCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listLayout = (LinearLayout)findViewById(R.id.listLayout);

        Button firstButton = new Button(this);
        firstButton = (Button) listLayout.findViewWithTag("1");
        firstButton.setOnClickListener(addButton);

        buttonCount = 1;


    }

    OnClickListener addButton = new OnClickListener(){

        public void onClick(View v){
            buttonCount++;
            Button newButton = new Button(MainActivity.this);
            newButton.setTag(Integer.toString(buttonCount));
            newButton.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            listLayout.addView(newButton);
            newButton.setOnClickListener(addButton);

            EditText buttonText = new EditText(MainActivity.this);
            newButton.addView(buttonText);


        }

    };

}
