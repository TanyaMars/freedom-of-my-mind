import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        baseball_game();

    }
//Problem 'baseball game'
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
}