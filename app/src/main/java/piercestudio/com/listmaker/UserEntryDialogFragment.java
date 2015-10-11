package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;

public class UserEntryDialogFragment extends DialogFragment {

	TextView mTextView;

	public void initialize(TextView textView){
		this.mTextView = textView;
	}

	public void setTextView(TextView textView){
		textView = mTextView;
	}
	
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.entry_dialog, null);

		final EditText editText = (EditText) dialogView.findViewById(R.id.entryDialogEditText);
		editText.requestFocus();

		builder.setView(dialogView);


        builder.setMessage(R.string.new_header);

//                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
//				{
//					public void onClick(DialogInterface dialog, int id)
//					{
//						mTextView.setText(editText.getText().toString());
//						dismiss();
//					}
//				})
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
//				{
//					public void onClick(DialogInterface dialog, int id)
//					{
//						dismiss();
//					}
//				});

//		builder.setMessage(null)
//		.setPositiveButton(null, null)
//		.setNegativeButton(null, null);

		Dialog dialog = builder.create();
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

		editText.addTextChangedListener(new TextWatcher()
		{

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3)
			{
				if (cs.toString().contains("\n")) {
					mTextView.setText(cs.toString().substring(0, cs.toString().length()-1));
					dismiss();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
			{
			}

			@Override
			public void afterTextChanged(Editable arg0) { }

		});

        return dialog;
    }
}