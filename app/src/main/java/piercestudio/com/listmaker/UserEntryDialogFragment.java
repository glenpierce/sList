package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;


public class UserEntryDialogFragment extends DialogFragment {

    public static final Bundle args = new Bundle();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.entry_dialog, null));

        final EditText userEntryEditText = (EditText) getView().findViewById(R.id.entryDialogEditText);
        userEntryEditText.requestFocus();

        builder.setMessage(R.string.entry)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        String getTextString = userEntryEditText.getText().toString();
                        char[] charArray = getTextString.toCharArray();
                        args.putCharArray("key", charArray);
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

    public interface UserEntryDialogFragmentListener {
        public String getUserEntry(DialogFragment dialog);

    }
}