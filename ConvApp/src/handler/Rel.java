package handler;
import java.awt.Dimension;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Андрей on 06.05.2014.
 */
public interface Rel {
    // ИНИЦИАЛИЗАЦИЯ
    // Массив путей к картинкам(путь к папке не могу брать из-за
    // того, что у OpenCv нет функций для анализа папок, есть
    // только загрузка из файла)
    public void init(List<Path> imagesPath) throws IOException;

    // Указываются координаты левого верхнего угла скриншота,
    // ширина и высота выделения
    public void screen(int x, int y, int width, int height);

    // Выполнение обработки
    public void make();

    // Получить имя картинки на экране
    public String getName();

    // Получить размеры окна скриншота
    public Dimension getSize();
}
