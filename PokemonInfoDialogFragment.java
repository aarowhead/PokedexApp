package aaron.com.pokedexapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Aaron on 1/9/2018.
 */

public class PokemonInfoDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Bundle args = getArguments();
        PokemonInfo pokemonInfo = (PokemonInfo)args.get("pokemonInfo");

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(pokemonInfo.getName())
                .setNegativeButton(R.string.close_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
