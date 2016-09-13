package com.example.mysqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZengWenZhi on 2016/9/8 0008.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * 数据库版本，需要升级数据库时只要加一即可
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "mySQLite.db";

    /**
     * 构造方法
     * 每次创建DatabaseHelper对象时，若本应用无该数据库，则新建数据库并调用onCreate方法；
     * 若该数据库已创建则直接使用已存在的数据库且跳过onCreate方法
     * factory : 当打开的数据库执行查询语句的时候 会创建一个Cursor对象, 这时会调用Cursor工厂类 factory, 可以填写null默认值
     *
     * @param context 上下文
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库是时调用（只被调用一次）
     *
     * @param db 数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user表，属性：id（用户id，主键）、name（姓名）、age（年龄）
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(10),age INTEGER,imgId INTEGER)");
    }

    /**
     * 跟新数据库时调用
     *
     * @param db         数据库
     * @param oldVersion 旧版本号
     * @param newVersion 新版本号
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //升级user表，添加性别
        //db.execSQL("ALTER TABLE user ADD COLUMN gender VARCHAR(2)");
    }

    /**
     * 插入一条数据
     *
     * @param user 用户对象
     */
    public void insertAUser(User user) {
        //如果要对数据进行更改，就调用此方法得到用于操作数据库的实例,该方法以读和写方式打开数据库
        SQLiteDatabase database = getWritableDatabase();
        //向user表插入一条数据
        database.execSQL(
                "INSERT INTO user(name, age, imgId) VALUES(?,?,?)",
                new Object[]{user.getName(), user.getAge(), user.getImgId()});
    }

    /**
     * 更新一条用户数据
     *
     * @param user 用户对象
     */
    public void updateAUser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id更新一条数据
        database.execSQL(
                "UPDATE user SET name=?, age=? WHERE id=?",
                new Object[]{user.getName(), user.getAge(), user.getId()});
    }

    /**
     * 根据id删除一条数据
     *
     * @param id 用户id
     */
    public void deleteAUser(Integer id) {
        SQLiteDatabase database = getWritableDatabase();
        //根据id删除一条数据
        database.execSQL("DELETE FROM user WHERE id=?",
                new Object[]{id});
    }

    /**
     * 获取整个用户列表
     *
     * @return
     */
    public List<User> readAllUser() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM user", new String[]{});
        List<User> list = new ArrayList<User>();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setImgId(cursor.getInt(cursor.getColumnIndex("imgId")));
            list.add(user);
        }
        cursor.close();
        return list;
    }

    /**
     * 读取一条数据
     *
     * @param id 用户id
     * @return 用户对象
     */
    public User readAUser(Integer id) {

        //如果只对数据进行读取，建议使用此方法
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * FROM user WHERE id=?",
                new String[]{id.toString()});
        if (cursor.moveToFirst()) {
            //读取数据，并返回
            User user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex("id")));
            user.setName(cursor.getString(cursor.getColumnIndex("name")));
            user.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            user.setImgId(cursor.getInt(cursor.getColumnIndex("imgId")));
            cursor.close();
            return user;
        } else {
            //未读出数据，返回空数据
            return null;
        }
    }
}

