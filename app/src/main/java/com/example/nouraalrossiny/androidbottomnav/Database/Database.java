package com.example.nouraalrossiny.androidbottomnav.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.nouraalrossiny.androidbottomnav.model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private  static final String DB_NAME="eatId.db";
    private  static final int DB_VER=1;
    public static final String TABLE_NAME = "OrderDetail";
    public static final String COLUMN_RECID = "recID";
    public static final String COLUMN_ProductId = "ProductId";
    public static final String COLUMN_ProductName = "ProductName";
    public static final String COLUMN_Quantity = "Quantity";
    public static final String COLUMN_Price = "Price";
    public static final String COLUMN_Discount = "Discount";


    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlStmt = "CREATE table " + TABLE_NAME + "(" +
                COLUMN_RECID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_ProductId + " TEXT," +
                COLUMN_ProductName + " TEXT," +
                COLUMN_Quantity + " TEXT," +
                COLUMN_Price + " TEXT," +
                COLUMN_Discount + " TEXT" +
                ");";
        db.execSQL(sqlStmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        Log.d("DB", "The table has been removed!" + oldVersion);
        onCreate(db);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductName","ProductId","Quantity","Price","Discount"};
        String sqlTable="OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(new Order(c.getString(c.getColumnIndex("ProductId")),
                        c.getString(c.getColumnIndex("ProductName")),
                        c.getString(c.getColumnIndex("Quantity")),
                        c.getString(c.getColumnIndex("Price")),
                        c.getString(c.getColumnIndex("Discount"))

                ));
            }while(c.moveToNext());
        }
        return result;

    }

    public void addToCart(Order order){


       // SQLiteDatabase db = getReadableDatabase();
       // SQLiteDatabase db = getWritableDatabase();
       // SQLiteDatabase db = getWritableDatabase();
        SQLiteDatabase db = getWritableDatabase();


        // String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",


        /*
        order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount() );*/

       /* String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('",
                order.getProductName(), "','" ,order.getProductName(),"','",order.getQuantity(),"','",
                order.getPrice(),"','",order.getDiscount(),"');");
*/
        db.execSQL("insert into " + TABLE_NAME + "(" + COLUMN_ProductId + "," + COLUMN_ProductName + "," + COLUMN_Quantity + "," + COLUMN_Price + "," + COLUMN_Discount
                +") VALUES (?,?,?,?,?)" , new String[] {order.getProductId(),order.getProductName(),order.getQuantity(),order.getPrice(),order.getDiscount()} );




      //  db.execSQL(query);
    }

    public void CleanCart(){

       // SQLiteDatabase db = getReadableDatabase();
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("DELETE FROM " +TABLE_NAME );
        db.execSQL(query);
    }


}
