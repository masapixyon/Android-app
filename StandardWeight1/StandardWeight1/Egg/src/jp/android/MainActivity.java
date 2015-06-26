package jp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  private int count=100;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.egg_main);
  }   

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
  }

  public void plus(View view) {
    
    TextView countView = (TextView) findViewById(R.id.textView);
    if(count>1){
    count--;
    countView.setText(String.valueOf(count));
    
    }else{
      count=0;
      countView.setText(String.valueOf(count));
      
      //BornActivityへの憑依
      Intent intent = new Intent(MainActivity.this,BornActivity.class);
      startActivity(intent);
  
    }
  }
}
