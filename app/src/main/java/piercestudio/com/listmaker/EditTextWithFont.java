package piercestudio.com.listmaker;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


public class EditTextWithFont extends EditText
{
	public EditTextWithFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setTypeface(MainActivity.robotoThin);
	}

	public EditTextWithFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(MainActivity.robotoThin);
	}

	public EditTextWithFont(Context context) {
		super(context);
		this.setTypeface(MainActivity.robotoThin);
	}

}
