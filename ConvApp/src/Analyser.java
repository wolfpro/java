import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_highgui.cvLoadImage;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_SQDIFF;
import static org.bytedeco.javacpp.opencv_imgproc.cvMatchTemplate;

/**
 * Created by Андрей on 06.05.2014.
 */
// Класс Analyser
// для анализа экрана на наличие изображений.
// Использовать методы объекта этого класса, а не интерфейса Rel

public class Analyser implements Rel {

    private Robot screenshotRobot;
    private String lastFoundName;
    private IplImage lastScreenshot;
    private IplImage[] templates;
    private String[] labels;
    private int iw, ih, tw, th;

    public Analyser() throws java.awt.AWTException {
        lastFoundName = null;
        iw = 0;
        ih = 0;
        tw = 0;
        th = 0;
        // Инициализация объекта для взятия скриншотов экрана
        screenshotRobot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }

    @Override
    public void init(List<Path> imagesPath) throws IOException {
        templates = new IplImage[imagesPath.size()];
        labels = new String[imagesPath.size()];
        // Получение информации об изображениях
        int i = 0;
        for (Path imgPath : imagesPath) {
            String filename = imgPath.toString();
            templates[i] = cvLoadImage(filename);
            int f = filename.lastIndexOf('/');
            if (f == -1) {
                f = filename.lastIndexOf('\\');
            }
            f++;
            int l = filename.lastIndexOf('.');
            labels[i] = filename.substring(f, l);
            if (tw == 0) {
                tw = templates[i].width();
                th = templates[i].height();
            }
            i++;
        }

    }

    @Override
    public void screen(int x, int y, int width, int height) {
        lastScreenshot = null;
        lastScreenshot = IplImage.createFrom(screenshotRobot.createScreenCapture(new Rectangle(x, y, width, height)));
        iw = lastScreenshot.width();
        ih = lastScreenshot.height();
    }

    public void checkImage(String filename) {
        lastScreenshot = null;
        lastScreenshot = cvLoadImage(filename);
        iw = lastScreenshot.width();
        ih = lastScreenshot.height();
    }

    @Override
    public void make() {
        int minIndex = -1;
        double minValue = 0;
        for (int i = 0; i < templates.length; i++) {
            IplImage result = cvCreateImage(cvSize(iw - tw + 1, ih - th + 1), IPL_DEPTH_32F, 1);
            cvMatchTemplate(lastScreenshot, templates[i], result, CV_TM_SQDIFF);

            double[] minval = new double[2];
            double[] maxval = new double[2];
            int[] minlock = new int[2];
            int[] maxlock = new int[2];
            cvMinMaxLoc(result, minval, maxval, minlock, maxlock, null);

            if (i == 0) {
                minValue = minval[0];
                minIndex = 0;
            } else if (minval[0] < minValue) {
                minValue = minval[0];
                minIndex = i;
            }
        }
        lastFoundName = labels[minIndex];
    }

    @Override
    public String getName() {
        return lastFoundName;
    }

    @Override
    public Dimension getSize() {
        return new Dimension(tw, th);
    }
}