package samle.aplication.memopad;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDBHelper extends SQLiteOpenHelper {
  static final String name = "memos.db";
  static final int version = 1;
  static final CursorFactory factory = null;
  
  public MemoDBHelper(Context context) {
    super(context, name, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE mempDB ("
                 +android.provider.BaseColumns._ID
                 +" INTEGER PRIMARY KEY AUTOINCREMENT, title Text, memo TEXT);";
                 db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

  }

}
