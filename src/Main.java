import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StepTracker tracker = new StepTracker();

        Scanner scanner = new Scanner(System.in);
        printMenu();
        int userInput = scanner.nextInt();

        while (userInput != 0) {
            if(userInput == 1) {
                // Ввод количества пройденных шагов в определённый день
                tracker.inputStepInDay();
            }
            else if(userInput == 2) {
                int numberMonth = -1;

                tracker.printAllMonth();
                while (numberMonth < 0 || numberMonth > tracker.monthToData.length) {
                    System.out.print("Введите номер месяца ( от 0 до " + tracker.monthToData.length + ")" +
                            ", по которому вы хотите получить статистику. " +
                            "Для отмены изменения цели введите число \"-999\": ");
                    numberMonth = scanner.nextInt();
                    if(numberMonth == -999) {
                        break;
                    }
                    if(numberMonth < 0 || numberMonth > tracker.monthToData.length) {
                        System.out.println("Введён неверный номер месяца. Повторить ввод!");
                    }
                }
                if(numberMonth != -999) {
                    tracker.printStatistic(numberMonth);
                }
            }
            else if(userInput == 3) {
                // Изменение цели по количеству шагов
                inputGoalSteps(tracker);
            }
            else {
                System.out.println("Введено некорректное значение!!!");
            }
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Программа завершена");
    }

    // Метод для ввода цели прохождения шагов в день
    private static void inputGoalSteps(StepTracker tracker)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество шагов которое вы хотите проходить каждый день. " +
                "Для отмены изменения цели введите число \"-999\".");
        int newGoalSteps = scanner.nextInt();

        // Если ввели некорректное значение
        while (newGoalSteps < 0 && newGoalSteps != -999){
            System.out.println("Вы ввели отрицательное количество шагов. Такая цель невозможна. " +
                    "Для отмены изменения цели введите число \"-999\".");
            System.out.print("Повторный ввод: ");
            newGoalSteps = scanner.nextInt();
        }

        // В случае отмены ничего не изменяем и выходим из метода
        if(newGoalSteps == -999) {
            return;
        }

        // Меняем цель шагов в день
        tracker.goalStepsInDay = newGoalSteps;
    }

    private static void printMenu() {
        System.out.println("Введите цифру, для выбора одного из необходимых Вам пунктов:");
        System.out.println("1.\t Ввести количество шагов за определённый день.");
        System.out.println("2.\t Напечатать статистику за определённый месяц.");
        System.out.println("3.\t Изменить цель по количеству шагов в день.");
        System.out.println("0.\t Выйти из приложения.");
        System.out.print("Ввод: ");
    }
}
