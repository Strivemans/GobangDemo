import java.util.ArrayList;

public class Exam {
	@SuppressWarnings("all")
	public static void main(String[] args) {
		ArrayList list=new ArrayList();
		list.add("jack");
		list.add("zds");
		list.add("zds");
		list.add("zds");
		list.add("zds");
		System.out.println(list.size());
		System.out.println(list.remove(list.size()-1));
		System.out.println(list.size());
	}

}
