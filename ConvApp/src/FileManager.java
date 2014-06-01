import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FileManager {
    final String dirname = "ConvApp/examples";
    char c = '/';// windows mod!!! linux;
    File fl;
    Vector<String> rl;

    FileManager() {
        fl = new File(dirname);
    }

    Vector<String> list() throws Exception {
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

    Vector<Rectangle> getCor() throws Exception {
        Vector<Rectangle> ans = new Vector<Rectangle>();
        File f = new File("data");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String s;
        String[] st;
        while ((s = reader.readLine()) != null) {
            if (!s.equals("you not clicked")) {
                st = s.split(" ");
                Rectangle area = new Rectangle(Integer.valueOf(st[0]), Integer.valueOf(st[1]),
                        Integer.valueOf(st[2]), Integer.valueOf(st[3]));
                ans.add(area);
                System.out.println(area.toString());
            }

        }
        return ans;
    }

    public List<Path> getImages() throws Exception {
        Path path = FileSystems.getDefault().getPath(dirname);
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
