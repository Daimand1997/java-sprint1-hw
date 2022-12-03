import java.util.Arrays;
import java.util.Scanner;

public class StepTracker {
    String[] nameAllMonth = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь",
        "Октябрь", "Ноябрь", "Декабрь"};
    static int goalStepsInDay = 10000;
    MonthData[] monthToData;

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
            monthToData[i].nameMonth = nameAllMonth[i];
        }
    }

    public void printStatistic(int numberMonth) {
        // P.s. реализовал в отдельных методах, а не в одном цикле,
        // чтобы можно было отдельно переиспользовать в будущем любой пункт статистики.

        // 1. Вывод количество пройденных шагов за каждый день в месяце
        printCountStepInMonth(monthToData[numberMonth]);

        // 2. Вывод общего количества шагов за месяц
        System.out.println("Общее количество пройденных шагов за месяц: " +
                sumStepsInMonth(monthToData[numberMonth]) + " шага(ов).");

        // 3. Вывод максимальное количество шагов пройденое в месяце
        System.out.println("Максимальное количество пройденных шагов в месяце: " +
                maxStepsInMonth(monthToData[numberMonth]) + " шага(ов).");

        // 4. Вывод среднего количества пройденных шагов за месяц
        System.out.println("Среднее количество пройденных шагов за месяц: " +
                (sumStepsInMonth(monthToData[numberMonth]) / monthToData[numberMonth].countStepInDay.length) +
                " шага(ов).");

        // 5. Вывод пройденной дистанция (в км)
        System.out.println("Пройденная дистанция (в км): " +
                Converter.countDistance(sumStepsInMonth(monthToData[numberMonth])) + " км.");

        // 6. Вывод количество сожжённых килокалорий
        System.out.println("Количество сожжённых килокалорий: " +
                Converter.burnedCalories(sumStepsInMonth(monthToData[numberMonth])) + " ккал.");

        // 6. Вывод лучшей серии
        System.out.println("Количество дней подряд, где количество шагов превышало целевое значение: " +
                countBestDays(monthToData[numberMonth]));
    }


    // Метод для нахождения максимального количества шагов проденных за день в месяце
    public int maxStepsInMonth(MonthData monthData) {
        int maxSteps = monthData.countStepInDay[0];
        for(int i = 0; i < monthData.countStepInDay.length; i++) {
            if(maxSteps < monthData.countStepInDay[i]) {
                maxSteps = monthData.countStepInDay[i];
            }
        }
        return maxSteps;
    }

    // Метод для подсчёта общего количества шагов за месяц
    public int sumStepsInMonth(MonthData monthData) {
        int countsStep = 0;
        for(int i = 0; i < monthData.countStepInDay.length; i++) {
            countsStep += monthData.countStepInDay[i];
        }
        return  countsStep;
    }

    // Метод для вывода количесто шагов пройденных за месяц
    public void printCountStepInMonth(MonthData monthData) {
        System.out.println("1.\t Количество пройденных шагов по дням:");
        for(int i = 0; i < monthData.countStepInDay.length; i++) {
            String strFromPrint = (i + 1) + " день: " +  monthData.countStepInDay[i];
            if(i + 1 != monthData.countStepInDay.length) {
                strFromPrint += ", ";
            }
            System.out.print(strFromPrint);
        }
        System.out.println("");
    }

    // Метод для ввода количества пройденных шагов в определённый день месяца.
    public void inputStepInDay() {
        Scanner scanner = new Scanner(System.in);
        int numberMonth = -1;
        int numberDay = -1;
        int countSteps = -1;

        printAllMonth();
        while (numberMonth < 0 || numberMonth > monthToData.length - 1) {
            System.out.print("Введите номер месяца (от 0 до " + (monthToData.length - 1) + ") " +
                    "в котором хотите изменить количество шагов. Для отмены введите число \"-999\": ");
            numberMonth = scanner.nextInt();
            if(numberMonth == -999) {
                return;
            }
            if(numberMonth < 0 || numberMonth > monthToData.length - 1) {
                System.out.println("Номер месяца должен быть в диапозоне от 0 до " + (monthToData.length - 1));
            }
        }
        while (numberDay < 1 || numberDay > monthToData[numberMonth].countStepInDay.length) {
            System.out.print("Введите порядковый номер дня месяца " +
                    "(от 1 до " + (monthToData[numberMonth].countStepInDay.length) + "), " +
                    "в котором хотите изменить количество шагов. Для отмены введите число \"-999\": ");

            numberDay = scanner.nextInt();
            if(numberDay == -999) {
                return;
            }
            if(numberDay < 1 || numberDay > monthToData[numberMonth].countStepInDay.length) {
                System.out.println("Номер дня должен быть в диапозоне от 1 до "
                        + monthToData[numberMonth].countStepInDay.length);
            }
        }
        while (countSteps < 0) {
            System.out.print("Введите количество шагов пройденных в этот день. Для отмены введите число \"-999\": ");
            countSteps = scanner.nextInt();
            if(countSteps == -999) {
                return;
            }
            if(countSteps < 0) {
                System.out.println("Количество шагов не должно быть отрицательным!!!");
            }
        }

        monthToData[numberMonth].countStepInDay[numberDay - 1] = countSteps;
    }

    // Метод для вывода наименования месяцев
    public void printAllMonth() {
        System.out.println("Номера месяцев:");
        for(int i = 0; i < monthToData.length; i++) {
            System.out.println(i + " - " + monthToData[i].nameMonth);
        }
    }

    // Метод для получения количество подряд дней где значение было выше целевого
    public int countBestDays(MonthData monthData) {
        int countDay = 0;
        int oldCountDay = 0;
        for(int i = 0; i < monthData.countStepInDay.length; i++) {
            if(monthData.countStepInDay[i] > goalStepsInDay) {
                countDay++;
            }
            else if(oldCountDay < countDay) {
                oldCountDay = countDay;
                countDay = 0;
            }
            else {
                countDay = 0;
            }
        }
        if(oldCountDay < countDay) {
            return countDay;
        }
        return oldCountDay;
    }

    class MonthData {
        int[] countStepInDay = new int[30];
        String nameMonth = "";
        public MonthData() {
            Arrays.fill(countStepInDay, 0);
        }
    }
}