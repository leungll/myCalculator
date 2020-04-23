import java.util.HashMap;
import java.util.Stack;

/**
 * @Author:liangll
 * @Description:
 * @Date: 0:05 2019/3/18
 */
public class Calculator {
    public int NBL(String str){
        //放数字
        Stack<Integer> numStack = new Stack<>();
        //放操作符
        Stack<String> operatorStack = new Stack<>();
        //存放运算符优先级
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("(", 0);
        hashMap.put("+", 1);
        hashMap.put("-", 1);
        hashMap.put("*", 2);
        hashMap.put("÷", 2);

        String formula = str.replaceAll(" ", "");

        for (int i = 0; i < formula.length();) {
            //StringBuilder类中的方法主要偏重于对于字符串的变化，例如追加、插入和删除等，这个也是StringBuffer和String类的主要区别。
            StringBuilder digit = new StringBuilder();
            //将式子字符串切割为c字符
            char c = formula.charAt(i);
            //判断字符是否为10进制数字,将一个数加入digit
            while (Character.isDigit(c)) {
                digit.append(c);
                i++;
                if (i < formula.length()){
                    c = formula.charAt(i);
                }else {
                    break;
                }
            }
            //当前digit里面已经无数字，即当前处理符号
            if (digit.length() == 0){
                switch (c) {
                    case '(': {
                        //如果是(转化为字符串压入字符栈
                        operatorStack.push(String.valueOf(c));
                        break;
                    }
                    //遇到右括号了计算，因为（的优先级最高
                    case ')': {
                        //如果是)，将符号栈栈顶元素取到
                        String stmp = operatorStack.pop();
                        //当前符号栈里面还有+ - * /
                        while (!operatorStack.isEmpty() && !stmp.equals("(")) {
                            //取操作数a,b
                            int a = numStack.pop();
                            int b = numStack.pop();
                            //计算
                            int result = calculate(b, a, stmp);
                            if(result < 0){
                                return  -1;
                            }
                            //将结果压入栈
                            numStack.push(result);
                            //符号指向下一个计算符号
                            stmp = operatorStack.pop();
                        }
                        break;
                    }
                    //遇到等号了计算
                    case '=': {
                        String stmp;
                        //当前符号栈里面还有+ - * /,即还没有算完
                        while (!operatorStack.isEmpty()) {
                            stmp = operatorStack.pop();
                            int a = numStack.pop();
                            int b = numStack.pop();
                            int result = calculate(b, a, stmp);
                            if(result < 0)
                                return  -1;
                            numStack.push(result);
                        }
                        break;
                    }
                    default: {  //不满足之前的任何情况
                        String stmp;
                        //如果符号栈有符号
                        while (!operatorStack.isEmpty()) {
                            //当前符号栈，栈顶元素
                            stmp = operatorStack.pop();
                            //比较优先级
                            if (hashMap.get(stmp) >= hashMap.get(String.valueOf(c))) {
                                int a = numStack.pop();
                                int b = numStack.pop();
                                int result =calculate (b, a, stmp);
                                if(result < 0)
                                    return  -1;
                                numStack.push(result);
                            }
                            else {
                                operatorStack.push(stmp);
                                break;
                            }
                        }
                        //将符号压入符号栈
                        operatorStack.push(String.valueOf(c));
                        break;
                    }
                }
            }
            else { //处理数字，直接压栈
                //Integer.valueof()返回的是Integer对象，而Integer.parseInt()返回的是int型
                numStack.push(Integer.valueOf(digit.toString()));
                //结束本次循环，回到for语句进行下一次循环，即不执行i++(因为此时i已经指向符号了)
                continue;
            }
            i++;
        }
        //返回栈底数字即等式的答案。
        return numStack.peek();
    }

    /**
     * 计算a stmp b的值
     * @param a
     * @param b
     * @param stmp
     * @return
     */
    private int calculate(int a, int b, String stmp) {
        //存结果
        int res = 0;
        char s = stmp.charAt(0);
        switch (s) {
            case '+': {
                res = a + b;
                break;
            }
            case '-': {
                //产生负数就不合格
                res = a - b;
                break;
            }
            case '*': {
                res = a * b;
                break;
            }
            case '÷': {
                if(b==0) {
                    return -1;
                } else if(a%b!=0) {
                    //产生小数就不合格
                    return -2;
                }
                else {
                    res = a / b;
                }
                break;
            }default:
        }
        return res;
    }
}
