/**
 * Created by Андрей on 06.05.2014.
 */
public interface Rel {
    // Инициализации массивом объектов с картинками.
    // Потом допишу аргументы...
    public void init();

    // Указываются координаты левого верхнего угла скриншота,
    // ширина и высота выделения
    public void screen(int x, int y, int width, int height);

    // Выполнение обработки
    public void make();

    // Получить имя картинки на экране
    public String getName();
}
