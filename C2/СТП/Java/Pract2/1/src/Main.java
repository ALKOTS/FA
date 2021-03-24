public class Main {
    public static void main(String[] args)
    {
        UndoableStringBuilder usb= new UndoableStringBuilder();

        System.out.println("Создаем строку");
        usb.append("aa");
        System.out.println(usb.get());
        System.out.println("append bb к предыдущей");
        usb.append("bb");
        System.out.println(usb.get());
        System.out.println("Длина строки");
        System.out.println(usb.length());
        System.out.println("Замена aa на cc");
        usb.replace("cc",0,2);
        System.out.println(usb.get());
        System.out.println("Вставка d на 2 позицию");
        usb.insert("d",1);
        System.out.println(usb.get());
        System.out.println("Удаление 1 элемента");
        usb.delete(0,1);
        System.out.println(usb.get());
        System.out.println("Undo действий");
        usb.undo();
        System.out.println(usb.get());
        usb.undo();
        System.out.println(usb.get());
        usb.undo();
        System.out.println(usb.get());
        usb.undo();
        System.out.println(usb.get());
        usb.undo();
        System.out.println(usb.get());


    }
}
