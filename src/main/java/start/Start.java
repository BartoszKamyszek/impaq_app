package main.java.start;

import main.java.engine.Engine;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Start {

    public Start() throws ParserConfigurationException, SAXException, IOException {

        Engine engine = new Engine();
        System.out.println("To finish transaction, write exit in console");
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Scan barcode:");
            engine.startEngine(sc.next());
        }
        while (true);

    }
}
