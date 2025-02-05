import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Choose the game: \n 1. Baseball, \n 2. Nim, \n 3. Division, \n 4. Division upgrate");
        int choice = in.nextInt();
        switch(choice){
            case 1:
                baseball_game();
            case 2:
                nim_game();
            case 3:
                division_game();
            case 4:
                division_game_upgrate();
            default:
                System.out.println("Try again");
        }
    }

//'baseball game'
//You are keeping the scores for a baseball game with strange rules. At the beginning of the game, you start with an empty record.
//You are given a list of strings operations, where operations[i] is the ith operation you must apply to the record and is one of the following:
//An integer x.
//Record a new score of x. '+'.
//Record a new score that is the sum of the previous two scores. 'D'.
//Record a new score that is the double of the previous score. 'C'.
//Invalidate the previous score, removing it from the record.
//Return the sum of all the scores on the record after applying all the operations.

    public static void baseball_game(){
        Scanner in = new Scanner(System.in);
        Stack<Integer> scores = new Stack<>();

        System.out.println("Enter operations (+, D, C or 'end' to finish):");
        while (true) {
            String operation = in.nextLine();
            if (operation.equals("end")) { // тут равно не напишешь...
                break;
            }

            switch (operation) {
                case "+":
                    if (scores.size() >= 2) {
                        int lastScore = scores.pop(); //удалила последний, чтоб посмотреть предыдущий
                        int secondLastScore = scores.peek(); // смотрю предыдущий
                        scores.push(lastScore); // возвращаю последний
                        scores.push(lastScore + secondLastScore); // суммирую по условию "+"
                    }
                    else {
                        System.out.println("Not enough scores to perform '+' operation");
                    }
                    break;

                case "C":
                    if (scores.size() >= 1) {
                        scores.pop();
                    }
                    else {
                        System.out.println("No scores to invalidate");
                    }
                    break;

                case "D":
                    if (scores.size() >= 1) {
                        scores.push(scores.peek() * 2); // удваиваю последний и добавляю его
                    }
                    else {
                        System.out.println("No scores to double");
                    }
                    break;

                default: // если нет совпадений с другими кейсами, тут у меня был максимальный тупеж
                    try {
                        int score = Integer.parseInt(operation); //  строку в число оказывается
                        scores.push(score); // новыый счет
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Enter a valid score or operation");
                    }
                    break;
            }
        }

        int totalScore = 0;
        while (scores.size() >= 1) {
            totalScore += scores.pop();
        }

        System.out.println("Total Score: " + totalScore);

    }

// 'nim game' - 292
//You are playing the following Nim Game with your friend:
//Initially, there is a heap of stones on the table.
//You and your friend will alternate taking turns, and you go first.
//On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.
//The one who removes the last stone is the winner.
//Given n, the number of stones in the heap, return true if you can win the game assuming both you and your friend play optimally, otherwise return false

    public static void nim_game(){
        Scanner in = new Scanner(System.in);
        int stones = in.nextInt();

            //  в процессе непонимания (stones % 4)
    }

//Alice and Bob take turns playing a game, with Alice starting first.
//Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:
//Choosing any x with 0 < x < n and n % x == 0.
//Replacing the number n on the chalkboard with n - x.
//Also, if a player cannot make a move, they lose the game.
//Return true if and only if Alice wins the game, assuming both players play optimally.

    public static void division_game(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = in.nextInt();
        if (num % 2 == 0){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

    public static void division_game_upgrate() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = in.nextInt();

        boolean isAliceTurn = true; // уступила девочке

        while (num > 1) { // стоп игра, когда число = 1, чья-то победа
            System.out.println("\nCurrent number: " + num);
                if (isAliceTurn) {
                    System.out.println("Alice Turn");
                } else {
                    System.out.println("Bobs Turn");
                }
            System.out.print("Choose x (0 < x < " + num + " и " + num + " % x == 0): ");
            int x = in.nextInt();

            while (x <= 0 || x >= num || num % x != 0) {
                System.out.print("Incorrect number, try again: ");
                x = in.nextInt();
                }

            num -= x;  // Уменьшаем num на выбранный x

            if (num == 1) { // Кто же тут у нас выиграл
                if (isAliceTurn) {
                    System.out.println("Alice is a winner");
                } else {
                    System.out.println("Bob is a winner");
                }
                break;
            }

            isAliceTurn = !isAliceTurn; // Передача хода (помощь всевышнего ума)
        }
    }
}
