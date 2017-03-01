package com.jairobilbao.infociudades.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jairobilbao.infociudades.Ciudad;

/**
 * Created by 21542295 on 01/03/2017.
 */
public class CityDatasource {
    private Context mContext;
    private SQLHelper mSQLiteHelper ;
    public CityDatasource(Context context) {
        mContext = context;
        mSQLiteHelper = new SQLHelper (mContext);
    }
    public SQLiteDatabase openReadable() {
        return mSQLiteHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return mSQLiteHelper.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    public void insertCity(Ciudad ciudad) {
        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLHelper.COLUMN_CITY, ciudad.getNomCiudad());
        contentValues.put(SQLHelper.COLUMN_PROV, ciudad.getProvincia());
        contentValues.put(SQLHelper.COLUMN_POBL, ciudad.getPoblacion());

        database.insert(SQLHelper.TABLE_NAME,null,contentValues);
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    private int getIntFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getInt(columnIndex);
    }
    private String getStringFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getString(columnIndex);
    }
    private Float getFloatFromColumnName(Cursor cursor, String
            columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        return cursor.getFloat(columnIndex);
    }

    public Ciudad getCity2(String nombreCiudad){
        SQLiteDatabase database = openReadable();
        Ciudad ciudad = new Ciudad();
        Cursor cursor = database.rawQuery(
                "SELECT * " +
                        " FROM "+SQLHelper.TABLE_NAME +
                        " WHERE "+SQLHelper.COLUMN_CITY+" = "+nombreCiudad+";",
                null);
        if (cursor.moveToFirst()) {
            ciudad = new Ciudad (
                    getIntFromColumnName(cursor, SQLHelper.COLUMN_ID),
                    getStringFromColumnName(cursor, SQLHelper.COLUMN_CITY),
                    getStringFromColumnName(cursor,SQLHelper.COLUMN_PROV),
                    getIntFromColumnName(cursor,SQLHelper.COLUMN_POBL)
            );
        }
        cursor.close();
        database.close();
        return ciudad;
    }
    public Ciudad getCity(String nombreCiudad){
        SQLiteDatabase database = openReadable();
        Ciudad ciudad = new Ciudad();
        Cursor cursor = database.query( SQLHelper.TABLE_NAME,
                new String[]{SQLHelper.COLUMN_ID,
                        SQLHelper.COLUMN_CITY,
                        SQLHelper.COLUMN_PROV,
                        SQLHelper.COLUMN_POBL,
                },
                String.format("%s=%s", SQLHelper.COLUMN_CITY,nombreCiudad), null, null, null, null );
        if (cursor.moveToFirst()) {
            ciudad = new Ciudad (
                    getIntFromColumnName(cursor, SQLHelper.COLUMN_ID),
                    getStringFromColumnName(cursor, SQLHelper.COLUMN_CITY),
                    getStringFromColumnName(cursor,SQLHelper.COLUMN_PROV),
                    getIntFromColumnName(cursor,SQLHelper.COLUMN_POBL)
            );
        }
        cursor.close();
        database.close();
        return ciudad;
    }

    public void updateCiudad(Ciudad ciudad) {
        //execsql solo para update

        SQLiteDatabase database = openWriteable();
        database.beginTransaction();
        database.execSQL("UPDATE "+SQLHelper.TABLE_NAME+" SET "+SQLHelper.COLUMN_CITY+ " = '"+ciudad.getNomCiudad()+
                "', "+SQLHelper.COLUMN_PROV+" = '"+ciudad.getProvincia()+
                        "', "+SQLHelper.COLUMN_POBL+" = '"+ciudad.getPoblacion()+
                "' WHERE "+SQLHelper.COLUMN_ID+" = "+ciudad.getCodCiudad());
        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);





    }
}
