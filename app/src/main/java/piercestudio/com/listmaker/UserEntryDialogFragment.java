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
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.TextView;
import android.content.Context;
import android.content.ContextWrapper;


public class UserEntryDialogFragment extends DialogFragment {

	public void initialize(TextView textView){

		this.mTextView = textView;

	}

	TextView mTextView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.entry_dialog, null);

		final EditText editText = (EditText) dialogView.findViewById(R.id.entryDialogEditText);
		editText.requestFocus();

//        v.setOnTouchListener(onTouchListener);

		builder.setView(dialogView);


        builder.setMessage(R.string.entry)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
						mTextView.setText(editText.getText().toString());
						dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
					}
                });

		Dialog dialog = builder.create();
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

        return dialog;
    }

	public void setTextView(TextView textView){
		textView = mTextView;
	}

}