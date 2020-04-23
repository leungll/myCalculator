import java.util.Scanner;

/**
 * @Author:liangll
 * @Description:
 * @Date: 20:39 2019/3/13
 */
public class Main {
    public static void main(String[] args) throws Exception{
        int n;
        try {
            //从命令行接收参数
            n = Integer.parseInt(args[0]);
//            Scanner input = new Scanner(System.in);
//            n = input.nextInt();
            if(n < 1 || n > 1000) {
                System.out.println("输入题目数量不符合要求，请重新输入题目数量!");
                return;
            }
        }catch(NumberFormatException e) {
            System.out.println("输入非法字符，请重新输入题目数量!");
            return;
        }
        CreateFile createFile = new CreateFile();
        createFile.fileCreate(n);
    }
}
