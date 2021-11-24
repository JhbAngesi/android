package cn.student.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity<EditTex> extends AppCompatActivity implements View.OnClickListener{
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_point;
    private Button button_clear;
    private Button button_plus;
    private Button button_minus;
    private Button button_multply;
    private Button button_divide;
    private Button button_equal;
    private Button button_zs;
    private TextView text_result;
    boolean div0 = false;//限制除数不为0
    boolean point0 = false;//限制不能重复点击小数点
    char last = ' ';


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_0 = (Button) findViewById(R.id.button_0);
        button_1 = (Button) findViewById(R.id.button_1);
        button_2 = (Button) findViewById(R.id.button_2);
        button_3 = (Button) findViewById(R.id.button_3);
        button_4 = (Button) findViewById(R.id.button_4);
        button_5 = (Button) findViewById(R.id.button_5);
        button_6 = (Button) findViewById(R.id.button_6);
        button_7 = (Button) findViewById(R.id.button_7);
        button_8 = (Button) findViewById(R.id.button_8);
        button_9 = (Button) findViewById(R.id.button_9);
        button_point = (Button) findViewById(R.id.button_point);
        button_clear = (Button) findViewById(R.id.button_clear);
        button_plus = (Button) findViewById(R.id.button_plus);
        button_minus = (Button) findViewById(R.id.button_minus);
        button_multply = (Button) findViewById(R.id.button_multply);
        button_divide = (Button) findViewById(R.id.button_divide);
        button_equal = (Button) findViewById(R.id.button_equal);
        button_zs = (Button) findViewById(R.id.button_zs);
        text_result = (TextView) findViewById(R.id.text_result);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_point.setOnClickListener(this);
        button_clear.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_multply.setOnClickListener(this);
        button_divide.setOnClickListener(this);
        button_equal.setOnClickListener(this);
        button_zs.setOnClickListener(this);

    }
    //Activity本身作为事件监听器
    @Override
    public void onClick(View view) {
        String lasttext;
        Formulautil formula = new Formulautil();
        switch (view.getId()){
            case R.id.button_0:
                if(div0==true){
                    Toast.makeText(MainActivity.this,"除数不能为0！",Toast.LENGTH_SHORT).show();
                }
                else{
                    text_result.setText(text_result.getText()+"0");
                }
                break;
            case R.id.button_1:
                last = '1';
                text_result.setText(text_result.getText()+"1");
                div0=false;
                break;
            case R.id.button_2:
                last = '2';
                text_result.setText(text_result.getText()+"2");
                div0=false;
                break;
            case R.id.button_3:
                last = '3';
                text_result.setText(text_result.getText()+"3");
                div0=false;
                break;
            case R.id.button_4:
                last = '4';
                text_result.setText(text_result.getText()+"4");
                div0=false;
                break;
            case R.id.button_5:
                last = '5';
                text_result.setText(text_result.getText()+"5");
                div0=false;
                break;
            case R.id.button_6:
                last = '6';
                text_result.setText(text_result.getText()+"6");
                div0=false;
                break;
            case R.id.button_7:
                last = '7';
                text_result.setText(text_result.getText()+"7");
                div0=false;
                break;
            case R.id.button_8:
                last = '8';
                text_result.setText(text_result.getText()+"8");
                div0=false;
                break;
            case R.id.button_9:
                last = '9';
                text_result.setText(text_result.getText()+"9");
                div0=false;
                break;
            case R.id.button_point:
                last = '.';
                if(point0==false){
                    text_result.setText(text_result.getText()+".");
                }
                else{
                    Toast.makeText(MainActivity.this,"请勿重复点击小数点",Toast.LENGTH_SHORT).show();
                }
                div0=false;
                point0=true;
                break;

            case R.id.button_clear:
                text_result.setText("");
                div0=false;
                point0=false;
                break;

            case R.id.button_plus:
                if(last=='+'||last=='-'||last=='*'||last=='/'||last=='^'){
                    Toast.makeText(MainActivity.this,"请勿重复点击运算符",Toast.LENGTH_SHORT).show();
                }
                else{
                    last = '+';
                    text_result.setText(text_result.getText()+"+");
                    point0=false;
                }
                break;
            case R.id.button_minus:
                if(last=='+'||last=='-'||last=='*'||last=='/'||last=='^'){
                    Toast.makeText(MainActivity.this,"请勿重复点击运算符",Toast.LENGTH_SHORT).show();
                }
                else{
                    last = '-';
                    text_result.setText(text_result.getText()+"-");
                    point0=false;
                }
                break;
            case R.id.button_multply:
                if(last=='+'||last=='-'||last=='*'||last=='/'||last=='^'){
                    Toast.makeText(MainActivity.this,"请勿重复点击运算符",Toast.LENGTH_SHORT).show();
                }
                else{
                    last = '*';
                    text_result.setText(text_result.getText()+"*");
                    point0=false;
                }

                break;
            case R.id.button_divide:
                if(last=='+'||last=='-'||last=='*'||last=='/'||last=='^'){
                    Toast.makeText(MainActivity.this,"请勿重复点击运算符",Toast.LENGTH_SHORT).show();
                }
                else{
                    last = '/';
                    text_result.setText(text_result.getText()+"/");
                    point0=false;
                    div0=true;
                }

                break;
            case R.id.button_equal:
                text_result.setText(text_result.getText()+"=");
                lasttext = text_result.getText().toString();
                String formula1 = String.valueOf(formula.caculate(lasttext));
                text_result.setText(formula1);
                point0=false;
                break;
            case R.id.button_zs:
                if(last=='+'||last=='-'||last=='*'||last=='/'||last=='^'){
                    Toast.makeText(MainActivity.this,"请勿重复点击运算符",Toast.LENGTH_SHORT).show();
                }
                else{
                    last = '^';
                    text_result.setText(text_result.getText()+"^");
                    point0 = false;
                }

                break;
        }
    }

}