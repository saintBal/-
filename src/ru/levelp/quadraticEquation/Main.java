package ru.levelp.quadraticEquation;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("введите число а:\t");
            int a = Integer.parseInt(reader.readLine());
            System.out.println("введите число b:\t");
            int b = Integer.parseInt(reader.readLine());
            System.out.println("введите число c:\t");
            int c = Integer.parseInt(reader.readLine());

            double disk = Math.pow(b, 2) - 4 * a * c;

            if (disk < 0) {
                System.out.println("Корней нет");
            } else if (disk == 0) {
                double x = -b / (2 * a);
                System.out.printf("Едиственный корень x= %g\n", x);
            } else {
                double x3 = Math.sqrt(disk);
                double x1 = (-b + x3) / (2 * a);
                double x2 = (-b - x3) / (2 * a);
                System.out.printf("x1= %g\nx2= %g\n", x1, x2);
            }
        } catch (NumberFormatException e) {
            System.err.println("Вводить надо целое натуральное число!");

        } catch (IOException e) {
        }

    }
}
