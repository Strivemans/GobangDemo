import java.util.HashMap;

//简单 人机
public class EasyAi implements Gobang{
	static HashMap<String,Integer> map=new HashMap<>();
	
	//静态代码块 里面存放 各种正常情况的棋子相连情况的权重
	static {
		//活一连：有一颗棋子 在棋盘上没有拦截 
		map.put("010",20);  //1：黑 -1：白
		map.put("0-10",15);
		//眠一连：有一颗棋子 在棋盘上被拦截 
		map.put("-110",1);
		map.put("1-10",1);

		//活二连
		map.put("0110",200);
		map.put("0-1-10",150);
		//眠二连
		map.put("-1110",20);
		map.put("1-1-10",10);
		
		//活三连
		map.put("01110",7000);	
		map.put("0-1-1-10",5250);
		//眠三连
		map.put("-11110",50);	
		map.put("1-1-1-10",30);
		
		//活四连
		map.put("011110",10000);	
		map.put("0-1-1-1-10",10000);
		//眠四连
		map.put("-111110",10000);	
		map.put("1-1-1-1-10",10000);
		
		//破壁眠四连：有一个方向 被墙壁拦截
		map.put("11110",10000);	
		map.put("-1-1-1-10",10000);
	}
	
	static String code; //存放棋子相连情况
	
	static Integer weight; //权重
	
	//测试 每个位置的权重
	public static void chessWeight() {
		for(int r=0;r<chessPositionArray.length;r++) {
			for(int c=0;c<chessPositionArray[r].length;c++) {
				//没有 棋子的地方 开始 获得 棋子相连路径
				if(chessPositionArray[r][c]==0) {
					//水平向左获取棋子路径情况
					code=countHL(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//水平向右获取棋子路径情况
					code=countHR(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//水平向下获取棋子路径情况
					code=countDown(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//水平向上获取棋子路径情况
					code=countUp(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//左斜向下获取棋子路径情况
					code=countHLDown(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//右斜向下获取棋子路径情况
					code=countHRDown(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//左斜向上获取棋子路径情况
					code=countHLUp(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
					//右斜向上获取棋子路径情况
					code=countHRUp(r,c);
					weight=map.get(code);
					if(weight!=null) {
						weightArray[r][c]+=weight;  //累加权值
					}
					
							//根据特殊的情况 进行权值处理
					//1.判断 两个二连在一条直线上但 中间有一个空位的情况
					if((countHL(r,c)+countHR(r,c)=="01100110")   //左右
							|| (countHL(r,c)+countHR(r,c)=="0-1-100-1-10")
							|| (countHL(r,c)+countHR(r,c)=="-11100110") 
							|| (countHL(r,c)+countHR(r,c)=="1-1-100-1-10")
							|| (countHL(r,c)+countHR(r,c)=="0110-1110")
							|| (countHL(r,c)+countHR(r,c)=="0-1-101-1-10")
							|| (countHL(r,c)+countHR(r,c)=="-1110-1110")
							|| (countHL(r,c)+countHR(r,c)=="1-1-101-1-10")) {
						weightArray[r][c]=weightArray[r][c]+5000;
					}
					if((countDown(r,c)+countUp(r,c)=="01100110")  //上下
							|| (countDown(r,c)+countUp(r,c)=="0-1-100-1-10")
							|| (countDown(r,c)+countUp(r,c)=="-11100110") 
							|| (countDown(r,c)+countUp(r,c)=="1-1-100-1-10")
							|| (countDown(r,c)+countUp(r,c)=="0110-1110")
							|| (countDown(r,c)+countUp(r,c)=="0-1-101-1-10")
							|| (countDown(r,c)+countUp(r,c)=="-1110-1110")
							|| (countDown(r,c)+countUp(r,c)=="1-1-101-1-10")) {
						weightArray[r][c]=weightArray[r][c]+5000;
					}
					if((countHLDown(r,c)+countHRUp(r,c)=="01100110")  //左下右上线
							|| (countHLDown(r,c)+countHRUp(r,c)=="0-1-100-1-10")
							|| (countHLDown(r,c)+countHRUp(r,c)=="-11100110") 
							|| (countHLDown(r,c)+countHRUp(r,c)=="1-1-100-1-10")
							|| (countHLDown(r,c)+countHRUp(r,c)=="0110-1110")
							|| (countHLDown(r,c)+countHRUp(r,c)=="0-1-101-1-10")
							|| (countHLDown(r,c)+countHRUp(r,c)=="-1110-1110")
							|| (countHLDown(r,c)+countHRUp(r,c)=="1-1-101-1-10")) {
						weightArray[r][c]=weightArray[r][c]+5000;
					}
					if((countHLUp(r,c)+countHRDown(r,c)=="01100110")  //左上右下线
							|| (countHLUp(r,c)+countHRDown(r,c)=="0-1-100-1-10")
							|| (countHLUp(r,c)+countHRDown(r,c)=="-11100110") 
							|| (countHLUp(r,c)+countHRDown(r,c)=="1-1-100-1-10")
							|| (countHLUp(r,c)+countHRDown(r,c)=="0110-1110")
							|| (countHLUp(r,c)+countHRDown(r,c)=="0-1-101-1-10")
							|| (countHLUp(r,c)+countHRDown(r,c)=="-1110-1110")
							|| (countHLUp(r,c)+countHRDown(r,c)=="1-1-101-1-10")) {
						weightArray[r][c]=weightArray[r][c]+5000;
					}
					
					
					//2.两个活连 和 一个活连 在一条直线上 但中间有空位的情况
					if((countHL(r,c)+countHR(r,c)=="0100110")
							||(countHL(r,c)+countHR(r,c)=="0-100-1-10")
							||(countHL(r,c)+countHR(r,c)=="0110010")
							||(countHL(r,c)+countHR(r,c)=="0-1-100-10")) {
						weightArray[r][c]=weightArray[r][c]+3000;
					}
					if((countUp(r,c)+countDown(r,c)=="0100110")
							||(countUp(r,c)+countDown(r,c)=="0-100-1-10")
							||(countUp(r,c)+countDown(r,c)=="0110010")
							||(countUp(r,c)+countDown(r,c)=="0-1-100-10")) {
						weightArray[r][c]=weightArray[r][c]+3000;
					}
					if((countHLDown(r,c)+countHRUp(r,c)=="0100110")
							||(countHLDown(r,c)+countHRUp(r,c)=="0-100-1-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="0110010")
							||(countHLDown(r,c)+countHRUp(r,c)=="0-1-100-10")) {
						weightArray[r][c]=weightArray[r][c]+3000;
					}
					if((countHLUp(r,c)+countHRDown(r,c)=="0100110")
							||(countHLUp(r,c)+countHRDown(r,c)=="0-100-1-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="0110010")
							||(countHLUp(r,c)+countHRDown(r,c)=="0-1-100-10")) {
						weightArray[r][c]=weightArray[r][c]+3000;
					}
					
					
					//3.眠3连的 一端被堵了 -1111 & 010
					if((countHL(r,c)+countHR(r,c)=="1-1-1-10010")
							||(countHL(r,c)+countHR(r,c)=="0101-1-1-10")
							||(countHL(r,c)+countHR(r,c)=="1-1-1-100") //被右边墙壁堵了
							||(countHL(r,c)+countHR(r,c)=="01-1-1-10") //被左边墙壁堵了
							||(countHL(r,c)+countHR(r,c)=="1-1-1-100110") //被左边的白棋堵了
							||(countHL(r,c)+countHR(r,c)=="01101-1-1-10")) { 
						weightArray[r][c]=1;  //权重变为1 
					}
					if((countUp(r,c)+countDown(r,c)=="1-1-1-10010")
							||(countUp(r,c)+countDown(r,c)=="0101-1-1-10")
							||(countUp(r,c)+countDown(r,c)=="1-1-1-100") //被右边墙壁堵了
							||(countUp(r,c)+countDown(r,c)=="01-1-1-10") //被左边墙壁堵了
							||(countUp(r,c)+countDown(r,c)=="1-1-1-100110") //被左边的白棋堵了
							||(countUp(r,c)+countDown(r,c)=="01101-1-1-10")) { 
						weightArray[r][c]=1;  //权重变为1 
					}
					if((countHLDown(r,c)+countHRUp(r,c)=="1-1-1-10010")
							||(countHLDown(r,c)+countHRUp(r,c)=="0101-1-1-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="1-1-1-100") //被右边墙壁堵了
							||(countHLDown(r,c)+countHRUp(r,c)=="01-1-1-10") //被左边墙壁堵了
							||(countHLDown(r,c)+countHRUp(r,c)=="1-1-1-100110") //被左边的白棋堵了
							||(countHLDown(r,c)+countHRUp(r,c)=="01101-1-1-10")) { 
						weightArray[r][c]=1;  //权重变为1 
					}
					if((countHLUp(r,c)+countHRDown(r,c)=="1-1-1-10010")
							||(countHLUp(r,c)+countHRDown(r,c)=="0101-1-1-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="1-1-1-100") //被右边墙壁堵了
							||(countHLUp(r,c)+countHRDown(r,c)=="01-1-1-10") //被左边墙壁堵了
							||(countHLUp(r,c)+countHRDown(r,c)=="1-1-1-100110") //被左边的白棋堵了
							||(countHLUp(r,c)+countHRDown(r,c)=="01101-1-1-10")) { 
						weightArray[r][c]=1;  //权重变为1 
					}
					
					
					//4.三连 和 1连在一条直线上
					if((countHL(r,c)+countHR(r,c)=="0-1-1-100-10")
							||(countHL(r,c)+countHR(r,c)=="0-101-1-1-10")
							||(countHL(r,c)+countHR(r,c)=="01110010")
							||(countHL(r,c)+countHR(r,c)=="010-11110")
							||(countHL(r,c)+countHR(r,c)=="0-100-1-1-10")
							||(countHL(r,c)+countHR(r,c)=="1-1-1-100-10")
							||(countHL(r,c)+countHR(r,c)=="01001110")
							||(countHL(r,c)+countHR(r,c)=="-11110010")) {
						weightArray[r][c]=weightArray[r][c]+4000;
					}
					if((countUp(r,c)+countDown(r,c)=="0-1-1-100-10")
							||(countUp(r,c)+countDown(r,c)=="0-101-1-1-10")
							||(countUp(r,c)+countDown(r,c)=="01110010")
							||(countUp(r,c)+countDown(r,c)=="010-11110")
							||(countUp(r,c)+countDown(r,c)=="0-100-1-1-10")
							||(countUp(r,c)+countDown(r,c)=="1-1-1-100-10")
							||(countUp(r,c)+countDown(r,c)=="01001110")
							||(countUp(r,c)+countDown(r,c)=="-11110010")) {
						weightArray[r][c]=weightArray[r][c]+4000;
					}
					if((countHLDown(r,c)+countHRUp(r,c)=="0-1-1-100-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="0-101-1-1-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="01110010")
							||(countHLDown(r,c)+countHRUp(r,c)=="010-11110")
							||(countHLDown(r,c)+countHRUp(r,c)=="0-100-1-1-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="1-1-1-100-10")
							||(countHLDown(r,c)+countHRUp(r,c)=="01001110")
							||(countHLDown(r,c)+countHRUp(r,c)=="-11110010")) {
						weightArray[r][c]=weightArray[r][c]+4000;
					}
					if((countHLUp(r,c)+countHRDown(r,c)=="0-1-1-100-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="0-101-1-1-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="01110010")
							||(countHLUp(r,c)+countHRDown(r,c)=="010-11110")
							||(countHLUp(r,c)+countHRDown(r,c)=="0-100-1-1-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="1-1-1-100-10")
							||(countHLUp(r,c)+countHRDown(r,c)=="01001110")
							||(countHLUp(r,c)+countHRDown(r,c)=="-11110010")) {
						weightArray[r][c]=weightArray[r][c]+4000;
					}		
				}
				
			}
		}
	}
	
	//水平向左的棋子相连情况
	public static String countHL(int r,int c) {
		StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
		int chess=0; //记录 第一颗棋子 
		for(int r1=r-1;r1>=0;r1--) {  //向左
			if(chessPositionArray[r1][c]==0) {//没有棋子 
				//为了保证 路径的 情况 不会存在 过多的 空棋情况
				// 这操作 保证了 第二位置 必须是 有棋子的情况
				if(r1+1==r) {
					break;  
				}else {
					code.insert(0,chessPositionArray[r1][c]); 
					break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
				}
			}else{    //有棋子
				if(chess==0) {  //说明 是 第一个 棋子
					chess=chessPositionArray[r1][c]; //记录 第一个 棋子
					code.insert(0,chessPositionArray[r1][c]);
				}else if(chess==chessPositionArray[r1][c]) { //后面棋子和 第一棋子相连
					code.insert(0,chessPositionArray[r1][c]);
				}else {  //碰到了别的棋子拦截
					code.insert(0,chessPositionArray[r1][c]);
					break; //结束 棋子相连情况记录
				}
			}
		}
		return new String(code);
	}
	
	//水平向右的棋子相连情况
	public static String countHR(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int r1=r+1;r1<row;r1++) {  //向左
				if(chessPositionArray[r1][c]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(r1-1==r) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r1][c]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r1][c]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r1][c]);
					}else if(chess==chessPositionArray[r1][c]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r1][c]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r1][c]);
						break; //结束 棋子相连情况记录
					}
				}
			}
			return new String(code);
		}
	
	//水平向下的棋子相连情况
	public static String countDown(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int c1=c-1;c1>=0;c1--) {  //向左
				if(chessPositionArray[r][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(c1+1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r][c1]);
					}else if(chess==chessPositionArray[r][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
	//水平向上的棋子相连情况
	public static String countUp(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int c1=c+1;c1<coloum;c1++) {  //向左
				if(chessPositionArray[r][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(c1-1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r][c1]);
					}else if(chess==chessPositionArray[r][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
	//左斜向下的棋子相连情况
	public static String countHLDown(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int r1=r-1,c1=c-1;r1>=0&&c1>=0;r1--,c1--) {  
				if(chessPositionArray[r1][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(r1+1==r && c1+1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r1][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r1][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r1][c1]);
					}else if(chess==chessPositionArray[r1][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r1][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r1][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
	//右斜向下的棋子相连情况
	public static String countHRDown(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int r1=r+1,c1=c-1;r1<row&&c1>=0;r1++,c1--) {  
				if(chessPositionArray[r1][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(r1-1==r && c1+1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r1][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r1][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r1][c1]);
					}else if(chess==chessPositionArray[r1][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r1][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r1][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
	//左斜向上的棋子相连情况
	public static String countHLUp(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int r1=r-1,c1=c+1;r1>=0&&c1<coloum;r1--,c1++) {  
				if(chessPositionArray[r1][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(r1+1==r && c1-1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r1][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r1][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r1][c1]);
					}else if(chess==chessPositionArray[r1][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r1][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r1][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
	//右斜向上的棋子相连情况
	public static String countHRUp(int r,int c) {
			StringBuilder code=new StringBuilder("0");  //记录棋子相连情况
			int chess=0; //记录 第一颗棋子 
			for(int r1=r+1,c1=c+1;r1<row&&c1<coloum;r1++,c1++) {  
				if(chessPositionArray[r1][c1]==0) {//没有棋子 
					//为了保证 路径的 情况 不会存在 过多的 空棋情况
					// 这操作 保证了 第二位置 必须是 有棋子的情况
					if(r1-1==r && c1-1==c) {
						break;  
					}else {
						code.insert(0,chessPositionArray[r1][c1]); 
						break;  //碰到 空位 就退出循环 即 结束本次位置的 路径记录
					}
				}else{    //有棋子
					if(chess==0) {  //说明 是 第一个 棋子
						chess=chessPositionArray[r1][c1]; //记录 第一个 棋子
						code.insert(0,chessPositionArray[r1][c1]);
					}else if(chess==chessPositionArray[r1][c1]) { //后面棋子和 第一棋子相连
						code.insert(0,chessPositionArray[r1][c1]);
					}else {  //碰到了别的棋子拦截
						code.insert(0,chessPositionArray[r1][c1]);
						break; //结束 棋子相连情况记录
					}
				}
			}
				return new String(code);
		}
	
}
