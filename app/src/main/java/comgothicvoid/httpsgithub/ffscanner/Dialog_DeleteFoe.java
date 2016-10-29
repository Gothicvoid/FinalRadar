package comgothicvoid.httpsgithub.ffscanner;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/29.
 */
public class Dialog_DeleteFoe extends Dialog {

    public Dialog_DeleteFoe(Context context) {
        super(context);
    }

    public Dialog_DeleteFoe(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private View contentView;
        private String numb;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        public void setNumb(String s){
            numb = s;
        }

        //Set the positive button resource and it's listener
        public Builder setPositiveButton(DialogInterface.OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(DialogInterface.OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Dialog_DeleteFoe create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final Dialog_DeleteFoe dialog = new Dialog_DeleteFoe(context,R.style.Dialog_DeleteFoe);
            View layout = inflater.inflate(R.layout.dialog_delete_enemy, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            TextView tnum = (TextView) layout.findViewById(R.id.txt_enemy_number);
            tnum.setText(numb);
            // set the confirm button
            ((Button) layout.findViewById(R.id.btn_dialog_ok))
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            positiveButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_POSITIVE);
                        }
                    });
            // set the cancel button
            ((Button) layout.findViewById(R.id.btn_dialog_close))
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            negativeButtonClickListener.onClick(dialog,
                                    DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
            dialog.setContentView(layout);
            return dialog;
        }
    }
}