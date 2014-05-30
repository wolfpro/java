import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.CV_TM_SQDIFF;
import static org.bytedeco.javacpp.opencv_imgproc.cvMatchTemplate;

/**
 * Created by Андрей on 29.05.2014.
 */
public class MainThread extends Thread {

    public int delay = 100;
    private ThreeForm threeFormLink;
    private boolean isRunning;
    private IplImage[] lastScreenState;
    private Robot screenshotRobot;
    private Vector<Rectangle> screenAreas;

    public MainThread(ThreeForm threeForm) throws Exception {
        threeFormLink = threeForm;
        isRunning = false;
        // Инициализация объекта для взятия скриншотов экрана
        screenshotRobot = new Robot(GraphicsEnvironment
                .getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        // Первый запуск
        screenAreas = threeFormLink.getScreenAreas();
        lastScreenState = new IplImage[screenAreas.size()];
        for (int i = 0; i < screenAreas.size(); i++) {
            lastScreenState[i] = getMyIplImage(screenshotRobot.createScreenCapture(screenAreas.elementAt(i)));
        }
        threeFormLink.fun();

        // Работа в цикле
        while (isRunning) {
            if (!isTheSame()) {
                threeFormLink.fun();
            }
            try {
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isTheSame() {
        boolean theSame = false;
        for (int i = 0; i < screenAreas.size(); i++) {
            IplImage newScreen = getMyIplImage(screenshotRobot.createScreenCapture(screenAreas.get(i)));
            IplImage result = cvCreateImage(cvSize(1, 1), IPL_DEPTH_32F, 1);
            cvMatchTemplate(lastScreenState[i], newScreen, result, CV_TM_SQDIFF);
            double[] minval = new double[2];
            double[] maxval = new double[2];
            int[] minlock = new int[2];
            int[] maxlock = new int[2];
            cvMinMaxLoc(result, minval, maxval, minlock, maxlock, null);
            if (minval[0] == 0) {
                theSame = true;
            } else {
                lastScreenState[i] = newScreen;
            }
        }
        return theSame;
    }

    private IplImage getMyIplImage(BufferedImage src) {
        BufferedImage img = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(src, 0, 0, null);
        g2d.dispose();
        return IplImage.createFrom(img);
    }

}
