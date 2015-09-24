package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;


public class UserEntryDialogFragment extends DialogFragment {

    public static final Bundle args = new Bundle();

//	EditText editText = new EditText(getActivity());
	static Button mButton;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.entry_dialog, null);

		final EditText editText = (EditText) v.findViewById(R.id.entryDialogEditText);

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
        return builder.create();
    }

	static public void setButton(Button button){
		mButton = button;
	}

	public interface UserEntryDialogFragmentListener {
        public void getUserEntry(UserEntryDialogFragment dialog, Button button);
    }
}