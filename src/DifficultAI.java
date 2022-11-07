public class DifficultAI implements Gobang{
    /*
    * 五元组评分算法
    * 对 15*15的 572个五元组分别评分。 一个五元组的得分 是每个位置贡献的分数。
    *                               一个位置的分数 是 所有五元组的分数之和
    * */
    public static void chessWeight(){
        int humanChessmanNum=0;  //五元组中 黑棋数量
        int machineChessmanNum=0; //五元组中 白棋数量
        int tupleScoreTmp=0;  //五元组得分临时变量

        //1. 扫描横向的15个行
        for(int i=0;i<row;i++){
            //遍历 每个五元组 -4是因为 最后4个凑不出五元组
            for(int j=0;j<coloum-4;j++){
                int k=j;
                while(k<j+5){ //保证 循环5次
                    if(chessPositionArray[i][k]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[i][k]==1){
                        humanChessmanNum++; //白棋数
                    }
                    k++;
                }
                tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                //为 五元组的每个位置 添加分数
                for(k=j;k<j+5;k++){
                    weightArray[i][k]+=tupleScoreTmp;
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }

        //2. 扫描 纵向15行
        for(int i=0;i<coloum;i++){
            //遍历 每个五元组 -4是因为 最后4个凑不出五元组
            for(int j=0;j<row-4;j++){
                int k=j;
                while(k<j+5){ //保证 循环5次
                    if(chessPositionArray[k][i]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[k][i]==1){
                        humanChessmanNum++; //白棋数
                    }
                    k++;
                }
                tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                //为 五元组的每个位置 添加分数
                for(k=j;k<j+5;k++){
                    weightArray[k][i]+=tupleScoreTmp;
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }

        //3. 右上角到左下角 上侧部分
        for(int i=14;i>=4;i--){
            for(int k=i,j=0;j<15 && k>=0;j++,k--){
                int m=k;
                int n=j;
                while(m>k-5 && k-5>=-1){ //保证 循环5次
                    if(chessPositionArray[m][n]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[m][n]==1){
                        humanChessmanNum++; //白棋数
                    }
                    m--;
                    n++;
                }
                if(m==k-5){
                    tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                    //为 五元组的每个位置 添加分数
                    for(m=k,n=j;m>k-5;m--,n++){
                        weightArray[m][n]+=tupleScoreTmp;
                    }
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }

        //4. 右上角到左下角 下侧部分
        for(int i=1;i<15;i++){
            for(int k=i,j=14;j>=0 && k<15;j--,k++){
                int m=k;
                int n=j;
                while(m<k+5 && k+5<=15){ //保证 循环5次
                    if(chessPositionArray[n][m]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[n][m]==1){
                        humanChessmanNum++; //白棋数
                    }
                    m++;
                    n--;
                }
                if(m==k+5){
                    tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                    //为 五元组的每个位置 添加分数
                    for(m=k,n=j;m<k+5;m++,n--){
                        weightArray[n][m]+=tupleScoreTmp;
                    }
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }

        //5. 左上角到右上角 上侧部分
        for(int i=0;i<11;i++){
            //遍历 每个五元组 -4是因为 最后4个凑不出五元组
            for(int k=i,j=0;j<15 && k<15;j++,k++){
                int m=k;
                int n=j;
                while(m<k+5 && k+5<=15){ //保证 循环5次
                    if(chessPositionArray[m][n]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[m][n]==1){
                        humanChessmanNum++; //白棋数
                    }
                    m++;
                    n++;
                }
                if(m==k+5){
                    tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                    //为 五元组的每个位置 添加分数
                    for(m=k,n=j;m<k+5;m++,n++){
                        weightArray[m][n]+=tupleScoreTmp;
                    }
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }

        //6. 右上角到左下角 下侧部分
        for(int i=1;i<11;i++){
            //遍历 每个五元组 -4是因为 最后4个凑不出五元组
            for(int k=i,j=0;j<15 && k<15;j++,k++){
                int m=k;
                int n=j;
                while(m<k+5 && k+5<=15){ //保证 循环5次
                    if(chessPositionArray[n][m]==-1){
                        machineChessmanNum++; //黑棋数
                    }else if(chessPositionArray[n][m]==1){
                        humanChessmanNum++; //白棋数
                    }
                    m++;
                    n++;
                }
                if(m==k+5){
                    tupleScoreTmp=tupleScore(humanChessmanNum,machineChessmanNum);
                    //为 五元组的每个位置 添加分数
                    for(m=k,n=j;m<k+5;m++,n++){
                        weightArray[n][m]+=tupleScoreTmp;
                    }
                }
                //置零
                machineChessmanNum=0;
                humanChessmanNum=0;
                tupleScoreTmp=0;
            }
        }


    }

    //五元组 得分表
    public static int tupleScore(int humanChessmanNum,int machineChessmanNum){
        //既有人类落子 又有机器落子 判分为0
        if(humanChessmanNum>0 && machineChessmanNum>0){
            return 0;
        }
        //全部为空 没有落子 判分为7
        if(humanChessmanNum==0 && machineChessmanNum==0){
            return 7;
        }
        //机器落1子 判分35
        if(machineChessmanNum==1){
            return 35;
        }
        //机器落2子 判分800
        if(machineChessmanNum==2){
            return 800;
        }
        //机器落3子 判分15000
        if(machineChessmanNum==3){
            return 15000;
        }
        //机器落4子 80000
        if(machineChessmanNum==4){
            return 80000;
        }
        //人类落1子 判分15
        if(humanChessmanNum==1){
            return 15;
        }
        //人类落2子 判分400
        if(humanChessmanNum==2){
            return 400;
        }
        //人类落3子 判分1800
        if(humanChessmanNum==3){
            return 1800;
        }
        //人类落4子 100000
        if(humanChessmanNum==4){
            return 100000;
        }
        return -1;  //不可能会有其他情况的
    }

}
