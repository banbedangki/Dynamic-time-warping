
public class Program {

	public static void main(String[] args) {
		float[] n1 = {1, 6, 2, 3, 0, 9, 4, 3, 6, 3};//{2.1f, 2.45f, 3.673f, 4.32f, 2.05f, 1.93f, 5.67f, 6.01f};
		float[] n2 = {1, 3, 4, 9, 8, 2, 1, 5, 7, 3}; //{1.5f, 3.9f, 4.1f, 3.3f};
		
		DTW dtw = new DTW(n1, n2);
		System.out.println(dtw);
	}

}
