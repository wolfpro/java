package handler;

import java.awt.*;

public class Andr {
    public static void main(String[] args) {
        // Указывается минимальная ширина и высота скриншота
        SnipIt snip = new SnipIt(200, 200);
        // Функция для получения размеров
        Rectangle rect = snip.selectArea();
        System.out.println(rect.x + "x" + rect.y + "x" + rect.width + "x" + rect.height);
    }
}
