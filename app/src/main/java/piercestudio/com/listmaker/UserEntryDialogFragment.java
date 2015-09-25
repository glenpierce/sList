package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;



public class UserEntryDialogFragment extends DialogFragment {

    public static final Bundle args = new Bundle();

	static Button mButton;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.entry_dialog, null);

		final EditText editText = (EditText) v.findViewById(R.id.entryDialogEditText);
		editText.requestFocus();
		builder.setView(v);


        builder.setMessage(R.string.entry)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
						mButton.setText(editText.getText().toString());
						dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
					}
                });
        // Create the AlertDialog object and return it

		Dialog dialog = builder.create();
		dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MODE_CHANGED);

        return dialog;
    }

	static public void setButton(Button button){
		mButton = button;
	}


}