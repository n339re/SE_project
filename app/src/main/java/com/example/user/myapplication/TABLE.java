package com.example.user.myapplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class TABLE {
    public static final String TABLE_NAME = "appointment";
    public static final String COLUMN_ID = "ID";
    public static final String DEPT_NAME = "dept";
    public static final String DOCTOR_NAME = "doctor";
    public static final String NUM = "number";
    public static final String DATE = "date";
    public static final String SLOT = "slot";

    public static final String TABLE_NAME2 = "server";
    public static final String COLUMN_ID2= "ID";
    public static final String DEPT_NAME2 = "dept";
    public static final String DOCTOR_NAME2 = "doctor";
    public static final String C_NUM = "number"; //current num
    public static final String TIME = "time";   //predict time

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DEPT_NAME + " TEXT NOT NULL, " +
                    DOCTOR_NAME + " TEXT NOT NULL, " +
                    NUM + " INTEGER NOT NULL, " +
                    DATE +" TEXT NOT NULL," +
                    SLOT +" TEXT NOT NULL,)";
    //DATE + "STRING",)";

    public static final String CREATE_TABLE2 =
            "CREATE TABLE " + TABLE_NAME2 + " (" +
                    COLUMN_ID2+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DEPT_NAME2 + " TEXT NOT NULL, " +
                    DOCTOR_NAME2 + " TEXT NOT NULL, " +
                    C_NUM + " INTEGER NOT NULL, " +
                    TIME +" TEXT NOT NULL,)";

    private static SQLiteDatabase db;

    public TABLE(Context context) {
        db = DBHandler.getDatabase(context);
    }

    public void close() {
        db.close();
    }

    public static ITEM add(ITEM item) {
        // 建立準備新增資料的ContentValues物件
        //SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
        // String date = sdf.format(new Date());
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DEPT_NAME, item.getDeptname());
        cv.put(DOCTOR_NAME, item.getDoctorname());
        cv.put(NUM, item.getNum());
        cv.put(DATE,item.getDate());
        cv.put(DATE,item.getSlot());
        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);

        // 設定編號
        item.setID(id);
        // 回傳結果
        return item;
    }
    public data_table add2(data_table item) {
        // 建立準備新增資料的ContentValues物件
        //SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
        // String date = sdf.format(new Date());
        ContentValues cv = new ContentValues();


        cv.put(DEPT_NAME2, item.getDept());
        cv.put(DOCTOR_NAME2, item.getDoctorname());
        cv.put(C_NUM, item.getNum());
        cv.put(TIME,item.getP());

        long id = db.insert(TABLE_NAME2, null, cv);

        // 設定編號
        item.setID(id);
        // 回傳結果
        return item;
    }
    public boolean update(ITEM item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DEPT_NAME, item.getDeptname());
        cv.put(DOCTOR_NAME, item.getDoctorname());
        cv.put(NUM, item.getNum());
        cv.put(DATE,item.getDate());
        cv.put(DATE,item.getSlot());
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = COLUMN_ID + "=" + item.getID();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }
    public boolean update2(data_table item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();

        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(DEPT_NAME2, item.getDept());
        cv.put(DOCTOR_NAME2, item.getDoctorname());
        cv.put(C_NUM, item.getNum());
        cv.put(TIME,item.getP());
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = COLUMN_ID2 + "=" + item.getID();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME2, cv, where, null) > 0;
    }

    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = COLUMN_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }
    public boolean delete2(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = COLUMN_ID2 + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME2, where , null) > 0;
    }

    public List<ITEM> getAll() {
        List<ITEM> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }
    public List<data_table> getAll2() {
        List<data_table> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME2, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord2(cursor));
        }

        cursor.close();
        return result;
    }

    public ITEM get(long id) {
        // 準備回傳結果用的物件
        ITEM item = null;
        // 使用編號為查詢條件
        String where = COLUMN_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }
    public data_table get2(long id) {
        // 準備回傳結果用的物件
        data_table item = null;
        // 使用編號為查詢條件
        String where = COLUMN_ID2 + "=" + id;
        // 執行查詢
        Cursor result = db.query(
                TABLE_NAME2, null, where, null, null, null, null, null);

        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord2(result);
        }

        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }
    public ITEM getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        ITEM result = new ITEM();

        result.setID(cursor.getLong(0));
        result.setDeptname(cursor.getString(1));
        result.setDoctorname(cursor.getString(2));
        result.setNum(cursor.getInt(3));
        result.setDate(cursor.getString(4));
        result.setSlot(cursor.getString(5));
        // 回傳結果
        return result;
    }
    public data_table getRecord2(Cursor cursor) {
        // 準備回傳結果用的物件
        data_table result = new data_table();

        result.setID(cursor.getLong(0));
        result.setDept(cursor.getString(1));
        result.setDoctorname(cursor.getString(2));
        result.setNum(cursor.getInt(3));
        result.setP(cursor.getInt(4));

        // 回傳結果
        return result;
    }
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }
    public int getCount2() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME2, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    /*public void sample() {
        ITEM item = new ITEM(0, "家庭醫療科", "葉亭均", 20);


        add(item);
    }*/
    public void sample() {
        data_table item = new data_table(0, "家庭醫療科", "葉亭均", 20,50);


        add2(item);
    }
}

