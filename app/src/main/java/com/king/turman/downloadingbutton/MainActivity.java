package com.king.turman.downloadingbutton;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.turman.downloadingbutton.proxy.Operate;
import com.king.turman.downloadingbutton.proxy.OperateImpl;
import com.king.turman.downloadingbutton.proxy.TimingInvocationHandler;
import com.king.turman.library.CoreView;
import com.king.turman.library.DownloadButton;
import com.king.turman.library.WaveView;
import com.king.turman.library.block.BlockView;

import java.lang.reflect.Proxy;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

//    private WaveView mWaveView;
//    private WaveView mWaveView1;

    private int[] colors = {R.color.white,R.color.ivory,R.color.lightyellow,
            R.color.yellow,R.color.snow,R.color.floralwhite,
            R.color.lemonchiffon,R.color.cornsilk,R.color.seashell,
            R.color.lavenderblush,R.color.papayawhip, R.color.blanchedalmond,
            R.color.mistyrose,
            R.color.bisque,
            R.color.moccasin,
            R.color.navajowhite,
            R.color.peachpuff,
            R.color.gold,
            R.color.pink,
            R.color.lightpink,
            R.color.orange,
            R.color.lightsalmon,
            R.color.darkorange,
            R.color.coral,
            R.color.hotpink,
            R.color.tomato,
            R.color.orangered,
            R.color.deeppink,
            R.color.fuchsia,
            R.color.red,
            R.color.oldlace,
            R.color.lightgoldenrodyellow,
            R.color.linen,
            R.color.antiquewhite,
            R.color.salmon,
            R.color.ghostwhite,
            R.color.mintcream,
            R.color.whitesmoke,
            R.color.beige,
            R.color.wheat,
            R.color.sandybrown,
            R.color.azure,
            R.color.honeydew,
            R.color.aliceblue,
            R.color.khaki,
            R.color.lightcoral,
            R.color.palegoldenrod,
            R.color.violet,
            R.color.darksalmon,
            R.color.lavender,
            R.color.lightcyan,
            R.color.burlywood,
            R.color.plum,
            R.color.gainsboro,
            R.color.crimson,
            R.color.palevioletred,
            R.color.goldenrod,
            R.color.orchid,
            R.color.thistle,
            R.color.lightgray,
            R.color.tan,
            R.color.chocolate,
            R.color.peru,
            R.color.indianred,
            R.color.mediumvioletred,
            R.color.silver,
            R.color.darkkhaki,
            R.color.rosybrown,
            R.color.mediumorchid,
            R.color.darkgoldenrod,
            R.color.firebrick,
            R.color.powderblue,
            R.color.lightsteelblue,
            R.color.paleturquoise,
            R.color.greenyellow,
            R.color.lightblue,
            R.color.darkgray,
            R.color.brown,
            R.color.sienna,
            R.color.darkorchid,
            R.color.palegreen,
            R.color.darkviolet,
            R.color.mediumpurple,
            R.color.lightgreen,
            R.color.darkseagreen,
            R.color.saddlebrown,
            R.color.darkmagenta,
            R.color.darkred,
            R.color.blueviolet,
            R.color.lightskyblue,
            R.color.skyblue,
            R.color.gray,
            R.color.olive,
            R.color.purple,
            R.color.maroon,
            R.color.aquamarine,
            R.color.chartreuse,
            R.color.lawngreen,
            R.color.mediumslateblue,
            R.color.lightslategray,
            R.color.slategray,
            R.color.olivedrab,
            R.color.slateblue,
            R.color.dimgray,
            R.color.mediumaquamarine,
            R.color.cornflowerblue,
            R.color.cadetblue,
            R.color.darkolivegreen,
            R.color.indigo,
            R.color.mediumturquoise,
            R.color.darkslateblue,
            R.color.steelblue,
            R.color.royalblue,
            R.color.turquoise,
            R.color.mediumseagreen,
            R.color.limegreen,
            R.color.darkslategray,
            R.color.seagreen,
            R.color.forestgreen,
            R.color.lightseagreen,
            R.color.dodgerblue,
            R.color.midnightblue,
            R.color.aqua,
            R.color.cyan,
            R.color.springgreen,
            R.color.lime,
            R.color.mediumspringgreen,
            R.color.darkturquoise,
            R.color.deepskyblue,
            R.color.darkcyan,
            R.color.teal,
            R.color.green,
            R.color.darkgreen,
            R.color.blue,
            R.color.mediumblue,
            R.color.darkblue,
            R.color.navy,
            R.color.black,
            R.color.grassgreen,
            R.color.gray_cc,
            R.color.gray_8f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        final double[] currentPercent = {0.0};
//        final DownloadButton db = (DownloadButton) findViewById(R.id.loading_btn);
//        final Handler handler = new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                db.setCurrentPercent(currentPercent[0]);
//            }
//        };
//
//        Timer timer = new Timer(true);
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                currentPercent[0] += 0.05;
//                handler.sendEmptyMessage(0);
//            }
//        };
//
//        timer.schedule(timerTask,500,200);


//        mWaveView = (WaveView) findViewById(R.id.wave_view);
//        mWaveView.setDuration(5000);
//        mWaveView.setStyle(Paint.Style.FILL);
//        mWaveView.setColor(Color.RED);
//        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
//        mWaveView.start();
//
//        mWaveView1 = (WaveView) findViewById(R.id.wave_view_1);
//        mWaveView1.setDuration(5000);
//        mWaveView1.setStyle(Paint.Style.STROKE);
//        mWaveView1.setSpeed(400);
//        mWaveView1.setColor(Color.RED);
//        mWaveView1.setInterpolator(new AccelerateInterpolator(1.2f));
//        mWaveView1.start();


//        mWaveView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mWaveView.stop();
//            }
//        }, 10000);


//        TextView textView = (TextView) findViewById(R.id.tv);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.base1);
//        animation.setRepeatCount(Animation.INFINITE);     //无效
//        textView.startAnimation(animation);

//        textView.setOnClickListener(v -> Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show());


//        他妈的不执行
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.base2);
//        set.setTarget(textView);
//        set.start();


//        ObjectAnimator ob1 = ObjectAnimator.ofFloat(textView,"x",400).setDuration(500);
//        ob1.setRepeatCount(ValueAnimator.INFINITE);
//        ob1.setRepeatMode(ValueAnimator.REVERSE);
//        ObjectAnimator ob2 = ObjectAnimator.ofFloat(textView,"y",300).setDuration(500);
//        ob2.setRepeatCount(ValueAnimator.INFINITE);
//        ob2.setRepeatMode(ValueAnimator.REVERSE);
//        AnimatorSet set1 = new AnimatorSet();
//        set1.playTogether(ob1,ob2);
//
//        ObjectAnimator ob3 = ObjectAnimator.ofObject(textView,"alpha", new MyFloatEvaluate() ,1.0f,0.2f).setDuration(500);
//        ob3.setRepeatCount(ValueAnimator.INFINITE);
//        ob3.setRepeatMode(ValueAnimator.REVERSE);
//
//        AnimatorSet set2 = new AnimatorSet();
//
//        set2.playSequentially(ob3);
//        set2.setDuration(1000);
//        set2.setInterpolator(new AccelerateDecelerateInterpolator());
//        set2.start();

        

//        TimingInvocationHandler timingInvocationHandler = new TimingInvocationHandler(new OperateImpl());
//
//        Operate operate = (Operate)(Proxy.newProxyInstance(Operate.class.getClassLoader(), new Class[] {Operate.class}, timingInvocationHandler));
//        operate.operateMethod1();
//        System.out.println();
//        operate.operateMethod2();
//        System.out.println();
//        operate.operateMethod3();

        ViewConfiguration.get(getApplication()).getScaledTouchSlop();


        CoreView coreView = (CoreView) findViewById(R.id.coreview);


        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        for (int i=0;i<10;i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            for (int j=0;j<10;j++) {
                int index = (int) (Math.random() * colors.length);
                BlockView blockView = new BlockView(this, getResources().getColor(colors[index]),new Point(0,0), 40);
                linearLayout.addView(blockView);
            }
            rootLayout.addView(linearLayout);
        }

//        getPackageManager().resolveActivity(new Intent(),PackageManager.MATCH_DEFAULT_ONLY);
//        getPackageManager().queryIntentActivities(new Intent(),PackageManager.MATCH_DEFAULT_ONLY);
//        getPackageManager().resolveService(new Intent(), PackageManager.MATCH_DEFAULT_ONLY);


        findViewById(R.id.tv).setOnClickListener(v->{
            TestClass.name = "Tom";
            Toast.makeText(this, TestClass.name, Toast.LENGTH_SHORT).show();
            ((BaseApplication)getApplication()).setCurrent_process(100);
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });


        //绑定服务
        Intent intentService = new Intent("com.king.turman.downloadingbutton.WorkingService");
        intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MainActivity.this.bindService(intentService, serviceConnection, BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        if (mIMyService != null) {
            unbindService(serviceConnection);
        }
        super.onDestroy();
    }

    private IMyService mIMyService;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyService = IMyService.Stub.asInterface(service);
            try {
                Book book = mIMyService.getBook().get(0);
                Toast.makeText(MainActivity.this,book.getName(),Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyService = null;
        }
    };
}

































