import java.util.Scanner;

public class normstr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str2 = sc.nextLine();
        sc.close();
        int strLen=str2.length();
        
        if (strLen>63) {
            str2 = str2.substring(1,63);
        }
        
        str2 = str2.replaceAll(" ", ""); //удаление всех пробелов
        str2 = str2.replaceAll("\n", ""); // удаление всех переносов строки
        str2 = str2.toLowerCase(); // преобразование в нижний регистр
        str2 = str2.trim(); //удаление табуляции с начала и конца строки
        
        System.out.println(str2);		
    }
    
}