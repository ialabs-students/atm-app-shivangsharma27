import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMApp {

    public static HashMap<Integer, Integer> balance = new HashMap<>(
            Map.of(2000, 0,
                    500, 0,
                    200, 0,
                    100, 0)
    );

    static int[] denominations = {100, 200, 500, 2000};


    public static void depositAmount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of currency notes in each denominations");
        System.out.print("2000s : "); int c2000 = sc.nextInt();
        System.out.print("500s : "); int c500 = sc.nextInt();
        System.out.print("200s : "); int c200 = sc.nextInt();
        System.out.print("100s : "); int c100 = sc.nextInt();

        if(c2000 < 0 || c500 < 0 || c200 < 0 || c100 < 0){
            System.out.println("Please enter correct deposit amount");
            return;
        }

        balance.put(2000, balance.get(2000) + c2000);
        balance.put(500, balance.get(500) + c500);
        balance.put(200, balance.get(200) + c200);
        balance.put(100, balance.get(100) + c100);

        getBalance();
    }

    public static void withdrawAmount(){
        int withdraw, temp;
        int count2000s = 0, count500s = 0, count200s = 0, count100s = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount to be withdrawn : ");
        withdraw = sc.nextInt();
        temp = withdraw;

        if(withdraw > getTotalAmount() || withdraw <= 0){
            System.out.println("Incorrect or Insufficient funds");
            return;
        }

        int n = denominations.length;

        ArrayList<Integer> values = new ArrayList<>();

        for(int i = n-1; i>=0; i--){
            while (temp >= denominations[i])
            {
                temp -= denominations[i];
                values.add(denominations[i]);

            }
        }

        for (Integer value : values) {
            if (value == 2000) {
                count2000s++;
            } else if (value == 500) {
                count500s++;
            } else if (value == 200) {
                count200s++;
            } else if (value == 100) {
                count100s++;
            }
        }

        String dispensed = "Dispensed : ";
        if(count2000s > 0){
            dispensed += "2000s = " + count2000s;
        }
        if(count500s > 0){
            dispensed += " 500s = " + count500s;
        }
        if(count200s > 0){
            dispensed += " 200s = "+ count200s;
        }
        if(count100s > 0){
            dispensed += " 100s = " + count100s;
        }

        balance.put(2000, balance.get(2000) - count2000s);
        balance.put(500, balance.get(500) - count500s);
        balance.put(200, balance.get(200) - count200s);
        balance.put(100, balance.get(100) - count100s);

        System.out.println(dispensed);
        getBalance();
    }

    public static void getBalance(){
        System.out.println("Balance : 2000s="+balance.get(2000)+", 500s="+balance.get(500)+
                ", 200s="+balance.get(200)+", 100s="+balance.get(100)+", Total="+getTotalAmount());
    }

    public static int getTotalAmount(){
        int totalAmount = 2000 * balance.get(2000) + 500 * balance.get(500)
                + 200 * balance.get(200) + 100 * balance.get(100);
        return totalAmount;
    }

    public static void main(String[] args) {

        int choice;
        do {
            System.out.println("Welcome to ABC ATM\n" +
                    "1. Deposit specific amount\n" +
                    "2. Withdraw amount\n" +
                    "3. Exit");
            System.out.print("Please enter your choice : ");

            Scanner sc = new Scanner(System.in);
             choice = sc.nextInt();

            switch (choice) {
                case 1:
                    depositAmount();
                    break;
                case 2:
                    withdrawAmount();
                    break;
                case 3:
                    System.exit(0);

                default:
                    System.out.println("Please enter correct choice");
            }

        }while(true);

    }
}
