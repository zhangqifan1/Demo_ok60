package com.as.demo_ok60;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView open = findViewById(R.id.open);
        final ImageView close = findViewById(R.id.close);
        final ImageView xin = findViewById(R.id.xin);
        final ImageView xinfeng = findViewById(R.id.xinfeng);

        // open 和 close  的XML 高度为 80dp
        final int y = dip2px(this, 80);
        close.setPivotY(0);
        open.setPivotY(y);

        xinfeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //首先是翻开  close
                ObjectAnimator scaleY = ObjectAnimator
//                        .ofFloat(close,"rotationX",0,90)
                        .ofFloat(close, "scaleY", 1, 0)
                        .setDuration(1000);
                scaleY.start();
                scaleY.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        //结束时  隐藏close图片  显示 open图片
                        close.setVisibility(View.GONE);
                        open.setVisibility(View.VISIBLE);


                        ObjectAnimator s1 = ObjectAnimator
//                                .ofFloat(open, "rotationX", 90, 0)
                                .ofFloat(open, "scaleY", 0, 1)
                                .setDuration(3000);
                        s1.start();

                        //这里可以是结束时的动画也可以是 同时
                        ObjectAnimator s2 = ObjectAnimator
                                .ofFloat(xin, "translationY", 0, -200)
                                .setDuration(3000);

                        AnimatorSet group = new AnimatorSet();
                        group.play(s1)
                                .with(s2);
                        group.start();



                        //结束时从信封向上移
//                        s1.addListener(new Animator.AnimatorListener() {
//                            @Override
//                            public void onAnimationStart(Animator animator) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animator animator) {
//                                ObjectAnimator translationY = ObjectAnimator
//                                        .ofFloat(xin, "translationY", 0, -200)
//                                        .setDuration(1000);
//                                translationY.start();
//                            }
//
//                            @Override
//                            public void onAnimationCancel(Animator animator) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animator animator) {
//
//                            }
//                        });

                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });


            }
        });


    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
