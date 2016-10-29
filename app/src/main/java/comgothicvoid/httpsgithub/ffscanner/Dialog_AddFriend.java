package comgothicvoid.httpsgithub.ffscanner;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/26.
 */
public class Dialog_AddFriend extends Dialog {

    public Dialog_AddFriend(Context context) {
        super(context);
    }

    public Dialog_AddFriend(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private String name;
        private String num;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
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

        public String getname(){
            return name;
        }

        public String getnum(){
            return num;
        }

        public Dialog_AddFriend create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final Dialog_AddFriend dialog = new Dialog_AddFriend(context,R.style.Dialog_Friend);
            View layout = inflater.inflate(R.layout.dialog_add_friend, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final EditText ename = (EditText)layout.findViewById(R.id.txt_friend_name);
            final EditText enumb = (EditText)layout.findViewById(R.id.txt_friend_number);
            // set the confirm button
            ((Button) layout.findViewById(R.id.btn_dialog_ok))
                    .setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            name = ename.getText().toString();
                            num = enumb.getText().toString();
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