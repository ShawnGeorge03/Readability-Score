import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in).useDelimiter(System.lineSeparator());

    public String loadText() {
        System.out.println("Welcome to the Readability Score Generator");
        System.out.println("Enter which method the text will be provided (-file  or -input)");
        System.out.println("-file : A file path from your computer");
        System.out.println("-input : User input text into the console \n");

        String text = "";
        while (text.isEmpty()) {
            String option = scanner.next();

            if (option.equals("-file")) {
                text = readFromFile();
            } else if (option.equals("-input")) {
                System.out.print("Enter text :");
                Scanner input = new Scanner(System.in);
                text = input.nextLine();
                input.close();
            } else {
                System.out.println("Invalid option! Please try again!");
            }
        }
        return text.trim();
    }

    private String readFromFile() {
        StringBuilder text = new StringBuilder();
        while(text.length() == 0) {
            System.out.print("Enter valid file path :");
            String path = scanner.next();

            try {
                Scanner input = new Scanner(new File(path));
                while (input.hasNext()) {
                    text.append(input.nextLine());
                }
                input.close();
            } catch (FileNotFoundException e) {
                System.out.println("Invalid File Path! Please try again!");
            }
        }
        return text.toString();
    }


}
