import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.imgrec.ColorMode;
import org.neuroph.imgrec.FractionRgbData;
import org.neuroph.imgrec.ImageRecognitionHelper;
import org.neuroph.imgrec.ImageRecognitionPlugin;
import org.neuroph.imgrec.image.Dimension;
import org.neuroph.util.TransferFunctionType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 06.05.2014.
 */
// Класс Analyser
// для анализа экрана на наличие изображений.
// Использовать методы объекта этого класса, а не интерфейса Rel

/* ПОКА ЕЩЁ НИЧЕГО НЕ РАБОТАЕТ */

public class Analyser implements Rel {

    private final String NNET_LABEL = "MainNeuralNetwork";
    private NeuralNetwork nnet;
    private Robot screenshotRobot;
    private ImageRecognitionPlugin recognition;
    private String lastFoundName;
    private BufferedImage lastScreenshot;

    public Analyser() throws java.awt.AWTException {
        nnet = null;
        lastFoundName = null;
        // Инициализация объекта для взятия скриншотов экрана
        screenshotRobot = new Robot(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice());
    }

    @Override
    public void init(List<Path> imagesPath) {
        // Получение информации об изображениях
        Dimension dimension = new Dimension(0, 0);
        List<String> imageLabels = new ArrayList<String>();
        List<Integer> layerNeuronsCount = new ArrayList<Integer>();

        // Преобразование изображений в необходимый формат
        Map<String, FractionRgbData> rgbDataMap = null;

        // Создание нейронной сети
        nnet = ImageRecognitionHelper.createNewNeuralNetwork(NNET_LABEL,
                dimension, ColorMode.FULL_COLOR, imageLabels,
                layerNeuronsCount, TransferFunctionType.SIGMOID);

        // Создание набора для тренировки сети и обучение сети на этом наборе
        DataSet trainingSet = ImageRecognitionHelper.createTrainingSet(imageLabels, rgbDataMap);
        nnet.learn(trainingSet);

        recognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.class);

        lastScreenshot = null;
    }

    @Override
    public void screen(int x, int y, int width, int height) {
        lastScreenshot = null;
        lastScreenshot = screenshotRobot.createScreenCapture(new Rectangle(x, y, width, height));
    }

    @Override
    public void make() {
        if (lastScreenshot == null) {
            lastFoundName = null;
            return;
        }
        // Распознование изображения
        HashMap<String, Double> output = recognition.recognizeImage(lastScreenshot);

        // Поиск наилучшего соотвествия
        double maxVal = 0.0;
        String maxLabel = null;
        for (String key : output.keySet()) {
            if (output.get(key) > maxVal) {
                maxVal = output.get(key);
                maxLabel = key;
            }
        }
        lastFoundName = maxLabel;
    }

    @Override
    public String getName() {
        return lastFoundName;
    }
}
