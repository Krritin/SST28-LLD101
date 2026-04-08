package com.example.pen;

public class App {

    public static void main(String[] args) {

        System.out.println("--- cap pen ---");
        Pen cap = new CapPen(new Refill("Blue"));
        cap.write("test");          // should say pen is closed
        cap.open();
        cap.write("Hello World");
        cap.shut();

        cap.changeRefill(new Refill("Red"));
        cap.open();
        cap.write("important note");
        cap.shut();

        System.out.println("\n--- click pen ---");
        Pen click = new ClickPen(new Refill("Black"));
        click.open();
        click.write("quick note");
        click.shut();

        click.changeRefill(new Refill("Green"));
        click.open();
        click.write("signature");
        click.shut();
    }
}
