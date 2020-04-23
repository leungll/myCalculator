import java.util.Random;

/**
 * @Author:liangll
 * @Description:简单四则运算(无分数版)
 * @Date: 8:41 2019/3/18
 */
public class SimpleCalculator {

    private static final String[] OPERATOR = {"+", "-", "*", "÷"};

    /**
     * 整数生成器
     * @return
     */
    public String calculatorInteger(){  //产生整数式子
        Random random = new Random();
        //随机操作符的个数（3-5个）
        int operatorCount = 3 + random.nextInt(3);
        //操作数个数，比操作符多1
        int[] operatorNum = new int[operatorCount + 1];
        //产生操作符的下标（后续为了判断操作符的重复个数）
        int[] operatorIndex = index(operatorCount, 4, random);
        for(int i = 0; i < operatorCount + 1; i++){
            operatorNum[i] = random.nextInt(101);
        }
        String str = stitchingFormula(operatorCount, operatorNum, operatorIndex);
        Calculator calculator = new Calculator();
        int answer = calculator.NBL(str);
        //判断式子是否符合要求，凡是返回负数的就是不合格的
        if(answer>=0){
            str+=answer;
        }else {
            //递归直到产生合格的式子
            return calculatorInteger();
        }
        return str;
    }

    /**
     * 随机产生操作符的下标数组
     * @param operatorCount
     * @param operatorTotal
     * @param random
     * @return
     */
    public int[] index(int operatorCount,int operatorTotal, Random random){
        int similar = 0;
        int[] operatorIndex = new int[operatorCount];
        for(int i = 0; i < operatorCount; i++){
            operatorIndex[i] = random.nextInt(operatorTotal);
        }
        for (int i : operatorIndex) {
            if(operatorIndex[0] == i) {
                similar++;
            }
        }
        //保证一个式子里至少有2个不同的操作符
        if(similar == operatorCount && operatorCount != 1){
            //若所有操作符下标都一样，则重新产生操作符下标
            return index(operatorCount, operatorTotal, random);
        } else {
            return operatorIndex;
        }
    }

    /**
     * 拼接式子
     * @param operatorCount
     * @param operatorNum
     * @param operatorIndex
     * @return
     */
    public String stitchingFormula(int operatorCount, int[] operatorNum, int[] operatorIndex){
        //式子形态
        int bracketForm = new Random().nextInt(2);
        StringBuilder formula = new StringBuilder();
        switch (operatorCount){
            case 3:
                // (1+2)*(3+4)型
                if(bracketForm == 0){
                    formula.append("(")
                            .append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[0]])
                            .append(operatorNum[1])
                            .append(")")
                            .append(OPERATOR[operatorIndex[2]])
                            .append("(")
                            .append(operatorNum[2])
                            .append(OPERATOR[operatorIndex[0]])
                            .append(operatorNum[3])
                            .append(")")
                            .append("=");
                }else{
                    //1+((2×3)-4)型
                    formula.append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[0]])
                            .append("((")
                            .append(operatorNum[1])
                            .append(OPERATOR[operatorIndex[2]])
                            .append(operatorNum[2])
                            .append(")")
                            .append(OPERATOR[operatorIndex[1]])
                            .append(operatorNum[3])
                            .append(")=");
                }break;
            case 4:{
                // (1+2)×3÷(4-1)型
                if (bracketForm == 0){
                    formula.append("(")
                            .append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[0]])
                            .append(operatorNum[1])
                            .append(OPERATOR[operatorIndex[2]])
                            .append(operatorNum[2])
                            .append(OPERATOR[operatorIndex[3]])
                            .append("(")
                            .append(operatorNum[3])
                            .append(OPERATOR[operatorIndex[1]])
                            .append(operatorNum[4])
                            .append("=");

                }else {
                    //3×(1+2)+(4÷2)型
                    formula.append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[2]])
                            .append("(")
                            .append(operatorNum[1])
                            .append(OPERATOR[operatorIndex[0]])
                            .append(operatorNum[2])
                            .append(")")
                            .append(OPERATOR[operatorIndex[0]])
                            .append("(")
                            .append(operatorNum[3])
                            .append(OPERATOR[operatorIndex[3]])
                            .append(operatorNum[4])
                            .append(")")
                            .append("=");
                }break;
            }
            case 5:{
                if (bracketForm == 0){
                    //2*((6÷3)-1)+(4*5)型
                    formula.append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[2]])
                            .append("((")
                            .append(operatorNum[1])
                            .append(OPERATOR[operatorIndex[3]])
                            .append(operatorNum[2])
                            .append(")")
                            .append(OPERATOR[operatorIndex[1]])
                            .append(operatorNum[3])
                            .append(")")
                            .append(OPERATOR[operatorIndex[0]])
                            .append("(")
                            .append(operatorNum[4])
                            .append(OPERATOR[operatorIndex[1]])
                            .append(operatorNum[5])
                            .append(")")
                            .append("=");
                }else {
                    //(1+(2×3+4))-(6÷3)型
                    formula.append("(")
                            .append(operatorNum[0])
                            .append(OPERATOR[operatorIndex[0]])
                            .append("(")
                            .append(operatorNum[1])
                            .append(OPERATOR[operatorIndex[2]])
                            .append(operatorNum[2])
                            .append(OPERATOR[operatorIndex[0]])
                            .append(operatorNum[3])
                            .append("))")
                            .append(OPERATOR[operatorIndex[1]])
                            .append("(")
                            .append(operatorNum[4])
                            .append(OPERATOR[operatorIndex[3]])
                            .append(operatorNum[5])
                            .append(")")
                            .append("=");
                }break;
            }default:
        }
        return formula.toString();
    }
}
