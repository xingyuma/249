import java.util.*;
import java.io.*;

public class AccurateCounter{

	public static void countDegree(ArrayList<HashSet<Integer>> set) {
		for (int i = 0; i < set.size(); i++){
			System.out.println(set.get(i).size());
		}
	}
	
	public static int countTriangle(ArrayList<HashSet<Integer>> set){
		int cnt = 0;
		for (int i = 0; i < 1000; i++) {
			for (int j = i + 1; j < 1000; j++) {
				for (int k = j + 1; k < 1000; k++) {
					if (set.get(i).contains(j) && set.get(i).contains(k) && 
					set.get(j).contains(k)) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}

	public static void main(String args[]) throws Exception{
		System.out.println(args.length);
		String fileName = args[1];
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String data = null;
		ArrayList<HashSet<Integer>> set = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < 1000; i++){
			set.add(new HashSet<Integer>());
		}
		while((data = br.readLine()) != null){
			String st[] = data.split(" ");
			int x = Integer.parseInt(st[0]);
			int y = Integer.parseInt(st[1]);
			set.get(x).add(y);
		}
		countDegree(set);
	}
}