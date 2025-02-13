package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
        System.out.println("Hello World!");
    }

    @Override
    public void run() {}
}
