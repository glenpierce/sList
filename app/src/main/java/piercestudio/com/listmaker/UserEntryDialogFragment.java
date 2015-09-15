package piercestudio.com.listmaker;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;


public class UserEntryDialogFragment extends DialogFragment {

    public static final Bundle args = new Bundle();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.entry_dialog, null));

        //In our attempts to instantiate the EditText, we have not yet found a means by which we can do so without a null pointer exception - see notes below

//        EditText userEntryEditText;
//        userEntryEditText = userEntryEditText.findViewById(R.id.entryDialogEditText); - may not have been initalized
//        final EditText userEntryEditText = (EditText) userEntryEditText.findViewById(R.id.entryDialogEditText); - variable may not have been initiized
//        final EditText = new EditText(); - not a valid constructor
//        final EditText = new (EditText) userEntryEditText.findViewById(R.id.entryDialogEditText); - cannot resolve symbol userEntryText
//        EditText userEntryEditText = new userEntryEditText.findViewWithTag("edittexttag"); - findViewWithTag not a method
//        inflater.inflate(R.id.entryDialogEditText, null); - requires a layout argument

        //This should work once the EditText has been instantiated
//                userEntryEditText.requestFocus();

        builder.setMessage(R.string.entry)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                        //Since the EditText hasn't been instantiated, we have to wait before we can send data through the Bundle

//                        String getTextString = userEntryEditText.getText().toString();
//                        char[] charArray = getTextString.toCharArray();
//                        args.putCharArray(getString(R.string.userentrykey), charArray);
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