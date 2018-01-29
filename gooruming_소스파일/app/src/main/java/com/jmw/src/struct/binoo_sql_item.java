package com.jmw.src.struct;

/**
 * Created by junminwoo on 2016-10-28.
 */

public class binoo_sql_item {

    private static binoo_sql_item instance;

    public static binoo_sql_item getInstance(){
        return instance = (instance == null ? new binoo_sql_item() : instance);
    }

    // TAG item INFO
    public static final String TAG_RESULTS="result";
    public static final String TAG_BBSNO = "bbsno";
    public static final String TAG_TITLE ="title";
    public static final String TAG_CONTENT = "content";
    public static final String TAG_SENO = "seno";
    public static final String TAG_BBSCODE ="bbscode";
    public static final String TAG_PID = "pid";
    public static final String TAG_WETHER ="wether";
    public static final String TAG_TT = "tt";
    public static final String TAG_SHOTT ="shott";
    public static final String TAG_CUR_IM ="cur_im";

    public final String TAG_HITITEM_PART="dep";
    public final String TAG_HITITEM_PARTALL="depall";
    public final String TAG_HITITEM_VAL="value";
    public final String TAG_HITITEM_ID="HitRatebyID";
    public final String TAG_HITITEM_IDALL="HitRateAllbyIDN";
    public final String TAG_HITITEM_LOC="HitRatebyLOC";
    public final String TAG_HITITEM_LOCALL="HitRateAllbyLOCN";

    // Server Address Info
    //public final String SERVER_ADDRESS="http://wjsalsdn001.dothome.co.kr/";
    public final String SERVER_ADDRESS="http://118.37.234.70:10080/";


    // SERVER SIDE CODE INFO

    public final String SERVER_ITEM_SAMPLE_CALL="binoo_sample_test.php";

    public final String SERVER_BBS_ITEM_CALL="grumy_item_select_bbs.php";
    public final String SERVER_BBS_ADD_CALL="grumy_item_insert_bbs.php";
    public final String SERVER_BBS_REMOVE_CALL="grumy_item_delete_bbs.php";
    public final String SERVER_BBS_EDIT_CALL="grumy_item_update_bbs.php";
    public final String SERVER_BBS_ITEMLIST_CALL="grumy_list_select_bbs.php";

    public final String SERVER_LOGIN_CALL="grumy_login.php";

    public final String SERVER_REPL_ADD_CALL="grumy_item_insert_repl.php";
    public final String SERVER_REPL_ITEMLIST_CALL="grumy_list_select_repl.php";
    public final String SERVER_REPL_ITEM_CALL="grumy_item_select_repl.php";

    public final String SERVER_PROC_HITRATE_CALL="binoo_shot_item_getter.php";
    public final String SERVER_PROC_HIT_GO="binoo_shot_item_update.php";
    public final String SERVER_PROC_UPLOAD="UploadToServer.php";

}