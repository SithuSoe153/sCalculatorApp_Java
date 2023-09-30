package com.uog.sca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
//add for JS
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_Result,tv_New;
    MaterialButton btn_AC,btn_Percent;
    MaterialButton btn_Divide,btn_Multiply,btn_Minus,btn_Plus,btn_Equal;
    MaterialButton btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    MaterialButton btn_C,btn_Dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_Result = findViewById(R.id.tv_Result);
        tv_New = findViewById(R.id.tv_New);

        autoID(btn_C,R.id.btn_C);
        autoID(btn_Percent,R.id.btn_Percent);
        autoID(btn_Divide,R.id.btn_Divide);
        autoID(btn_Multiply,R.id.btn_Multiply);
        autoID(btn_Plus,R.id.btn_Plus);
        autoID(btn_Minus,R.id.btn_Minus);
        autoID(btn_Equal,R.id.btn_Equal);
        autoID(btn_0,R.id.btn_0);
        autoID(btn_1,R.id.btn_1);
        autoID(btn_2,R.id.btn_2);
        autoID(btn_3,R.id.btn_3);
        autoID(btn_4,R.id.btn_4);
        autoID(btn_5,R.id.btn_5);
        autoID(btn_6,R.id.btn_6);
        autoID(btn_7,R.id.btn_7);
        autoID(btn_8,R.id.btn_8);
        autoID(btn_9,R.id.btn_9);
        autoID(btn_AC,R.id.btn_AC);
        autoID(btn_Dot,R.id.btn_Dot);

    }


    void autoID(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        MaterialButton button =(MaterialButton) view;
        String btn_Text = button.getText().toString();
        String cal_Data = tv_New.getText().toString();


        if(btn_Text.equals("AC")){
            tv_New.setText("0");
            tv_Result.setText("0");
            return;
        }

        if(btn_Text.equals("=")){
            tv_New.setText(tv_Result.getText());
            return;
        }

        if(btn_Text.equals("C")){
            if ( cal_Data.length()==1){
                tv_New.setText("0");
                tv_Result.setText("0");
                return;

            }else{
                cal_Data = cal_Data.substring(0,cal_Data.length()-1);
            }
        }
        else{
            if (cal_Data.startsWith("0")){
                cal_Data = cal_Data.substring(0,cal_Data.length()-1);
            }
            cal_Data = cal_Data+btn_Text;
        }

        tv_New.setText(cal_Data);

        String finalResult = getResult(cal_Data);

        if(!finalResult.equals("Err")){
            tv_Result.setText(finalResult);
        }


    }


    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}