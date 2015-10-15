package piercestudio.com.listmaker;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewWithFont extends TextView
{
	public TextViewWithFont(Context context, AttributeSet attrs) {
			super(context, attrs);
			this.setTypeface(MainActivity.robotoThin);
	}

	public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setTypeface(MainActivity.robotoThin);
	}

	public TextViewWithFont(Context context) {
		super(context);
		this.setTypeface(MainActivity.robotoThin);
	}

}
