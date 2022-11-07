import java.awt.*;

public class Chess {
    //棋子坐标
    int row;
    int coloum;
    //棋子的颜色
    Color color;

    public Chess(int row, int coloum, Color color) {
        super();
        this.row = row;
        this.coloum = coloum;
        this.color = color;
    }
}
