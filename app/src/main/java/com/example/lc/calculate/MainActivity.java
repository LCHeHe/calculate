package com.example.lc.calculate;
import android.content.DialogInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog.Builder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    DecimalFormat ql = new DecimalFormat("###################.###########");
    Button[] n = new Button[10];
    Button[] fu_hao = new Button[4];

    final double[] s = {0,0};  // s[0]小数的整数部分   s[1]： 小数部分的尾数
    final int[] n_time = {0,0};  // n_time[0]：判断是否作出运算    n_time[1]：小数点位数
    final int[] select = {0,0};  // select[0]：运算符选择   select[1]：判断小数点
    final double[] h = {0,0};    // h[0]：所有部分          h[1]：小数部分
    final double[] f = {0};      // 过渡数

    final ArrayList<Double> number = new ArrayList<>();           //存数字
    final ArrayList<String> fuHao = new ArrayList<>();            //存符号
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0;i<=3;i++)//初始化运算符
        {
            fu_hao[i] = new Button(this);
        }

        for(int i = 0;i<=9;i++)//初始化0~9
        {
            n[i] = new Button(this);
        }

        final TextView jieGuo = (TextView)findViewById(R.id.outPut);  //结果显示

        fu_hao[0] = (Button)findViewById(R.id.add);          //加
        fu_hao[1] = (Button)findViewById(R.id.minus);        //减
        fu_hao[2] = (Button)findViewById(R.id.multiply);     //乘
        fu_hao[3] = (Button)findViewById(R.id.divide);       //除
        Button Sin = (Button)findViewById(R.id.sin);         //sin
        Button Cos = (Button)findViewById(R.id.cos);         //cos
        Button shan = (Button)findViewById(R.id.delete);     //退格键
        Button deng = (Button)findViewById(R.id.equal);      //等号
        Button C = (Button)findViewById(R.id.clean);         //清零
        Button change1 = (Button) findViewById(R.id.change_jinzhi); //进制转换
        Button change2 = (Button) findViewById(R.id.change_danwei); //单位转换

        Button dian = (Button)findViewById(R.id.point);      //小数点
        n[0] = (Button)findViewById(R.id.number0);           //0~9
        n[1] = (Button)findViewById(R.id.number1);
        n[2] = (Button)findViewById(R.id.number2);
        n[3] = (Button)findViewById(R.id.number3);
        n[4] = (Button)findViewById(R.id.number4);
        n[5] = (Button)findViewById(R.id.number5);
        n[6] = (Button)findViewById(R.id.number6);
        n[7] = (Button)findViewById(R.id.number7);
        n[8] = (Button)findViewById(R.id.number8);
        n[9] = (Button)findViewById(R.id.number9);

        for (int i = 0;i<=3;i++)  //加减乘除
        {
            final int I = i;
            fu_hao[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    select[0] = I;
                    if( n_time[0] < 2){
                        switch (select[0]){
                        case 0:
                            fuHao.add("+");
                            break;
                        case 1:
                            fuHao.add("-");
                            break;
                        case 2:
                            fuHao.add("*");
                            break;
                        case 3:
                            fuHao.add("/");
                            break;
                            }
                        number.add(f[0]);
                    }
                    select[1] =0;
                    h[0] = 0;
                    h[1] = 0;
                    n_time[0] = 2;
                    n_time[1] = 0;
                }
            });
        }

        deng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number.add(f[0]);
                n_time[0] = 1;
                for (int i = 0; i < fuHao.size(); i++) {
                    if (fuHao.get(i).contains("*") || fuHao.get(i).contains("/")) {
                        String o = fuHao.remove(i);
                        if (o.equals("*")) {
                            double d1 = number.remove(i);
                            double d2 = number.remove(i);
                            double guoCheng = d1 * d2;
                            number.add(i, guoCheng);
                        }
                        if (o.equals("/")) {
                            double d1 = number.remove(i);
                            double d2 = number.remove(i);
                            double guoCheng = d1 / d2;
                            number.add(i, guoCheng);
                        }
                        i--;
                    }
                }
                while (fuHao.size() != 0) {
                    String o = fuHao.remove(0);
                    double d1 = number.remove(0);
                    double d2 = number.remove(0);
                    if (o.equals("+")) {
                        double guoCheng = d1 + d2;
                        number.add(0, guoCheng);
                    }
                    if (o.equals("-")) {
                        double guoCheng = d1 - d2;
                        number.add(0, guoCheng);
                    }
                }
                f[0] = number.remove(0);
                jieGuo.setText("" + ql.format(f[0]));
                h[0] = 0;
            }
        });

        Sin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                n_time[0] +=1;
                jieGuo.setText("" + Math.sin(f[0]));
                f[0] = Math.sin(f[0]);
            }
        });

        Cos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                n_time[0] +=1;
                jieGuo.setText("" + Math.cos(f[0]));
                f[0] = Math.cos(f[0]);
            }
        });

        shan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(n_time[0]>=1) {
                    jieGuo.setText("请不要对结果作出修改，清零吧！！！");
                    h[0] = 0;
                    h[1] = 0;
                }
                else if(n_time[0]<1 && select[1]!=1)
                    {
                    h[0] = (h[0] - h[0] % 10) / 10;
                    jieGuo.setText("" + ql.format(h[0]));
                    f[0] = Double.parseDouble(jieGuo.getText().toString());
                }
                else
                {
                    h[0] = (h[0] * Math.pow(10,n_time[1]));
                    h[0] = (h[0] - h[0] % 10) / 10;
                    h[1] = (h[1] * Math.pow(10,n_time[1]));
                    h[1] = (h[1] - h[1] % 10) / 10;
                    n_time[1] -= 1;
                    h[0] = h[0] / Math.pow(10,n_time[1]);
                    h[1] = h[1] / Math.pow(10,n_time[1]);
                    jieGuo.setText("" + h[0]);
                    f[0] = Double.parseDouble(jieGuo.getText().toString());
                }
            }
        });

        for (int i = 0;i<=9;i++)//0~9
        {
            final int I = i;
            n[i].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    n_time[0] = 0;
                    double aId = I;
                    if(select[1]== 0) {
                        h[0] = h[0] * 10 + aId;
                        jieGuo.setText("" + ql.format(h[0]));
                        f[0] = Double.parseDouble(jieGuo.getText().toString());
                    }
                    else {
                        n_time[1] += 1;
                        s[1] = aId/ Math.pow(10,n_time[1]);
                        h[1] = h[1] + s[1];
                        h[0] = s[0] + h[1];
                        jieGuo.setText("" + h[0]);
                        f[0] = Double.parseDouble(jieGuo.getText().toString());
                    }
                }
            });
        }

        dian.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                s[0] = f[0];
                if(select[1]!=1 && n_time[0] < 1)
                {
                    jieGuo.setText("" + ql.format(h[0]) + ".");
                    select[1] = 1;
                }
                else if(n_time[0]>=1) {
                    jieGuo.setText("请不要对结果作出修改，清零吧！！！");
                    h[0] = 0;
                }
                else
                    jieGuo.setText("不要点两下小数点");
            }
        });

        C.setOnClickListener(new View.OnClickListener() {  //清零
            public void onClick(View v) {
                clearall();
                number.clear();
                fuHao.clear();
                jieGuo.setText("本计算器结果需按等号查看,初始结果默认为零");
            }
        });

        change1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Builder builder=new Builder(MainActivity.this);
                builder.setTitle("进制转换");
                builder.setSingleChoiceItems(R.array.进制, 0, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        String hoddy=getResources().getStringArray(R.array.进制)[which];
                        switch(hoddy)
                        {
                            case "十转二": jieGuo.setText("" + Integer.toBinaryString((int) f[0]));break;
                            case "十转八": jieGuo.setText("" + Integer.toOctalString((int) f[0]));break;
                            case "十转十六": jieGuo.setText("" + Integer.toHexString((int)f[0]));break;
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        clearall();
                        number.clear();
                        fuHao.clear();
                    }
                });
                builder.create();
                builder.show();
            }
        });

        change2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Builder builder=new Builder(MainActivity.this);
                builder.setTitle("单位换算");
                builder.setSingleChoiceItems(R.array.单位, 0, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        String hoddy=getResources().getStringArray(R.array.单位)[which];
                        switch(hoddy)
                        {
                            case "英寸->厘米": jieGuo.setText("" + ql.format(f[0]*2.3));break;
                            case "厘米->英寸": jieGuo.setText("" + ql.format(f[0]/2.3));break;
                            case "英尺->厘米": jieGuo.setText("" + ql.format(f[0]*30.48));break;
                            case "厘米->英尺": jieGuo.setText("" + ql.format(f[0]/30.48));break;
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                        clearall();
                        number.clear();
                        fuHao.clear();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
    public void clearall()
    {
        n_time[0] = 0;
        n_time[1] = 0;
        select[0] = 0;
        select[1] = 0;
        h[0] = 0;
        h[1] = 0;
        s[0] = 0;
        s[1] = 0;
        f[0] = 0;
    }
}