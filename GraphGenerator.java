
import java.util.*;

public class GraphGenerator{
	
	public static void genLocal(int clusterNumber, ArrayList<Integer> clusterSize, ArrayList<Double> coEfficient) {
		int number = 0;
		int offset = 0;
		for (int i = 0 ; i < clusterNumber; i++){
			int cnt = 0;
			int curSize = clusterSize.get(i);
			double coe = coEfficient.get(i);

			int map[][] = new int[curSize][];
			for (int j = 0; j < curSize;j++) {
				map[j] = new int[curSize];
			}

			while (cnt < (int)(curSize * curSize * coe)) {
				int k = (int)((Math.random() * curSize));
				int j = (int)((Math.random() * curSize));
				if (map[k][j] == 0 && map[j][k] == 0) {
					map[k][j] = 1;
					map[j][k] = 1;
					cnt++;
				}
			}
			for (int j = 0; j < curSize; j++) {
				for (int k = 0; k < curSize; k++) {
					if (map[j][k] == 1){
						System.out.println((j+offset)+" "+(k+offset));
					}
				}
			}
			offset += clusterSize.get(i);
		}
	}
	
	public static void genSkewed(int n, int degree) {
		ArrayList<Integer> degreeList = new ArrayList<Integer>();
		HashMap<Integer,TreeSet<Integer>> connect = new HashMap<Integer,TreeSet<Integer>>();
		for (int i = 0; i < n; i++){
			degreeList.add(i,(Integer)(i*degree/n));
			connect.put(i,new TreeSet<Integer>());
		}
		for (int i = 0; i < n; i++) {
			TreeSet<Integer> set = connect.get(i);
			int cnt = 0;
			while (cnt < degreeList.get(i)){
				int k = (int)((Math.random()*n));
				TreeSet<Integer> another = connect.get(k);
				if (another.size() > degreeList.get(k)) continue;
				another.add(i);
				connect.put(k,another);
				set.add(k);
				cnt++;
			}
			connect.put(i,set);
		}
		for (int i = 0; i < n; i++){
			TreeSet<Integer> set = connect.get(i);
			for (Integer j:set){
				System.out.println(i+" "+j);
			}
		}
	}
	
	public static void main(String args[]){
//		genSkewed(1000,500);
			ArrayList<Integer> size = new ArrayList<Integer>();
			for (int i = 0; i < 10; i++){
				size.add(100);
			}
			ArrayList<Double> frac = new ArrayList<Double>();
			frac.add(0.01);
			frac.add(0.02);
			frac.add(0.03);
			frac.add(0.05);
			frac.add(0.1);
			frac.add(0.15);
			frac.add(0.20);
			frac.add(0.25);
			frac.add(0.30);
			frac.add(0.35);
			genLocal(10, size, frac);
	}
}