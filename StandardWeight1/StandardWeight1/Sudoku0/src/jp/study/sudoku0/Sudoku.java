package jp.study.sudoku0;

import jp.study.sudoku0.About;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class Sudoku extends Activity implements OnClickListener{
  private static final String TAG = "Sudoku0";
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sudoku);
    View conteinueButton = findViewById(R.id.continue_button);
    conteinueButton.setOnClickListener(this);
    View newBotton = findViewById(R.id.new_button);
    newBotton.setOnClickListener(this);
    View aboutButton = findViewById(R.id.about_button);
    aboutButton.setOnClickListener(this);
    View exitBotton = findViewById(R.id.exit_button);
    exitBotton.setOnClickListener(this);
  }
  @Override
  public void onClick(View v) {
    switch (v.getId()){
    case R.id.about_button:
       Intent i = new Intent(Sudoku.this,About.class);
       startActivity(i);
       break;
    
    case R.id.new_button:
      openNewGameDialog();
      break;
    }
   }
  private void openNewGameDialog() {
    new AlertDialog.Builder(this)
      .setTitle(R.string.new_game_title)
      .setItems(R.array.difficulty,new DialogInterface.OnClickListener(){
        public void onClick(DialogInterface dialoginterface ,int i){
    
      startGame(i);
        }
    })
    .show();
    }
  private void startGame(int i){
     Log.d(TAG,"clicked on" + i);
     //ここでゲームを開始する
  }
    
   
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.sudoku, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
