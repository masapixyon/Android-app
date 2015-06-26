package samle.aplication.memopad;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Selection;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class MemopadAvtivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.avtivity_main);
    
    EditText et = (EditText) findViewById(R.id.editText1);
    SharedPreferences pref = getSharedPreferences("MemoPrefs",MODE_PRIVATE);
    et.setText(pref.getString("memo",""));
    et.setSelection(pref.getInt("cursor", 0));
  }
  @Override
  protected void onStop() {
    super.onStop();
    EditText et = (EditText) findViewById(R.id.editText1);
    SharedPreferences pref = getSharedPreferences("MemoPrefs",MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();
    editor.putString("Memo",et.getText().toString());
    editor.putInt("cursor",Selection.getSelectionStart(et.getText()));
    editor.commit();
  }
  
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater mi = getMenuInflater();
    mi.inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    EditText et = (EditText) findViewById(R.id.editText1);
    switch(item.getItemId()){     //menuのIDを収得
    case R.id.menu_save:
      saveMemo();
      break;
    case R.id.menu_open:
      Intent i = new Intent(MemopadAvtivity.this,MemoList.class);   //menuListをiに格納
      startActivityForResult(i,0);
      break;
    case R.id.menu_new:
      et.setText("");
      break;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(resultCode==RESULT_OK){
      EditText et = (EditText) findViewById(R.id.editText1);
      
      switch(requestCode){
      case 0:
        et.setText(data.getStringExtra("text"));
        break;
      }
    }
  }
  
  void saveMemo(){
    EditText et = (EditText) this.findViewById(R.id.editText1);
    String title;
    String memo = et.getText().toString();      //メモの内容を格納
    
    if(memo.trim().length() > 0){               //メモの長さが0出ない時
      if(memo.indexOf("¥n") == 1){             //indexOf("¥n") == -1は改行がないということ
        title = memo.substring(0,Math.min(memo.length(), 20));     
        //20文字までをtitleに格納
      }
      else{
        title = memo.substring(0,Math.min(memo.indexOf("¥n"), 20));     
        //20までで、改行が行われるまでをtitleに格納
      }
      String ts = DateFormat.getDateTimeInstance().format(new Date());     //保存に日時を格納
      MemoDBHelper memos = new MemoDBHelper(this);
      SQLiteDatabase db = memos.getWritableDatabase();                    //書き込み状態でmemosのデータベースを開く
      ContentValues values = new ContentValues(); 
      values.put("title", title+"¥n"+ts);                                 //タイトルを書き込み
      values.put("memo","memo" );                                         //メモを書き込み
      db.insertOrThrow("memoDB", null, values);
      memos.close();                                                      //memosを閉じる
    }
  }
  
}
