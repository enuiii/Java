import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Main {
    
    public static String findLongestUniqueSubstring(String input) {
        int n = input.length();
        int start = 0, end = 0, maxLength = 0;
        int maxStart = 0;
        HashSet<Character> charSet = new HashSet<>();

        while (end < n) {
            if (!charSet.contains(input.charAt(end))) {
                charSet.add(input.charAt(end));
                end++;
                if (end - start > maxLength) {
                    maxLength = end - start;
                    maxStart = start;
                }
            } else {
                charSet.remove(input.charAt(start));
                start++;
            }
        }
        return input.substring(maxStart, maxStart + maxLength);
    }
    
    public static int[] merge(int[] array1, int[] array2) {
        int length1 = array1.length;
        int length2 = array2.length;
        int[] mergedArray = new int[length1 + length2];
        
        int i = 0, j = 0, k = 0;

        while (i < length1 && j < length2) {
            if (array1[i] <= array2[j]) {
                mergedArray[k++] = array1[i++];
            } else {
                mergedArray[k++] = array2[j++];
            }
        }

        while (i < length1) {
            mergedArray[k++] = array1[i++];
        }

        while (j < length2) {
            mergedArray[k++] = array2[j++];
        }

        return mergedArray;
    }

    public static int maxSubarraySum(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        int maxCurrent = arr[0];
        int maxGlobal = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxCurrent = Math.max(arr[i], maxCurrent + arr[i]);
            if (maxCurrent > maxGlobal) {
                maxGlobal = maxCurrent;
            }
        }
        return maxGlobal;
    }

    public static int[][] rotate90Clockwise(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotated[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static int[] findPair(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int num : nums) {
            int complement = target - num;
            if (numMap.containsKey(complement)) {
                return new int[]{complement, num};
            }
            numMap.put(num, num);
        }
        return null; 
    }

    public static int sumOf2DArray(int[][] array) {
        int sum = 0;
        for (int[] row : array) {
            for (int element : row) {
                sum += element;
            }
        }
        return sum;
    }

    public static int[] findMaxInRows(int[][] array) {
        if (array == null || array.length == 0) {
            return new int[0]; 
        }
        int[] maxInRows = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int max = array[i][0];
            for (int j = 1; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
            maxInRows[i] = max;
        }
        return maxInRows; 
    }

    public static int[][] rotateCounterClockwise(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] rotatedMatrix = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedMatrix[cols - j - 1][i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        String inputString = "abcabcbb";
        int[] array1 = {35, 46, 51, 86};
        int[] array2 = {25, 43, 65, 84};
        int[] subArray = {-2, 1, -3, -4, -1, -2, 1, 1, 1, -5};
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        do {
            System.out.println("\nВыберите задание:");
            System.out.println("1. Наибольшая подстрока без повторяющихся символов");
            System.out.println("2. Объединение двух массивов");
            System.out.println("3. Максимальная сумма подмассива");
            System.out.println("4. Поворот матрицы на 90 градусов по часовой стрелке");
            System.out.println("5. Найти пару с заданной суммой");
            System.out.println("6. Сумма всех элементов в 2D массиве");
            System.out.println("7. Максимальные значения в строках 2D массива");
            System.out.println("8. Поворот матрицы на 90 градусов против часовой стрелки");
            System.out.println("0. Выход");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Наибольшая подстрока без повторяющихся символов: " + findLongestUniqueSubstring(inputString));
                    break;
                case 2:
                    int[] mergedArray = merge(array1, array2);
                    System.out.println("Объединенный отсортированный массив: " + Arrays.toString(mergedArray));
                    break;
                case 3:
                    int maxSum = maxSubarraySum(subArray);
                    System.out.println("Максимальная сумма подмассива: " + maxSum);
                    break;
                case 4:
                    System.out.println("Изначальный массив:");
                    printMatrix(matrix);
                    int[][] rotatedMatrix = rotate90Clockwise(matrix);
                    System.out.println("Массив, повёрнутый на 90 градусов по часовой стрелке: ");
                    printMatrix(rotatedMatrix);
                    break;
                case 5:
                    int target = 25; // Задайте нужное значение
                    int[] resultPair = findPair(new int[]{10, 15, 3, 7}, target);
                    if (resultPair != null) {
                        System.out.println("Найдена пара с суммой " + target + ": " + Arrays.toString(resultPair));
                    } else {
                        System.out.println("Пара не найдена.");
                    }
                    break;
                case 6:
                    int totalSum = sumOf2DArray(matrix);
                    System.out.println("Сумма всех элементов в массиве: " + totalSum);
                    break;
                case 7:
                    int[] maxValues = findMaxInRows(matrix);
                    System.out.println("Максимальные значения в строках: " + Arrays.toString(maxValues));
                    break;
                case 8:
                    System.out.println("Изначальный массив:");
                    printMatrix(matrix);
                    int[][] rotatedCCMatrix = rotateCounterClockwise(matrix);
                    System.out.println("Массив, повёрнутый на 90 градусов против часовой стрелки: ");
                    printMatrix(rotatedCCMatrix);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        } while (choice != 0);
        
        scanner.close();
    }
}
