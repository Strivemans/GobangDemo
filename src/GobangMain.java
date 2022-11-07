import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GobangMain extends JPanel implements Gobang{
    private ArrayList<Chess> chessArray=new ArrayList<>(); // 存放 棋子数组

    public static void main(String[] args) {
        GobangMain a=new GobangMain();
        a.InitUI();
    }

    //事件监听器
    GobangListener gl=new GobangListener(this,chessArray);

    JPanel eastPanel=new JPanel();  // 东面的选择面板
    ButtonGroup bg=new ButtonGroup();  //单选框组

    //构建五子棋 面板
    public void InitUI() {
        JFrame frame=new JFrame("五子棋"); //容器
        frame.setSize(780,635); //容器大小
        //设置 容器点击关闭按钮时 删除的情况 3：点击关闭图标 同时关闭程序
        frame.setDefaultCloseOperation(3);
        //设置 容器的相对位置 null：默认居中
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);  //窗体大小 是否可以被用户改变
        frame.setLayout(new BorderLayout()); //设置 布局格式

        this.setBackground(Color.LIGHT_GRAY); //设置 棋盘面板背景颜色

        //给东边面板设置布局
        eastPanel.setLayout(new FlowLayout());
        //给东边面板 设置 大小
        eastPanel.setPreferredSize(new Dimension(150, 0));

        //设置按钮
        for(int i=0;i<messageAeeay.length;i++) {
            if(i<3) {
                //创建按钮
                JButton button=new JButton(messageAeeay[i]);
                //设置按钮 大小
                button.setPreferredSize(new Dimension(140, 80));
                //添加按钮
                eastPanel.add(button);
                //添加事件
                button.addActionListener(gl);
            }
            if(i==3) {
                //设置 标签
                JLabel label=new JLabel(messageAeeay[i]);
                eastPanel.add(label);
            }
            if(i>3) {
                //设置 复选框按钮
                JRadioButton button=new JRadioButton(messageAeeay[i]);
                //取消被默认选中
                button.setSelected(false);
                bg.add(button); //加入 按钮组
                eastPanel.add(button);
                //添加事件
                button.addActionListener(gl);
            }
        }
        // 将棋盘面板 添加到窗体的中间部位
        frame.add(this,BorderLayout.CENTER);
        // 设置 东面面板 加进窗体
        frame.add(eastPanel,BorderLayout.EAST);
        frame.setVisible(true); //设置 窗体 是否可见
    }

    //用画笔类 来 绘制 棋盘 Graphics:画笔类
    //paint 类是自动系统自动调用的 类名不能写错
    public void paint(Graphics g) {
        super.paint(g);  //在原来画像的基础上，再画图
        for(int i=0;i<coloum;i++) {
            g.drawLine(X,Y+size*i,X+size*(coloum-1),Y+size*i);
            g.drawLine(X+size*i,Y,X+size*i,Y+size*(coloum-1));
        }
        //画棋子
            for(int i=0;i<chessArray.size();i++){
                Chess e=chessArray.get(i);
                g.setColor(e.color);
                //画相对应棋子的 椭圆棋子 填充颜色
//            x- 要填充椭圆的左上角的 x 坐标。
//               y - 要填充椭圆的左上角的 y 坐标。
//              width - 要填充椭圆的宽度。
//             height - 要填充椭圆的高度。
                g.fillOval(X+e.row*size-size/2,Y+e.coloum*size-size/2,size,size);
                if(i==chessArray.size()-1){
                    g.setColor(Color.RED);
                    g.drawRect(X+e.row*size-size/2,Y+e.coloum*size-size/2,size,size);
                }
            }
    }
}
