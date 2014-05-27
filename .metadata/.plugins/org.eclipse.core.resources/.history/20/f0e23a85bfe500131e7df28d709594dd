import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileManager {
	char c = '/';// windows mod!!! linux;
	File fl;
	Vector<String> rl;
	String dirname = "examples/";

	FileManager() {
		fl = new File(dirname);
	}

	Vector<String> list() throws IOException {
		rl = new Vector<String>();

		if (fl.isDirectory()) {
			String s[] = fl.list();
			for (int i = 0; i < s.length; i++) {
				rl.add(new File(fl.getAbsolutePath() + c + s[i])
						.getAbsolutePath());
				System.out.println(rl.lastElement());
			}
		}
		return rl;
	}

	File getimg(int i) {
		String s[] = fl.list();
		File f = new File(dirname + "/" + s[i]);
		return f;
	}
	
	 Vector<Dimension> getCor() throws NumberFormatException, IOException{
		Vector<Dimension> ans= new Vector<Dimension>();
		File f = new File("data");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String s;
		String[] st;
		while((s=reader.readLine())!=null){
			if(!s.equals("you not clicked")){
				st=s.split(" ");
			ans.add(new Dimension(Integer.valueOf(st[0]), Integer.valueOf(st[1])));
			}
		}
		return ans;
	}

}
