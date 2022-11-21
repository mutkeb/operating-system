import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    //  页表列表
    List<Table> tableList = new ArrayList<>();
    //  指令列表
    List<Order> orderList = new ArrayList<>();
    //  新页表列表
    List<NewTable> newTableList = new ArrayList<>();
    //  新页表
    NewTable[] newTables;
    //  创建主存存储空间
    int m = 0;
    //  给定一个块号库，被使用过就会设置为1
    int[] pieceNums = new int[11];
    //  题目一
    private void init1(){
        //  初始化页表信息
        Table table1 = new Table(0,1,5,"011");
        Table table2 = new Table(1,1,8,"012");
        Table table3 = new Table(2,1,1,"013");
        Table table4 = new Table(3,1,1,"021");
        Table table5 = new Table(4,0,null,"022");
        Table table6 = new Table(5,0,null,"023");
        Table table7 = new Table(6,0,null,"121");
        tableList.add(table1);
        tableList.add(table2);
        tableList.add(table3);
        tableList.add(table4);
        tableList.add(table5);
        tableList.add(table6);
        tableList.add(table7);
        //  初始化指令信息
        Order order1 = new Order("+",0,"70");
        Order order2 = new Order("+",1,"50");
        Order order3 = new Order("×",2,"15");
        Order order4 = new Order("存",3,"21");
        Order order5 = new Order("取",0,"56");
        Order order6 = new Order("-",6,"40");
        Order order7 = new Order("移位",4,"053");
        Order order8 = new Order("+",5,"023");
        Order order9 = new Order("存",1,"023");
        Order order10 = new Order("取",2,"078");
        Order order11 = new Order("+",4,"001");
        Order order12 = new Order("存",6,"084");
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);
        orderList.add(order11);
        orderList.add(order12);
    }
    public void run1(){
        init1();
        for (Order order : orderList) {
            //  获得页号
            int pageNum = order.getPageNum();
            //  根据页号获得对应的页表
            Table table = tableList.get(pageNum);
            //  获得标志位
            int symbol = table.getSymbol();
            if (symbol == 0){
                //  发生缺页中断
                System.out.println("发生缺页中断*" +pageNum);
            }else{
                //  行成绝对地址
                int address = table.getPieceNum() * 128 + Integer.parseInt(order.getUnitNum());
                System.out.println("绝对地址为:"+address);
            }
        }
    }
    //  题目二
    private void init2(){
        //  初始化页表信息
        NewTable table1 = new NewTable(0,1,5,0,"011");
        NewTable table2 = new NewTable(1,1,8,0,"012");
        NewTable table3 = new NewTable(2,1,1,0,"013");
        NewTable table4 = new NewTable(3,1,1,0,"021");
        NewTable table5 = new NewTable(4,0,null,0,"022");
        NewTable table6 = new NewTable(5,0,null,0,"023");
        NewTable table7 = new NewTable(6,0,null,0,"121");
        newTableList.add(table1);
        newTableList.add(table2);
        newTableList.add(table3);
        newTableList.add(table4);
        newTableList.add(table5);
        newTableList.add(table6);
        newTableList.add(table7);
        for (NewTable newTable : newTableList) {
            if (newTable.getSymbol() == 1){
                m ++;
            }
        }
        newTables = new NewTable[m];
        int i = 0;
        for (NewTable newTable : newTableList) {
            if (newTable.getSymbol() == 1){
                newTables[i++] = newTable;
                pieceNums[newTable.getPieceNum()] = 1;
            }
        }
        orderInit1();
//        orderInit2();
    }
    private void orderInit1(){
        //  初始化指令信息
        Order order1 = new Order("+",0,"70");
        Order order2 = new Order("+",1,"50");
        Order order3 = new Order("×",2,"15");
        Order order4 = new Order("存",3,"21");
        Order order5 = new Order("取",0,"56");
        Order order6 = new Order("-",6,"40");
        Order order7 = new Order("移位",4,"053");
        Order order8 = new Order("+",5,"023");
        Order order9 = new Order("存",1,"023");
        Order order10 = new Order("取",2,"078");
        Order order11 = new Order("+",4,"001");
        Order order12 = new Order("存",6,"084");
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);
        orderList.add(order11);
        orderList.add(order12);
    }
    private void orderInit2(){
        Order order1 = new Order("存",5,"70");
        Order order2 = new Order("+",1,"50");
        Order order3 = new Order("×",2,"15");
        Order order4 = new Order("存",3,"21");
        Order order5 = new Order("取",2,"56");
        Order order6 = new Order("-",6,"40");
        Order order7 = new Order("移位",4,"053");
        Order order8 = new Order("+",3,"023");
        Order order9 = new Order("存",0,"023");
        Order order10 = new Order("取",1,"078");
        Order order11 = new Order("+",4,"001");
        Order order12 = new Order("存",4,"084");
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);
        orderList.add(order11);
        orderList.add(order12);
    }
    private void run2(){
        init2();
        //  要取的页表位置
        int k = 0;
        int n = 1;
        //  取一条指令
        for (Order order : orderList) {
            System.out.println("第" + n +"条指令运行结果为：");
            //  取出访问的页号
            int pageNum = order.getPageNum();
            //  查页表
            NewTable table =newTableList.get(pageNum);
            //  查标志
            int symbol = table.getSymbol();
            if (symbol == 1){
                //  形成绝对地址
                int address = 128 * table.getPieceNum() + Integer.parseInt(order.getUnitNum());
                //  根据操作,查看是否需要修改
                if (!order.getAction().equals("存") && !order.getAction().equals("取")){
                    //  操作既不是存也不算取，那就是修改
                    table.setChangeSymbol(1);
                }
                //  输出绝对地址
                System.out.println("绝对地址为:"+address);
            }else{
                //  取出页表
                NewTable j = newTables[k];
                if (j.getPageNUm() == 3){
                    j.setPieceNum(1);
                }
                //  查看修改位置
                if (j.getChangeSymbol() == 1){
                    //  调出该表
                    j.setSymbol(0);
                    pieceNums[j.getPieceNum()] = 0;
                    j.setPieceNum(null);
                    j.setChangeSymbol(0);
                    System.out.println("调出的页号为："+ j.getPageNUm());
                }else{
                    System.out.println("无调出页号");
                }
                //  覆盖
                newTables[k] = table;
                table.setSymbol(1);
                //  获得一个随机块号
                int index = 0;
                while (true){
                    Random random = new Random();
                    index = random.nextInt(10) + 1;
                    if (pieceNums[index] == 0){
                        break;
                    }
                }
                table.setPieceNum(index);
                if (!order.getAction().equals("存") && !order.getAction().equals("取")){
                    //  操作既不是存也不算取，那就是修改
                    table.setChangeSymbol(1);
                }
                k = (k + 1) % m;
                System.out.println("调入的页号为："+pageNum);
            }
            n++;
        }
        System.out.println("页号" + "    " + "标志" +"   "+ "主存块号"+"   "+"修改标志" +"   "+"在磁盘上的位置");
        for (int i = 0; i < newTables.length ; i++) {
            System.out.println(newTables[i].getPageNUm()+"      "+newTables[i].getSymbol()+"      "+newTables[i].getPieceNum()
            +"        "+newTables[i].getChangeSymbol()+"         "+newTables[i].getPosition());
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
//        main.run1();
        main.run2();
    }
}
