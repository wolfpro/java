/**
 * Created by Андрей on 29.05.2014.
 */
public class MainThread extends Thread {

    private ThreeForm threeFormLink;
    private boolean isRunning;

    public MainThread(ThreeForm threeForm) {
        threeFormLink = threeForm;
        isRunning = false;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public void run() {
        while (isRunning) {
            threeFormLink.fun();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
