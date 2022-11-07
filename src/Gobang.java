public interface Gobang {
    public static final int size=40; //设置格子的大小为40
    public static final int X=20,Y=20;  //设置棋盘右上角位置
    public static final int row=15; //设置 行数
    public static final int coloum=15; //设置 列数
    // 记录 棋子的位置
    public static final int[][] chessPositionArray=new int[row][coloum];
    //记录 棋盘中 每个位置的权值
    public static final int[][] weightArray=new int[row][coloum];
    //记录 用户 选择哪个 对战模式（flag[0]:人人对战 flag[1]:人机对战 flag[2]:人机 vs 人机）
    public static final boolean[] flag=new boolean[3];
    // 记录 组件上要显示的文字
    public static final String[] messageAeeay= {"开始新游戏","悔棋","认输","对战模式","人人对战",
            "简单人机","困难人机","电脑vs电脑"};
}
