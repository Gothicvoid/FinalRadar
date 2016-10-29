package comgothicvoid.httpsgithub.ffscanner;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/10/29.
 */
public class CursorAdapter_Foe extends CursorAdapter {
    private LayoutInflater mInflater;
    private Context mContext;
    public CursorAdapter_Foe(Context context, Cursor c) {
        super(context, c);
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    //数据库相关
    private static SQLiteDatabase db;

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {

        TextView tname = (TextView) view.findViewById(R.id.name_cell);
        Button del = (Button) view.findViewById(R.id.delete_button_cell);
        tname.setText(cursor.getString(cursor.getColumnIndex("name")));

        del.setTag(cursor.getString(cursor.getColumnIndex("num")));
        del.setOnClickListener(new  View.OnClickListener()
        {
            public void onClick(View v){
                final String id = v.getTag().toString();
                final Dialog_DeleteFoe.Builder builder = new Dialog_DeleteFoe.Builder(context);
                builder.setNumb(id);
                builder.setPositiveButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //打开数据库
                        db = DBConnection.open(context);
                        db.delete("foes","num=?",new String[]{id});
                        DBConnection.close(db);
                        ((Activity)mContext).recreate();
                    }
                });
                builder.setNegativeButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
        return mInflater.inflate(R.layout.enemies_list_item, arg2, false);
    }
}