package first_task;

import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException
	   {
	      InputStreamReader cin = null;
	      int iter=0;
	      char mas[]=new char[63];
	      try {
	         cin = new InputStreamReader(System.in);
	         System.out.println("Enter characters, 'q' to quit.");
	         char c;
	         do {
	            c = (char) cin.read();
	            mas[iter]=c;
	            iter++;
	         } while(c != 'q' && iter < 63);
	      } finally {
	         if (cin != null) {
	            cin.close();
	         }
	      }
	      String str=new String(mas);
	      str = str.replaceAll(" ", ""); //удаление всех пробелов
	      str = str.replaceAll("\n", ""); // удаление всех переносов строки
	      str = str.toLowerCase(); // преобразование в нижний регистр
	      str = str.trim(); //удаление табуляции с начала и конца строки
	        
	        System.out.println(str);		
	   }
	   
}

