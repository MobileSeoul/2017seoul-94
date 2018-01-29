package com.jmw.adaptertest.seojungwon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by won on 2016. 12. 4..
 */

public class won_SQLite extends SQLiteOpenHelper {

    public won_SQLite(Context context){
        super(context,"anna_a_db.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE anna_board(_id INTEGER PRIMARY KEY AUTOINCREMENT ,_bbscode INTEGER, anna_title TEXT NOT NULL , anna_content TEXT NOT NULL, anna_seno INTEGER , anna_bbscode INTEGER , anna_pid INTEGER, anna_weather INTEGER, anna_tt DATE , anna_ts TIME, anna_shott INTEGER , anna_cur_im TEXT );");


/*

CREATE TABLE IF NOT EXISTS `we_bbs` (
  `bbsno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `seno` int(11) DEFAULT NULL,
  `bbscode` varchar(2) DEFAULT NULL,
  `pid` varchar(200) DEFAULT NULL,
  `wether` int(11) DEFAULT NULL,
  `tt` time DEFAULT NULL,
  `shott` int(11) DEFAULT NULL,
  `cur_im` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bbsno`)
)
  */

        /*

CREATE TABLE IF NOT EXISTS `we_food` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `food_code` tinyint(4) NOT NULL,
  `food_name` text NOT NULL,
  `food_temp` float NOT NULL,
  `food_type` tinyint(4) NOT NULL,
  `person_temp` float NOT NULL,
  `weather` tinyint(4) NOT NULL,
  `anna_date` date NOT NULL,
  `anna_time` time NOT NULL,
  `bake_data` double NOT NULL,
  PRIMARY KEY (`_id`)
)
         */

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){



    }
}
