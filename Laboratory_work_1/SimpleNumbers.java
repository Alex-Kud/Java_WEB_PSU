import java.util.Scanner;

class SimpleNumbers {
    public static void main(String[] args) {
		int n = 10; 													// Количество элементов в массиве
		int[] numbers = new int[n];
		Scanner in = new Scanner(System.in);
		System.out.println("Enter " + n + " numbers");
		for (int i = 0; i < n; ++i)
			numbers [i] = in.nextInt();
		System.out.println("Simple numbers:");
		for (int i = 0; i < n; ++i){
			if (isSimple(numbers[i]))
				System.out.println(numbers[i]);
		}
   }
   
   // Проверка на простоту числа. Возвращает true, если число простое, возвращает false, если число сложное
   public static boolean isSimple(int num){
		for (int j = 2; j < num; ++j)
			if (num % j == 0)
				return false;
		return true;
   }
}