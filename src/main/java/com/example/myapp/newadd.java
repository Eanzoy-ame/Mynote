package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class newadd extends AppCompatActivity {
    EditText et1,et2;
    MyDataBase myDataBase;
    cuns cuns;
    int ids;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_add);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        myDataBase=new MyDataBase(this);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab1);
        Intent intent=this.getIntent();
        ids=intent.getIntExtra("ids", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if(ids!=0){
            cuns=myDataBase.getTiandCon(ids);
            et1.setText(cuns.getTitle());
            et2.setText(cuns.getContent());
        }
        //点击悬浮按钮保存
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

    }
    //调用返回按钮
    public void onBackPressed() {
        //super.onBackPressed();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title=et1.getText().toString();
        String content=et2.getText().toString();
        //修改数据
        if(ids!=0){
            cuns=new cuns(title,ids, content, times);
            myDataBase.toUpdate(cuns);
            Intent intent=new Intent(newadd.this,MainActivity.class);
            startActivity(intent);
            newadd.this.finish();
        }
        //新建
        else{
            if(title.equals("")&&content.equals("")){
                Intent intent=new Intent(newadd.this,MainActivity.class);
                startActivity(intent);
                newadd.this.finish();
            }
            else{
                cuns=new cuns(title,content,times);
                myDataBase.toInsert(cuns);
                Intent intent=new Intent(newadd.this,MainActivity.class);
                startActivity(intent);
                newadd.this.finish();
            }

        }
    }
    //调用save方法
    private void Save(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String times = formatter.format(curDate);
        String title=et1.getText().toString();
        String content=et2.getText().toString();
        //修改数据
        if(ids!=0){
            cuns=new cuns(title,ids, content, times);
            myDataBase.toUpdate(cuns);
            Intent intent=new Intent(newadd.this,MainActivity.class);
            startActivity(intent);
            newadd.this.finish();
        }
        //新建日记
        else{
            cuns=new cuns(title,content,times);
            myDataBase.toInsert(cuns);
            Intent intent=new Intent(newadd.this,MainActivity.class);
            startActivity(intent);
            newadd.this.finish();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    //菜单的分享功能
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        "标题："+et1.getText().toString()+"    " +
                                "内容："+et2.getText().toString());
                startActivity(intent);
                break;

            default:
                break;
        }
        return false;
    }
}

