package com.ybj.remoteviewsdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
                //为了在不同版本下创建和使用兼容的通知
                NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(getApplicationContext());
                //设置大图标，不设置时默认为应用图标
                notificationCompat.setLargeIcon(bitmap);
                //设置小图标
                notificationCompat.setSmallIcon(R.drawable.main_pic);
                //设置颜色
                notificationCompat.setColor(getResources().getColor(R.color.colorAccent));
                //setVibrate();设置整栋
                //设置闪灯 setLight(int,int,int) 第一个int为颜色的资源id,第二个和第三个是闪灯开启和关闭的时间,单位为毫秒
                //设置声音 setSound
                notificationCompat.setTicker("我是一两");
                notificationCompat.setContentText("设置文字内容");
                notificationCompat.setWhen(System.currentTimeMillis());
                notificationCompat.setContentTitle("我是内容标题");
                //自定义RemoteViews
                //notificationCompat.setContent("我是主内容");
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification build = notificationCompat.build();

                NotificationManagerCompat.from(getApplicationContext())
                        .notify(0,build);

            }
        });

    }
}
