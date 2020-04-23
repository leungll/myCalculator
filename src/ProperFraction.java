import java.util.Random;

/**
 * @Author:liangll
 * @Description:真分数四则运算
 * @Date: 8:41 2019/3/17
 */
public class ProperFraction {

    private static final String[] OPERATOR = {"+", "-"};

    /**
     * 真分数生成器
     * @return
     */
    public String properFraction(){
        Random random = new Random();
        //操作符的个数3-5
        int operatorCount = 3 + random.nextInt(3);

        SimpleCalculator createIndex = new SimpleCalculator();
        //操作符的下标
        int[] operatorIndex = createIndex.index(operatorCount,2, random);

        //第一个数的分子1-20
        int numx1 = 1 + random.nextInt(10);
        //第一个数的分母1-20
        int numy1 = 1 + random.nextInt(10);
        int greatFactor = greatFactor(numx1, numy1);
        //化简
        numx1 /= greatFactor;
        numy1 /= greatFactor;

        while (numx1 >= numy1){
            numx1 = 1 + random.nextInt(10);
            numy1 = 1 + random.nextInt(10);
            greatFactor = greatFactor(numx1 ,numy1);
            numx1 /= greatFactor;
            numy1 /= greatFactor;
        }

        String s = numx1 + "/" + numy1;

        for(int i = 0; i < operatorCount; i++){
            int numx2 = random.nextInt(25);
            int numy2 = 1 + random.nextInt(25);
            String currentOpreator = OPERATOR[operatorIndex[i]];
            //生成剩下的操作数
            while (numx2 >= numy2){
                numx2 = random.nextInt(25);
                numy2 = 1 + random.nextInt(25);
                greatFactor = greatFactor(numx2 ,numy2);
                numx2 /= greatFactor;
                numy2 /= greatFactor;
            }

            //加法
            if(currentOpreator.equals("+")) {
                //和为假分数
                while(numx1 * numy2 + numy1 * numx2 > numy1 * numy2)
                {
                    numx2 = random.nextInt(25);
                    numy2 = 1 + random.nextInt(25);
                    greatFactor = greatFactor(numx2, numy2);
                    numx2 /= greatFactor;
                    numy2 /= greatFactor;
                }
                numx1 = numx1 * numy2 + numy1 * numx2;
                numy1 *= numy2;
            }else{
                //减法，差为负数
                while(numx1 * numy2 - numy1 * numx2 < 0){
                    numx2 = random.nextInt(25);
                    numy2 = 1 + random.nextInt(25);
                    greatFactor = greatFactor(numx2, numy2);
                    numx2 /= greatFactor;
                    numy2 /= greatFactor;
                }
                numx1 = numx1 * numy2 - numy1 * numx2;
                numy1 *= numy2;
            }
            s += currentOpreator + numx2 + "/" + numy2;
        }

        greatFactor = greatFactor(numx1, numy1);
        //最终结果化简
        numx1 /= greatFactor;
        numy1 /= greatFactor;

        if(numx1 == 0){
            s += "=" + numx1;
        }
        else if(numx1 == 1&& numy1 ==1){
            s += "=" + numx1;
        }
        else{
            s += "=" + numx1 + "/" + numy1;
        }
        return s;
    }

    /**
     * 求最大公因数
     * @param x
     * @param y
     * @return
     */
    public int greatFactor(int x,int y) {
        while(true){
            if(x % y == 0){
                return y;
            }
            int temp = y;
            y = x % y;
            x = temp;
        }
    }
}
