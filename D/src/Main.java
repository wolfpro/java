import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void main() {
		Scanner sc = new Scanner(System.in);
		int v = Integer.valueOf(sc.nextLine());
		Map<Integer, String> nameV = new HashMap<Integer, String>();
		Map<String, Integer> idV = new HashMap<String, Integer>();

		String s;
		for (int i = 0; i < v; i++) {
			s = sc.nextLine();
			nameV.put(i, s);
			idV.put(s, i);
		}

		int e = Integer.valueOf(sc.nextLine());
		Vector<Vector<String>> naij = new Vector<Vector<String>>();
		Vector<Vector<Integer>> path = new Vector<Vector<Integer>>();

		String[] sm;

		for (int i = 0; i < e; i++) {
			s = sc.nextLine();
			sm = s.split(" ");
			if (naij.isEmpty() || naij.get(idV.get(sm[0])) == null) {
						
			}
		}

	}
}
