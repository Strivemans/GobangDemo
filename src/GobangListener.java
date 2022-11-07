import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class GobangListener extends MouseAdapter implements ActionListener,Gobang{
    private GobangMain gm;
    private Graphics g; //画笔 需要 画棋子
    private boolean cco=true; //记录棋子 true：黑棋 false：白棋
    private boolean fff=true; //记录 是否 允许悔棋
    private boolean ggg=true; //记录 是否 可以认输
    private ArrayList<Chess> chessArray; //记录 存放棋子

    //真人棋子位置
    int playerRow,playerColoum;
    //人机棋子位置
    int machineRow,machineColoum;
    //人机2位置
    int machineRow2,machineColoum2;
    //棋子 权重
    int max;

    int max2;
    //玩家选择的 难度系数
    String difficultyGrade="";
    
    public GobangListener(GobangMain gm,ArrayList<Chess> chessArry) {
        this.gm = gm;
        this.chessArray=chessArry;
    }

    //ActionListener的 接口方法
    @Override
    public void actionPerformed(ActionEvent e) {
        //开始新游戏
        if(e.getActionCommand().equals(messageAeeay[0])) {
            //初始化 棋盘数组 初始化都为0
            for(int i=0;i<chessPositionArray.length;i++) {
                for(int j=0;j<chessPositionArray[i].length;j++) {
                    chessPositionArray[i][j]=0;
                }
            }
            cco=true; //先下黑棋
            fff=true; //能悔棋
            ggg=true; //能认输
            gm.addMouseListener(this);
            chessArray.clear(); //清空 棋子数组
            //具有刷新页面的效果 ，说白了 就是重画棋盘
            gm.repaint();
        }

        //悔棋
        if(e.getActionCommand().equals(messageAeeay[1])) {
            //在进行 人人对战
            if(flag[0]) {
                if(fff) {  //允许 悔棋
                    if(chessArray.size()>1) {  //说明 棋盘有棋子
                        //把棋盘数组的当前棋子位置 变为 0
                        chessPositionArray[playerRow][playerColoum]=0;
                        //获取 前一个 当前悔棋的 前一个棋子的位置
                        Chess temp=chessArray.get(chessArray.size()-2);
                        playerRow=temp.row;
                        playerColoum=temp.coloum;
                        chessArray.remove(chessArray.size()-1); //删除当前棋子
                        //把 下棋权 交给 悔棋方
                        cco=!cco;
                        gm.repaint();
                    }
                }
            }
            //人机对战悔棋
            if(flag[1]) {
                if(fff) {
                    //只有 真人才会悔棋 所以 chessArray.size()>2
                    if(cco) {
                        //当有棋子
                        if(chessArray.size()>2) {
                            //把人机的棋子从 棋盘组中拿掉
                            chessPositionArray[machineRow][machineColoum]=0;
                            //获取 悔棋前的棋子
                            Chess temp=chessArray.get(chessArray.size()-2);
                            playerRow=temp.row;
                            playerColoum=temp.coloum;
                            chessArray.remove(chessArray.size()-1);
                            chessPositionArray[playerRow][playerColoum]=0;

                            //设置 ai棋子的位置
                            Chess temp2=chessArray.get(chessArray.size()-2);
                            machineRow=temp2.row;
                            machineColoum=temp2.coloum;
                            chessArray.remove(chessArray.size()-1);

                            gm.repaint();
                        }
                    }
                }
            }



        }

        //选择 认输
        if(e.getActionCommand().equals(messageAeeay[2])) {
            if(ggg) { //允许认输
                if(cco) {
                    JOptionPane.showMessageDialog(gm, "白棋获胜");
                }else {
                    JOptionPane.showMessageDialog(gm, "黑棋获胜");
                }
            }
            gm.removeMouseListener(this);
            fff=false; //设置 不可悔棋
            ggg=false; //设置 不可认输
        }

        //选择 人人对战
        if(e.getActionCommand().equals(messageAeeay[4])) {
            flag[0]=true;
            flag[1]=false;
            //设置 棋盘 数组为0 （相当于 重新开游戏）
            for(int i=0;i<chessPositionArray.length;i++) {
                Arrays.fill(chessPositionArray[i], 0);
            }
            cco=true; //黑棋先手
            fff=true; //允许悔棋
            ggg=true; //允许认输
            chessArray.clear();//清空 棋子数组
            gm.repaint();
        }

        //选择 人机对战(简单难度)
        if(e.getActionCommand().equals(messageAeeay[5])) {
        	//设置难度
        	difficultyGrade=messageAeeay[5];
            flag[0]=false;
            flag[1]=true;
            //设置 棋盘 数组为0 （相当于 重新开游戏）
            for(int i=0;i<chessPositionArray.length;i++) {
                Arrays.fill(chessPositionArray[i], 0);
            }
            cco=true; //黑棋先手
            fff=true; //允许悔棋
            ggg=true; //允许认输
            chessArray.clear();//清空 棋子数组
            gm.repaint();
        }

        //选择 人机对战(困难难度)
        if(e.getActionCommand().equals(messageAeeay[6])) {
            flag[0]=false;
            flag[1]=true;
            difficultyGrade=messageAeeay[6];
            for(int i=0;i<chessPositionArray.length;i++){
               Arrays.fill(chessPositionArray[i],0);
            }
            cco=true;
            fff=true;
            ggg=true;
            chessArray.clear();
            gm.repaint();
        }

        //选择 人机 vs 人机
        if(e.getActionCommand().equals(messageAeeay[7])){
            flag[0]=false;
            flag[1]=false;
            flag[2]=true;
            for(int i=0;i<chessPositionArray.length;i++){
                Arrays.fill(chessPositionArray[i],0);
            }
            cco=true;
            fff=false;  //人机之间 只有厮杀 没有悔棋!!
            ggg=false;  //人机之间 只有厮杀 没有认输!!
            chessArray.clear();
            gm.repaint();
        }

    }
    //鼠标鼠标
    public void mouseReleased(MouseEvent e) {
        //人人对战
        if(flag[0]) {
           if(g==null){
               g=gm.getGraphics(); //获取 画笔
           }
           //获取 棋子的坐标
            int x=e.getX();
            int y=e.getY();
            playerRow=Math.round(x/size);  //Math.round 四舍五入
            playerColoum=Math.round(y/size);
            //下棋 位置 在 棋盘内
            if(playerRow<row && playerColoum<coloum){
                //下棋的 位置 没有 棋子
                if(chessPositionArray[playerRow][playerColoum]==0){
                    //黑棋
                    if(cco){
                        //创建棋子对象
                        Chess chess=new Chess(playerRow,playerColoum,Color.BLACK);
                        //设置 棋子位置数组
                        chessPositionArray[playerRow][playerColoum]=1;
                        //把棋子 添加到 棋子数组中
                        chessArray.add(chess);
                    }else{  //白棋
                        //创建棋子对象
                        Chess chess=new Chess(playerRow,playerColoum,Color.WHITE);
                        //设置 棋子位置数组
                        chessPositionArray[playerRow][playerColoum]=-1;
                        //把棋子 添加到 棋子数组中
                        chessArray.add(chess);
                    }
                    //cco=!cco; //交换棋权
                    WindOrNo(playerRow,playerColoum);  //每下一次棋 判断输赢
                }
            }
        }  
        if(flag[1]) { //人机对战
            if(g==null) {
            	g=gm.getGraphics();
            }
            if(cco) {  //人：黑棋 先下
            	int x=e.getX();
            	int y=e.getY();
            	playerRow=Math.round(x/size);  //Math.round 四舍五入
                playerColoum=Math.round(y/size);
                if(playerRow<row && playerColoum<coloum) {
                	if(chessPositionArray[playerRow][playerColoum]==0) {
                		chessPositionArray[playerRow][playerColoum]=1;
                		Chess temp=new Chess(playerRow, playerColoum,Color.BLACK);
                		chessArray.add(temp);
                		//判断输赢
                		WindOrNo(playerRow,playerColoum);
                	}
                }
            }
            if(!cco) {  //人机下棋
            	AIX();
            }
            
        }

        if(flag[2]){
            if(g==null){
                g=gm.getGraphics();
            }
            if(cco){
                difficultyGrade=messageAeeay[5];
                AIX2();
            }
            if(!cco){
                difficultyGrade=messageAeeay[6];
                AIX();
            }
        }


        gm.paint(g);  //调用GobangMain 的 paint函数 画棋子
    }
    
    //判断输赢
    public void  WindOrNo(int row,int coloum) {
    	Judge jd=new Judge();
    	if(jd.judge(row, coloum)) {
    		if(cco) {
    			JOptionPane.showMessageDialog(gm, "黑棋获胜");
    		}else {
    			JOptionPane.showMessageDialog(gm, "白棋获胜");
    		}
    		gm.removeMouseListener(this);  //释放 鼠标事件
    		fff=false; //不可悔棋
    		ggg=false; //不可认输

    	}else {
    		cco=!cco;  //还没结束 交换期权
    	}
    }
    
    //人机 下棋
    public void AIX() {
    	//将棋盘中的 权重 初始化 为0
    	for(int i=0;i<weightArray.length;i++) {
    		for(int j=0;j<weightArray[i].length;j++) {
    			weightArray[i][j]=0;
    		}
    	}
    	//初始化 最大权重值
    	max=-1;
    	
    	//根据不同难度 得到不同的权重方式
    	switch(difficultyGrade) {
    		case "简单人机":
    			EasyAi.chessWeight();
    			break;
    		case "困难人机":
    			DifficultAI.chessWeight();
    			break;
    	}
    	
    	//测试 用于 输出权重数组
    	for(int i=0;i<weightArray.length;i++) {
    		for(int j=0;j<weightArray[i].length;j++) {
    			System.out.print(weightArray[i][j]+" ");
    		}
    		System.out.println();
    	}
    	//输出 长度
    	System.err.println(weightArray.length+"--++"+weightArray[0].length);
    	
    	//获取 最大的权重值 以及对应的棋盘位置
    	for(int i=0;i<weightArray.length;i++) {
    		for(int j=0;j<weightArray[i].length;j++) {
    			if(max<weightArray[i][j] && chessPositionArray[i][j]==0) {
    				max=weightArray[i][j];
    				machineRow=i;
    				machineColoum=j;
    			}
    		}
    	}
    	
    	//输出 最大权值 和 对应的 位置
    	System.out.println(machineRow+"--++"+machineColoum+"--"+max);
    	
    	if(chessPositionArray[machineRow][machineColoum]==0){
            chessPositionArray[machineRow][machineColoum]=-1; //人机：白棋
            Chess temp=new Chess(machineRow,machineColoum,Color.WHITE);
            chessArray.add(temp);

            //判断输赢
            WindOrNo(machineRow,machineColoum);
        }
    	
    }

    //人机 下棋
    public void AIX2() {
        //将棋盘中的 权重 初始化 为0
        for(int i=0;i<weightArray.length;i++) {
            for(int j=0;j<weightArray[i].length;j++) {
                weightArray[i][j]=0;
            }
        }
        //初始化 最大权重值
        max2=-1;

        //根据不同难度 得到不同的权重方式
        switch(difficultyGrade) {
            case "简单人机":
                EasyAi.chessWeight();
                break;
            case "困难人机":
                DifficultAI.chessWeight();
                break;
        }

        //测试 用于 输出权重数组
        for(int i=0;i<weightArray.length;i++) {
            for(int j=0;j<weightArray[i].length;j++) {
                System.out.print(weightArray[i][j]+" ");
            }
            System.out.println();
        }
        //输出 长度
        System.err.println(weightArray.length+"--++"+weightArray[0].length);

        //获取 最大的权重值 以及对应的棋盘位置
        for(int i=0;i<weightArray.length;i++) {
            for(int j=0;j<weightArray[i].length;j++) {
                if(max2<weightArray[i][j] && chessPositionArray[i][j]==0) {
                    max2=weightArray[i][j];
                    machineRow2=i;
                    machineColoum2=j;
                }
            }
        }

        //输出 最大权值 和 对应的 位置
        System.out.println(machineRow2+"--++"+machineColoum2+"--"+max2);

        if(chessPositionArray[machineRow2][machineColoum2]==0){
            chessPositionArray[machineRow2][machineColoum2]=1; //人机2：黑棋
            Chess temp=new Chess(machineRow2,machineColoum2,Color.BLACK);
            chessArray.add(temp);

            //判断输赢
            WindOrNo(machineRow2,machineColoum2);
        }

    }
}
