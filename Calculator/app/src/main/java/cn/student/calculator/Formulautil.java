package cn.student.calculator;

import android.util.Log;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.Stack;

public class Formulautil {
    private Stack<BigDecimal> numberStack = null;//数字栈
    private Stack<Character> symbolStack = null;//符号栈
    private int scale;//除法时出现循环小数保留精度
    public Formulautil(int scale){
        super();
        this.scale = scale;
    }
    public Formulautil(){
        this(20);
    }

    //计算运算式
    public BigDecimal caculate(String numStr){
        if(numStr.charAt(0)=='+'||numStr.charAt(0)=='-'||numStr.charAt(0)=='*'||numStr.charAt(0)=='/'){
            numStr="0"+numStr;
        }
        //判断算数表示是否结束，也就是判断结尾有木有=号！没有给加上！
        //equals方法：值的比较
        //charAt方法：检索方法
        if (numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "=";
        }
        //检查表达式是否是正确的！利用Standard方法(自定义)
        if (!isStandard(numStr)) {
            return null;
        }
        // 初始化栈
        if (numberStack == null) {
            numberStack = new Stack<BigDecimal>();
        }
        numberStack.clear();
        if (symbolStack == null) {
            symbolStack = new Stack<Character>();
        }
        symbolStack.clear();


        //创建一个StringBuffer，用来放多位的数字
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            // 获取一个字符
            char ch = numStr.charAt(i);
            // 若当前字符是数字
            if (isNumber(ch)) {
                // 加入到数字缓存中
                temp.append(ch);
            }
            else { // 非数字的情况
                // 将缓存转为字符串
                String tempStr = temp.toString();
                if (!tempStr.isEmpty()) {
                    BigDecimal num = new BigDecimal(tempStr);
                    // 将数字压栈
                    numberStack.push(num);
                    // 重置数字缓存
                    temp = new StringBuffer();
                }

                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!comparePri(ch) && !symbolStack.empty()) {
                    // 出栈，取出数字，后进先出
                    BigDecimal b = numberStack.pop();
                    BigDecimal a = numberStack.pop();
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    switch ((char) symbolStack.pop()) {
                        case '+':
                            numberStack.push(a.add(b));
                            break;
                        case '-':
                            numberStack.push(a.subtract(b));
                            break;
                        case '*':
                            numberStack.push(a.multiply(b));
                            break;
                        case '/':
                            try {
                                numberStack.push(a.divide(b));
                            } catch (java.lang.ArithmeticException e) {
                                // 进行除法出现无限循环小数时，就会抛异常，此处设置精度重新计算
                                numberStack.push(a.divide(b, this.scale, BigDecimal.ROUND_HALF_EVEN));
                            }
                            break;
                        case '^':
                            numberStack.push(a.pow(b.intValue()));
                            break;
                        default:
                            break;
                    }
                } // while循环结束

                if (ch != '=') {
                    // 符号入栈
                    symbolStack.push(new Character(ch));
                }
            }
        } // for循环结束

        // 返回计算结果
        return numberStack.pop();

    }
    /**=================================检查算数表达式是否合格======================================*/
    private boolean isStandard(String numStr) {
        // 表达式不能为空
        if (numStr == null || numStr.isEmpty())
            return false;

        // 用来标记'='符号是否存在多个
        boolean b = false;
        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 判断字符是否合法
            if (!(isNumber(n) || '+'==n || '-'==n || '^'==n
                    || '*'==n || '/'==n || '='==n)) {
                return false;

            }
            // 检查是否有多个'='号
            if ('='==n) {
                if (b)
                    return false;
                b = true;
            }
        }
        // 检查'='号是否不在末尾
        if (!('='==numStr.charAt(numStr.length() - 1) ))
            return false;
        return true;
    }

    /**=================================判断是否是0-9的数字包括小数========================================*/
    private boolean isNumber(char num) {
        if ((num >= '0' && num <= '9') || num == '.')
            return true;
        return false;
    }

    /**==============比较优先级，如果当前运算符比栈顶元素运算符优先级高则返回true,否则返回false==========*/
    private boolean comparePri(char symbol) {
        // 空栈返回ture
        if (symbolStack.empty()) {
            return true;
        }
        // 查看堆栈顶部的对象
        char top = (char) symbolStack.peek();
        // 比较优先级
        switch (symbol) {
            //优先级最高
            case '^':
                return true;
            // 优先级'*'比'+'和'-'高
            case '*': {
                if (top == '+' || top == '-')
                    return true;
                else
                    return false;
            }
            // 优先级'/'比'+'和'-'高
            case '/':
                if (top == '+' || top == '-')
                    return true;
                else
                    return false;
            case '+':
                return false;
            case '-':
                return false;
            // 结束符
            case '=':
                return false;
            default:
                break;
        }
        return true;
    }
}
