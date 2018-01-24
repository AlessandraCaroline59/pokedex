package pokedex.thepokemoncompany.com.pokedex.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import pokedex.thepokemoncompany.com.pokedex.R;

/**
 * Created by Alessandra on 23/01/2018.
 */

public class SpinnerDialog {

    private ProgressDialog progress;

    public void showLoadingDialog(Activity context, String title) {
        if (progress == null) {
            progress = new ProgressDialog(context);
            progress.setTitle(title);
        }
        progress.show();
    }

    public void dismissLoadingDialog(Activity context) {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
