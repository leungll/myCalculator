import java.io.*;
import java.util.Random;

/**
 * @Author:liangll
 * @Description:
 * @Date: 21:22 2019/3/17
 */
public class CreateFile {
    public void fileCreate(int n) {
        try {
            File file = new File("../result.txt");
            //如果根目录中存在名为result.txt文件，则删除
            if (file.exists()) {
                file.delete();
            }
            //创建文件
            file.createNewFile();
            //写文件
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("2017011581");
            //换行
            bw.newLine();
            SimpleCalculator simpleCalculator = new SimpleCalculator();
            ProperFraction properFraction = new ProperFraction();
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                int x = random.nextInt(2);
                if (x == 0) {
                    //产生简单四则运算(无分数版)，每个数字的范围在0和100之间
                    bw.write(simpleCalculator.calculatorInteger());
                    bw.newLine();
                } else {
                    //产生真分数四则运算，每个数字的范围在0和100之间
                    bw.write(properFraction.properFraction());
                    bw.newLine();
                }
            }
            bw.flush();
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
