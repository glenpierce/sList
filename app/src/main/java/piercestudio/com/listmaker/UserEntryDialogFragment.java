package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;


public class UserEntryDialogFragment extends DialogFragment {

    public static final Bundle args = new Bundle();
	public String getTextString = "default";

	EditText editText = new EditText(getActivity());

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.entry_dialog, null);

        editText = (EditText) v.findViewById(R.id.entryDialogEditText);

        builder.setView(v);

        builder.setMessage(R.string.entry)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
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

	public String getEditText(){
		return editText.getText().toString();
	}

    public interface UserEntryDialogFragmentListener {
        public String getUserEntry(UserEntryDialogFragment dialog);
    }
}