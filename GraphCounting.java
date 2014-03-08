import java.util.*;
import java.io.*;

public class GraphCounting{
	
	public static void myRun(int part) throws Exception{
				String fileName = "node1.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String data = null;
		
		HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Integer, ArrayList<Integer>> degreePool = new HashMap<Integer, ArrayList<Integer>>();
		
		HashMap<Integer,Integer> statAllWedge = new HashMap<Integer,Integer>();
    
		int prev = -1;
		int cnt = 0;
		int nodeCount = 0;
		while((data = br.readLine()) != null){
			String st[] = data.split(" ");
			int x = Integer.parseInt(st[0]);
			int y = Integer.parseInt(st[1]);
			if (x == prev) {
				cnt++;
			}
			else{
				ArrayList<Integer> list;
				int div = (int)(cnt/part);
				if (degreePool.get(div) == null) {
					list = new ArrayList<Integer>();
				}else {
					list = degreePool.get(div);
				}
				list.add(prev);
				degreePool.put(div,list);
				if (statAllWedge.get(div) == null) {
					statAllWedge.put(div,cnt * (cnt +1)/2);
				}else {
					statAllWedge.put(div,statAllWedge.get(div) + cnt * (cnt + 1)/2);
				}
//				System.out.println(prev +" "+cnt);
				cnt = 0;
				prev = x;
				nodeCount++;
			}	
		}
		
		HashMap<Integer, HashSet<Integer>> subDegreePool = new HashMap<Integer, HashSet<Integer>>();
		HashMap<Integer,Integer> sampledPoint = new HashMap<Integer,Integer>();
		for (Integer ii:degreePool.keySet()){
			ArrayList<Integer> list = degreePool.get(ii);
			HashSet<Integer> subSet = new HashSet<Integer>();

			for (int i = 0; i < (int)list.size()/20 ; i++){
				int idx = (int)(Math.random()*list.size());
//				int idx = i;
				int u = list.get(idx);
//				System.out.println(idx);
				subSet.add(u);
				sampledPoint.put(u,ii);
			}
//			System.out.println(subSet);

			subDegreePool.put(ii,subSet);
		}
		
		
		br.close();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		
		while((data = br.readLine()) != null){
			String st[] = data.split(" ");
			int x = Integer.parseInt(st[0]);
			int y = Integer.parseInt(st[1]);
			if (sampledPoint.get(x) != null) {
				int val = sampledPoint.get(x);
				HashSet<Integer> wedge;
				if ((wedge = map.get(x)) == null) {
					wedge = new HashSet<Integer>();				
				}
				wedge.add(y);
				map.put(x,wedge);
			}
		}
		
		br.close();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		
		HashMap<Integer,Integer> statWedge = new HashMap<Integer,Integer>();
		HashMap<Integer,Integer> statTriangle = new HashMap<Integer,Integer>();
		
		while((data = br.readLine()) != null){
			String st[] = data.split(" ");
			int x = Integer.parseInt(st[0]);
			int y = Integer.parseInt(st[1]);
			for (Integer ii : map.keySet()) {
				HashSet<Integer> set = map.get(ii);
				int bin = sampledPoint.get(ii);
				if (set.contains(x) && set.contains(y)){
					if (statTriangle.get(bin) == null){
						statTriangle.put(bin,1);
					}else {
						statTriangle.put(bin,statTriangle.get(bin) + 1);
					}
				}
			}
		}
		sampledPoint.remove(-1);	
//		System.out.println(sampledPoint.keySet().size());	
		for (int i :sampledPoint.keySet()) {
			int bin = sampledPoint.get(i);
			HashSet<Integer> set = map.get(i);
//			System.out.println(i+" "+set.size());
			int len = set.size();
			if (statWedge.get(bin) == null){
						statWedge.put(bin,len * (len - 1)/2);
					}else {
						statWedge.put(bin,statWedge.get(bin) + len * (len - 1)/2);
					}
		}
		int estimatedTriangle = 0; 
//		System.out.println(statAllWedge.keySet().size());
		for (int i = 0; i < statAllWedge.keySet().size(); i++) {
			if (statWedge.keySet().contains(i) && statTriangle.keySet().contains(i)) {
			estimatedTriangle += statAllWedge.get(i) * (statTriangle.get(i) *1.0)/(statWedge.get(i)*1.0);
			System.out.println(statAllWedge.get(i)+" "+statWedge.get(i)+ "  "+statTriangle.get(i) +" "+(statTriangle.get(i) *1.0)/(statWedge.get(i)*1.0));
			}
		}
		System.out.println(estimatedTriangle/6);

	}
	
	public static void main(String args[]) throws Exception{
		for (int i = 0; i < 100; i++){
			myRun(10);
		}
	}
}