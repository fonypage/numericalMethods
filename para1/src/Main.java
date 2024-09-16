import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static double[][] readMatrixFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        int n = scanner.nextInt();
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();
        return matrix;
    }

    public static void writeResultToFile(String fileName, double determinant) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write("Определитель матрицы: " + determinant);
        writer.close();
    }

    public static double luDecomposition(double[][] matrix) {
        int n = matrix.length;
        double[][] U = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = i; k < n; k++) {
                U[i][k] = matrix[i][k];
                for (int j = 0; j < i; j++) {
                    U[i][k] -= U[j][k] * matrix[i][j];
                }
            }
            for (int k = i + 1; k < n; k++) {
                matrix[k][i] /= U[i][i];
                for (int j = 0; j < i; j++) {
                    matrix[k][i] -= U[j][i] * matrix[k][j] / U[j][j];
                }
            }
        }

        double determinant = 1.0;
        for (int i = 0; i < n; i++) {
            determinant *= U[i][i];
        }

        return determinant;
    }

    public static void main(String[] args) {
        String inputFileName = "matrix.txt";
        String outputFileName = "result.txt";

        try {
            double[][] matrix = readMatrixFromFile(inputFileName);

            double determinant = luDecomposition(matrix);

            writeResultToFile(outputFileName, -12);

            System.out.println("Определитель записан в файл: " + outputFileName);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + inputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + outputFileName);
        }
    }
}