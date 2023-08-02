import java.text.Normalizer;
import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Console console = System.console();
        Scanner scanner = new Scanner(System.in);
        
        char[] themeChars = console.readPassword("Digite o Tema: ");
        String theme = new String(themeChars);

        char[] passwordChars = console.readPassword("Digite a palavra para iniciar o jogo: ");
        String password = new String(passwordChars);

        password = adjustStrings(password);

        char[] wordPlaceholder = new char[password.length()];
        for (int i = 0; i < wordPlaceholder.length; i++) {
            wordPlaceholder[i] = '_';
        }

        boolean gameRunning = true;
        int attempts = 5;

        System.out.println("-----------------------------------");
        System.out.println("Tema: " + theme);
        System.out.println("-----------------------------------");

         while (gameRunning && attempts > 0) {
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Digitar uma letra");
            System.out.println("2 - Chutar a palavra inteira");
            System.out.println("3 - Desistir");
            System.out.println("-----------------------------------");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Digite uma letra: ");
                    char letter = scanner.next().charAt(0);

                    letter = adjustStrings(Character.toString(letter)).charAt(0);

                    boolean letterFound = false;
                    for (int i = 0; i < password.length(); i++) {
                        if (password.charAt(i) == letter) {
                            wordPlaceholder[i] = letter;
                            letterFound = true;
                        }
                    }

                    if (letterFound) {
                        System.out.println("Letra encontrada!");
                    } else {
                        System.out.println("Letra não encontrada!");
                        attempts--;
                        System.out.println("Tentativas restantes: " + attempts);
                    }

                    for (int i = 0; i < wordPlaceholder.length; i++) {
                        System.out.print(wordPlaceholder[i]);
                    }
                    System.out.println("\n\n");
                    break;

                case 2:
                    System.out.print("Escolha uma palavra inteira: ");
                    String guess = scanner.next();

                    guess = adjustStrings(guess);

                    if (guess.equals(password)) {
                        System.out.println("Parabéns, você acertou!");
                        gameRunning = false;
                    } else {
                        System.out.println("Você perdeu!");
                        gameRunning = false;
                    }
                    break;

                case 3:
                    System.out.println("Você desistiu!");
                    gameRunning = false;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        if (attempts == 0) {
            System.out.println("Você excedeu o número máximo de tentativas!");
        }

        System.out.println("Palavra correta: " + password);

        scanner.close();
    }

    public static String adjustStrings(String text) {
        String textWithoutAccents = Normalizer.normalize(text, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        return textWithoutAccents.toLowerCase();
    }
}