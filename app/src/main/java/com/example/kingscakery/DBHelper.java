package com.example.kingscakery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "pemesanan.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table datatransaksi(nama TEXT primary key, telp TEXT, alamat TEXT, pesanan TXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists datatransaksi");
    }
    public boolean insertdatatransaksi(String nama, String telp, String alamat, String pesanan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("telp", telp);
        contentValues.put("alamat", alamat);
        contentValues.put("pesanan", pesanan);

        long result = db.insert("datatransaksi", null, contentValues);
        if (result== -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean updatedatatransaksi(String nama, String telp, String alamat, String pesanan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("telp", telp);
        contentValues.put("alamat", alamat);
        contentValues.put("pesanan", pesanan);
        Cursor cursor = db.rawQuery("Select * from datatransaksi where nama = ?", new String[] {nama});
        if (cursor.getCount() > 0) {
            long result = db.update("datatransaksi", contentValues, "nama=?", new String[]{nama});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public boolean deletedatatransaksi(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from datatransaksi where nama = ?", new String[] {nama});
        if (cursor.getCount() > 0) {
            long result = db.delete("datatransaksi", "nama=?", new String[]{nama});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public Cursor getdatatransaksi(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from datatransaksi",null);
        return cursor;
    }
}
