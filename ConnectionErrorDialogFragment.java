package aaron.com.pokedexapp;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Aaron on 1/10/2018.
 */

public class ConnectionErrorDialogFragment extends DialogFragment {

    private TryAgainListener listener;

    public interface TryAgainListener{
        public void onTryAgain();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            // Instantiate the ChooseColorDialogListener so we can send events to the host
            listener = (TryAgainListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement TryAgainListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Bundle args = getArguments();
        String errorInfo = args.getString("errorInfo");

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setMessage(errorInfo)
                .setPositiveButton(R.string.retry_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onTryAgain();
                    }
                });

        return builder.create();
    }
}
