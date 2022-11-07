public class Judge implements Gobang{
	int count; //判断 连接棋子的总数
	
	//调用 构造器 
	public boolean judge(int x,int y) {
		if(JudgeWinner(x,y)>=5) {
			return true;
		}
		return false;
	}
	
	/*五子棋 输赢判定方法
	 *  主要分为 上下 左右 斜线 三个方向
	 *  x:左右 y：上下
	 * */
	private int JudgeWinner(int x,int y) {
		count=1;
		//判断左右
			//从左到右
		for(int i=x+1;i<row;i++) {
			if(chessPositionArray[x][y]==chessPositionArray[i][y]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
			//从右到左
		for(int i=x-1;i>=0;i--) {
			if(chessPositionArray[x][y]==chessPositionArray[i][y]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		if(count==5) {
			return count;
		}
		count=1;// 没有形成五子棋 则重新计算
		
		//判断上下  分别有两条斜线 左上右下  左下右上 
			//判断 从上到下（左上右下）
		for(int i=y-1;i>=0;i--) {
			if(chessPositionArray[x][y]==chessPositionArray[x][i]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		//判断 从下到上
		for(int i=y+1;i<coloum;i++) {
			if(chessPositionArray[x][y]==chessPositionArray[x][i]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		if(count==5) {
			return count;
		}
		count=1;// 没有形成五子棋 则重新计算
		
		
		//判断斜线
			//判断左上斜线
		for(int i=x-1,j=y+1;i>=0&&j<coloum;i--,j++) {
			if(chessPositionArray[x][y]==chessPositionArray[i][j]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
			//判断右下斜线
		for(int i=x+1,j=y-1;i<row&&j>=0;i++,j--) {
			if(chessPositionArray[x][y]==chessPositionArray[i][j]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		if(count==5) {
			return count;
		}
		count=1;// 没有形成五子棋 则重新计算
		
		// 判断第二条斜线  左下 右上
			//判断 右上斜线
		for(int i=x+1,j=y+1;i<row&&j<coloum;i++,j++) {
			if(chessPositionArray[x][y]==chessPositionArray[i][j]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		//判断 左下斜线
		for(int i=x-1,j=y-1;i>=0&&j>=0;i--,j--) {
			if(chessPositionArray[x][y]==chessPositionArray[i][j]) {  //有棋子
				count++;
			}else{
				break;  //没有棋子直接跳出循环   
			}
		}
		if(count==5) {
			return count;
		}
		
		return count;
	}
}
