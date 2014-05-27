import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

	Vector<Dimension> getCor() throws NumberFormatException, IOException {
		Vector<Dimension> ans = new Vector<Dimension>();
		File f = new File("data");
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String s;
		String[] st;
		while ((s = reader.readLine()) != null) {
			if (!s.equals("you not clicked")) {
				st = s.split(" ");
				ans.add(new Dimension(Integer.valueOf(st[0]), Integer
						.valueOf(st[1])));
				System.out.println(st[0] + " " + st[1]);
			}

		}
		return ans;
	}
	
	 public List<Path> getImages() throws IOException {
		 Path path = new File("examples/").toPath();
	        List<Path> images = new ArrayList<Path>();
	        DirectoryStream<Path> ds = Files.newDirectoryStream(path);
	        for (Path child : ds) {
	            String name = child.getFileName().toString().toLowerCase();
	            if (name.endsWith(".png") || name.endsWith(".jpg")
	                    || name.endsWith(".jpeg") || name.endsWith(".bmp")
	                    || name.endsWith(".ico")) {
	                images.add(child);
	            }
	        }
	        return images;
	    }
}
