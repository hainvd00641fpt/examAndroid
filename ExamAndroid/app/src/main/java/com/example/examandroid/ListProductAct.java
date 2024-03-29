package com.example.examandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListProductAct extends AppCompatActivity {
    private DBHelper db;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DBHelper(this);
        ListView lvUser = (ListView) findViewById(R.id.lv);

        c = db.getAllUser();

        adapter = new SimpleCursorAdapter(this, R.layout.item_product, c, new String[]{
                DBHelper.ID, DBHelper.NAME, DBHelper.QUANTITY}, new int[]{
                R.id.tvId, R.id.tvName, R.id.tvQuantity}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        lvUser.setAdapter(adapter);

        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                int _id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID));
                String name = cursor.getString(cursor.getColumnIndex(DBHelper.NAME));
                String quantity = cursor.getString(cursor.getColumnIndex(DBHelper.QUANTITY));

//                Intent intent = new Intent(ListProductAct.this, UpdateAct.class);
//                intent.putExtra(DBHelper.ID,_id);
//                intent.putExtra(DBHelper.NAME,name);
//                intent.putExtra(DBHelper.QUANTITY,quantity);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        c = db.getAllUser();
        adapter.changeCursor(c);
        adapter.notifyDataSetChanged();
        db.close();
    }
}
