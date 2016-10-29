package comgothicvoid.httpsgithub.ffscanner;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class Activity_Friends extends AppCompatActivity {

    //数据库相关
    private static SQLiteDatabase db;
    //信息列表
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friends_list);

        //打开数据库
        db = DBConnection.open(this);

        listView = (ListView) findViewById(R.id.lvw_friends_list);
        setListView();

        final Dialog_AddFriend.Builder builder = new Dialog_AddFriend.Builder(this);
        Button add = (Button)findViewById(R.id.btn_friends_list_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setPositiveButton(new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", builder.getname());
                        contentValues.put("num", builder.getnum());
                        db.insert("friends",null,contentValues);
                        setListView();
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

        Button radar = (Button)findViewById(R.id.btn_friends_list_radar);
        radar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Friends.this,Activity_Main.class);
                startActivity(intent);
                finish();
            }
        });

        Button foe = (Button)findViewById(R.id.btn_friends_list_enemies);
        foe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Friends.this,Activity_Foes.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        DBConnection.close(db);
        Intent intent = new Intent();
        setResult(100,intent);
        super.onDestroy();
    }

    private void setListView(){
        Cursor cursor = db.query("friends",new String[]{"_id","name","num"},null,null,null,null,"name");
        CursorAdapter_Friend friendsCursorAdapter = new CursorAdapter_Friend(this,cursor);
        listView.setAdapter(friendsCursorAdapter);
    }
}