package com.example.yoony.opensourceandroidproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.yoony.opensourceandroidproject.db.DBConst;

public class DBHelper extends SQLiteOpenHelper {

    //DBHelper 생성자로 관리할 DB이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //새로운 테이블 생성
        db.execSQL("CREATE TABLE Quest(main TEXT,sub TEXT);");
        db.execSQL("CREATE TABLE Date(month INTEGER,day INTEGER);");
        db.execSQL("CREATE TABLE Todo(_id INTEGER PRIMARY KEY AUTOINCREMENT,job TEXT,date INTEGER,quest TEXT,isdone INTEGER DEFAULT '0' );");
        db.execSQL("CREATE TABLE User(nickname TEXT,point INTEGER);");
        db.execSQL("CREATE TABLE Shop(item TEXT,price INTEGER);");
        Log.e("tableCreate","");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//db버전이 다를경우

    }

    public void  setShopItem(String item, int price){
        SQLiteDatabase db = getWritableDatabase();

        Log.e("sange", "어이템셋");
        db.execSQL("insert into Shop values('"+item+"',"+price+");");
        db.close();
    }

    public String  selectShopItem(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";

        Cursor cursor=db.rawQuery("select * from Shop",null);
        while(cursor.moveToNext()){
            result += cursor.getString(0)+"|"+cursor.getInt(1)+"\n";
        }
        Log.e("sangeun", result);
        return result;
    }

    public int isEmptyShopItem(){
        SQLiteDatabase db=getReadableDatabase();
        try{
            db.execSQL("CREATE TABLE Shop(item TEXT,price INTEGER);");
        }catch(Exception e){}
        String result="";

        Cursor cursor=db.rawQuery("select * from Shop",null);
        while(cursor.moveToNext()){
            result += cursor.getString(0)+"|"+cursor.getInt(1)+"\n";
            Log.e("sangeun", result);
        }
        if(result=="")
            return 1;
        else
            return 0;

    }

    public void deleteShopItem(String item){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from Shop where item='"+item+"';");
    }

    public void  setUserNickname(String nickname, int point){
        SQLiteDatabase db = getWritableDatabase();
        try{
            db.execSQL("CREATE TABLE User(nickname TEXT,point INTEGER);");
        }catch(Exception e){}

        db.execSQL("insert into User values('"+nickname+"',"+point+");");
        db.close();
    }

    public void  updateUserNickname(String nickname){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("update User set nickname='"+nickname+"';");
        db.close();
    }

    public void  plusUserPoint(int point){
        SQLiteDatabase db = getWritableDatabase();
        int result = selectUserpoint();
        result += point;

        db.execSQL("update User set point='"+result+"';");
        db.close();
    }

    public void  minusUserPoint(int point){
        SQLiteDatabase db = getWritableDatabase();
        int result = selectUserpoint();
        result -= point;

        db.execSQL("update User set point='"+result+"';");
        db.close();
    }

    public String selectUsername(){
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        Cursor cursor=db.rawQuery("select nickname from User",null);
        while(cursor.moveToNext()) {
            result += cursor.getString(0);
        }
        return result;
    }

    public int selectUserpoint(){
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        Cursor cursor=db.rawQuery("select point from User",null);
        while(cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }

    public void insert(String main,String sub){//퀘스트 추가
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("INSERT INTO Quest VALUES('" + main + "','" + sub + "');");
        db.close();
    }
    public void insert(int month,int day){//날짜 추가
        SQLiteDatabase db = getWritableDatabase();
        String sql="insert into Date values('"+month+"','"+day+"');";
        db.execSQL(sql);
        db.close();
    }
    public  void insert(String job,int date,String quest){//할일 추가
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL("insert into Todo values(null,'"+job+"',"+date+",'"+quest+"',"+0+");");
        db.close();
    }

    public void updateSub(String main,String sub){//서브퀘스트 수정
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update Quest set sub='"+sub+"'where main='"+main+"';");
    }
    public void updateTodoDate(String job,int date){//날짜 수정
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update Todo from date="+date+" where job='"+job+"';");
    }
    public void updateJob(String job,String quest){//할일 수정
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update Todo from job='"+job+"'where quest='"+quest+"';");
    }
    public void updateisDone(int id,int isdone){//할일 수행여부 수정
        SQLiteDatabase db = getWritableDatabase();
        Log.e("id", ""+id);
        db.execSQL("update Todo set isdone=" +isdone+" where _id="+id+" ;");
        db.close();
    }
    public void deleteTodo(String job){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from Todo where job='"+job+"';");
    }

    public void deleteMainQuest(String main){//동일한 메인퀘스트를 갖는 모든 서브퀘스트 삭제
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from Quest where main='"+main+"';");
        db.close();
    }
    public void deleteSubQuest(String sub){//단일 서브퀘스트 삭제
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from Quest where sub='"+sub+"';");
        db.close();
    }
    public void deleteId(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from Todo where _id="+id+";");
    }

    public String selectTodo(){
        SQLiteDatabase db=getReadableDatabase();
        String result="";

        Cursor cursor=db.rawQuery("select * from Todo",null);
        while(cursor.moveToNext()){
            result+=cursor.getInt(0)+"|"+cursor.getString(1)+"|"+cursor.getInt(2)+"|"+cursor.getString(3)+"|"+cursor.getInt(4)+"\n";
        }
        Log.e("selectTodo",""+result);
        return result;
    }
        public String MainQuest(){
            SQLiteDatabase db=getReadableDatabase();
            String result="";

            Cursor cursor=db.rawQuery("select distinct "+DBConst.GoalTable.GOAL_TITLE+" from "+DBConst.GoalTable.TABLE_NAME,null);
            while (cursor.moveToNext()){
                result+=cursor.getString(0)+"\n";
            }
            Log.e("TodoQuest",""+result);
            return result;
    }
        public String SubQuest(){
            SQLiteDatabase db=getReadableDatabase();
            String result="";

            Cursor cursor=db.rawQuery("select distinct "+DBConst.SubGoalTable.SUBTITLE+" from "+DBConst.SubGoalTable.TABLE_NAME,null);
            while (cursor.moveToNext()){
                result+=cursor.getString(0)+"\n";
            }
            Log.e("TodoQuest",""+result);
            return result;
    }
    public String sortTodo(int date){

        SQLiteDatabase db=getReadableDatabase();
        try{
            db.execSQL("CREATE TABLE Todo(_id INTEGER PRIMARY KEY AUTOINCREMENT,job TEXT,date INTEGER,quest TEXT,isdone INTEGER DEFAULT '0' );");
        }catch(Exception e){}
        String result="";

        Cursor cursor=db.rawQuery("select * from Todo where date="+date+" order by isdone asc, job asc",null);
        while(cursor.moveToNext()){
            result+=cursor.getInt(0)+"|"+cursor.getString(1)+"|"+cursor.getInt(2)+"|"+cursor.getString(3)+"|"+cursor.getInt(4)+"\n";
        }
        Log.e("sortTodo",""+result);
        return result;
    }
    public String selectTodoByDate(int date){//날짜별 할일
        SQLiteDatabase db = getReadableDatabase();
        String result="";

        Cursor cursor = db.rawQuery("select date='"+date+"' from Todo",null);
        while(cursor.moveToNext()){
            result+=cursor.getString(0)+"|"+cursor.getInt(1)+"|"+cursor.getString(2)+"|"+cursor.getInt(3)+"\n";
        }
        return result;
    }
    public String selectAllQuest(){//모든 퀘스트 출력
        SQLiteDatabase db=getReadableDatabase();
        String result="";

        Cursor cursor=db.rawQuery("select * from Quest",null);
        while(cursor.moveToNext()){
            result+=cursor.getString(0)+"|"+cursor.getString(1)+"\n";
        }
        return result;
    }
    public String selectSubQuest(){
        return "";
    }
}
