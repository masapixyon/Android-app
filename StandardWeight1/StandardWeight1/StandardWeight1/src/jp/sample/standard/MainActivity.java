package jp.sample.standard;


import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    
    //ボタンを取得
    View btn = findViewById(R.id.btn);
    btn.setOnClickListener(this);
  }
  @Override
  public void onClick(View v) {

      //オブジェクト取得
      EditText editHeight = (EditText)findViewById(R.id.Input);    

      TextView textNormalWeight = (TextView)findViewById(R.id.output);
      
      //内容取得
      String strHeight = editHeight.getText().toString();     
      
      //数値に変換
      double height = Double.parseDouble(strHeight);      
      double normalWeight = 22.0*Math.pow((height/100.0), 2);
      //double型の桁数フォーマット
      DecimalFormat df1= new DecimalFormat("0.00000");
      textNormalWeight.setText(df1.format(normalWeight));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }
}
