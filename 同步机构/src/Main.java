import javax.print.attribute.standard.NumberUp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    //  运行态
    private static String RUNNING_STATUS = "run";
    //  就绪态
    private static String READY_STATUS = "ready";
    //  等待态
    private static String WAITING_STATUS = "wait";
    //  完成态
    private static String ENDING_STATUS = "end";

    //  初始化信号量
    private int S1,S2;

    //  定义生产者、消费者指令
    private String[] PA,SA;

    //  生成生产者和消费者进程列表
    private List<PCB> producePCBs,consumePCBs;

    //  指示现行进程是消费者还是生产者，若为0则运行生产者进程
    private int flag;

    //  仓库
    private char B[] = new char[10];

    //  存放字符
    private char c;
    private char x;

    //  定义PC
    private int PC;

    private int IN,OUT;

    public void init(){
        //  信号量
        S1 = 10;
        S2 = 0;
        PC = 0;
        flag = 0;
        IN = 0;
        OUT = 0;
        PA = new String[5];
        SA = new String[5];
        //  生产者指令
        PA[0] = "produce";
        PA[1] = "P(s1)";
        PA[2] = "PUT";
        PA[3] = "V(s2)";
        PA[4] = "goto 0";
        //  消费者指令
        SA[0] = "P(s2)";
        SA[1] = "GET";
        SA[2] = "V(s1)";
        SA[3] = "consume";
        SA[4] = "goto 0";
        //  生产者进程和消费者进程各生成10个
        producePCBs = new ArrayList<>();
        consumePCBs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            producePCBs.add(new PCB("生产者",READY_STATUS, null,0));
            consumePCBs.add(new PCB("消费者",READY_STATUS, null,0));
        }
    }

    public void run(){
        init();
        int end = 0;
        while (true){
            //  随机取一个就绪状态得进程
            int position = -1;
            switch (flag){
                //  运行生产者进程
                case 0:
                    //  取出该进程
                    for (int i = 0; i < 10; i++) {
                        if (producePCBs.get(i).getStatus().equals(READY_STATUS)){
                            position = i;
                            break;
                        }
                    }
                    if (position == -1){
                        //  说明生产者进程已无就绪状态进程
                        break;
                    }
                    PCB pcb = producePCBs.get(position);
                    pcb.setStatus(RUNNING_STATUS);
                    //  获得该进程得断点
                    int breakPoint = pcb.getBreakPoint();
                    //  赋值PC
                    PC = breakPoint;
                    //  开始取出对应的指令
                    String instuction = PA[PC];
                    switch (instuction){
                        case "produce":
                            System.out.println("执行生产者指令：produce");
                            //  输入一个字符放入C中
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("请输入一个字符：");
                            c = scanner.next().charAt(0);
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "P(s1)":
                            System.out.println("执行生产者指令：P(s1)");
                            //  执行P操作原语
                            S1--;
                            if (S1 < 0){
                                //  将其置为等待状态
                                pcb.setStatus(WAITING_STATUS);
                                System.out.println("进程置为等待态，等待原因：s1");
                            }else{
                                //  将其状态设置为就绪
                                pcb.setStatus(READY_STATUS);
                            }
                            break;
                        case "PUT":
                            System.out.println("执行生产者指令：PUT");
                            B[IN] = c;
                            IN = (IN + 1) % 10;
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "V(s2)":
                            System.out.println("执行生产者指令：V(s2)");
                            //  执行V操作原语
                            S2++;
                            if (S2 < 0){
                                //  找一个等待s信号量的进程设置为就绪态
                                for (int i = 0; i < 10; i++) {
                                    if (producePCBs.get(i).getStatus().equals(WAITING_STATUS)){
                                        producePCBs.get(i).setStatus(READY_STATUS);
                                        break;
                                    }
                                }
                            }
                            //  将当前进程设置为就绪
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "goto 0":
                            System.out.println("执行生产者指令：goto 0");
                            pcb.setStatus(READY_STATUS);
                            break;
                    }
                    PC = (PC + 1) % 5;
                    pcb.setBreakPoint(PC);
                    break;
                //  运行消费者进程
                case 1:
                    //  取出该进程
                    for (int i = 0; i < 10; i++) {
                        if (consumePCBs.get(i).getStatus().equals(READY_STATUS)){
                            position = i;
                            break;
                        }
                    }
                    if (position == -1){
                        //  说明消费者进程已无就绪状态进程
                        break;
                    }
                    pcb = consumePCBs.get(position);
                    pcb.setStatus(RUNNING_STATUS);
                    //  获得该进程得断点
                    breakPoint = pcb.getBreakPoint();
                    //  赋值PC
                    PC = breakPoint;
                    //  开始取出对应的指令
                    instuction = SA[PC];
                    switch (instuction){
                        case "P(s2)":
                            System.out.println("执行消费者指令：P(s2)");
                            //  执行P操作原语
                            S2--;
                            System.out.println("S2:" + S2);
                            if (S2 < 0){
                                //  将其置为等待状态
                                pcb.setStatus(WAITING_STATUS);
                                System.out.println("进程置为等待态，等待原因：s2");
                            }else{
                                //  将其状态设置为就绪
                                pcb.setStatus(READY_STATUS);
                            }
                            break;
                        case "GET":
                            System.out.println("执行消费者指令：GET");
                            x = B[OUT];
                            OUT = (OUT + 1)  % 10;
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "V(s1)":
                            System.out.println("执行消费者指令：V(s1)");
                            //  执行V操作原语
                            S1++;
                            if (S1 < 0){
                                //  找一个等待s信号量的进程设置为就绪态
                                for (int i = 0; i < 10; i++) {
                                    if (consumePCBs.get(i).getStatus().equals(WAITING_STATUS)){
                                        consumePCBs.get(i).setStatus(READY_STATUS);
                                        break;
                                    }
                                }
                            }
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "consume":
                            System.out.println("执行消费者指令：consume,x中字符为：" + x);
                            pcb.setStatus(READY_STATUS);
                            break;
                        case "goto 0":
                            System.out.println("执行消费者指令：goto 0");
                            pcb.setStatus(READY_STATUS);
                            break;
                    }
                    PC = (PC + 1) % 5;
                    pcb.setBreakPoint(PC);
                    break;
                default:
                    break;
            }
            Random random = new Random();
            flag = (int) Math.floor(random.nextInt(2));
            if (end == 0){
                //  生产者进程是否结束
                Scanner scanner = new Scanner(System.in);
                System.out.print("生产者进程是否需要结束(Y/N)");
                String str = scanner.next();
                if (str.equals("Y")){
                    end = 1;
                    flag = 1;
                }
            }else{
                flag = 1;
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}
