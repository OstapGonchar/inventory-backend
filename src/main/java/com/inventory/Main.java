package com.inventory;

public class Main {
    public static void main(String[] args) {
        try {
            riskyMethod();
        } catch (ArithmeticException e) {
            System.out.println("Caught an ArithmeticException: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Caught a NullPointerException: " + e.getMessage());
        } finally {
            System.out.println("This finally block will always be executed.");
        }
    }

    public static void riskyMethod() {
        int number = (int)(Math.random() * 3);
        switch(number) {
            case 0:
                int result = 10 / number; // This will throw ArithmeticException if number is 0
                break;
            case 1:
                String str = null;
                str.length(); // This will throw NullPointerException
                break;
            case 2:
                System.out.println("No exception thrown");
                break;
        }
    }
}
