package com.example.yoony.opensourceandroidproject.db.factory;

import android.accounts.Account;
import android.content.Context;

import com.example.yoony.opensourceandroidproject.L;
import com.example.yoony.opensourceandroidproject.db.DbHelper;
import com.example.yoony.opensourceandroidproject.db.dao.GoalDAO;
import com.example.yoony.opensourceandroidproject.db.model.Goal;

import java.util.List;


public class GoalDAOFactory {

    public GoalDAOFactory() {
    }


    public static long addGoal(Context context, Goal goal) throws Exception {
        //db에 최종목표를 추가 시키는 함수이다.
        long pass = -1;
        DbHelper dbHelper = null;
        try {
            dbHelper = DbHelper.getInstance(context);
            GoalDAO goalDAO = new GoalDAO(context, dbHelper.getWritableDatabase());
            pass = goalDAO.addDate(goal);
            L.e("::::add success : " + pass);
        } catch (Exception e) {
            L.e(":::::GoalDAOFactory addGoal Exception");
        } finally {
            if (dbHelper != null) {
                dbHelper.close();
            }
        }
        return pass;
    }

    public static List<Goal> getGoalList(Context context) throws Exception {
        //db에 있는 최종 목표 리스트를 호출하는 함수이다.
        List<Goal> list = null;
        DbHelper dbHelper = null;
        try {
            dbHelper = DbHelper.getInstance(context);
            GoalDAO goalDAO = new GoalDAO(context, dbHelper.getWritableDatabase());
            list = goalDAO.getGoalList();
        } catch (Exception e) {
            L.e(":::::GoalDAOFactory getGoalList Exception");
        } finally {
            if (dbHelper != null) {
                dbHelper.close();
            }
        }
        return list;
    }

}
