import java.util.*;
import java.io.*;

public class DegreeCounter{

	public static void main(String args[]) throws Exception{
		String fileName = args[1];
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String data = null;
		while((data = br.readLine()) != null){
			String st[] = data.split(" ");
			int x = Integer.parseInt(st[0]);
			int y = Integer.parseInt(st[1]);
			set.get(x).add(y);
		}