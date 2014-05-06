/**
 * Created by Андрей on 06.05.2014.
 */
public interface Rel {
    // Инициализация: Путь к папке с картинками
    public void init(String folderPath);

    // Указываются координаты левого верхнего угла скриншота,
    // ширина и высота выделения
    public void screen(int x, int y, int width, int height);

    // Выполнение обработки
    public void make();

    // Получить имя картинки на экране
    public String getName();
}
