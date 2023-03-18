package homework;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class SosGame {
	static final String hosgeldinMesajı = "SOS'a hoş geldiniz...";
	static final String dikeyCizgi = "|";
	static final String bilgisayarKarakteri = "O";
	static final String oyuncuKarakteri = "S";
	static final String araCizgi = "*******";
	static final String startMessage = "Oynamak istediğiniz kutucuğun numarasını giriniz :";
	static final String hataMesasage = "Hatalı bir seçim yaptınız lütfen tekrar deneyiniz";
	static final String oyuncuSecimMessage = "Oyuncu Seçimini Yaptı";
	static final String bilgisayarSecimMessage = "Bilgisayar Seçimini Yaptı";
	static final String kazanan = "SOS";
	static final String userWin = "Oyuncu Kazandı";
	static final String pcWin = "Bilgisayar Kazandı";
	static final String DRAW = "Berabere...";
	
	private static void writeLog(String log) {
		try {

			FileWriter yazici = new FileWriter("sos.txt", true); // true : append
			yazici.write(log);
			yazici.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		LocalDateTime tarih = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String log = ("Oyun Başladı  " + format.format(tarih));
		writeLog(log);
		String[] board = {"1","2","3","4","5","6","7","8","9"};
		System.out.println(hosgeldinMesajı);
		drawBoard(board);
		boolean somebodyWin = false;
		int hamle = 0;
		while(true) {
			
			System.out.print(startMessage);
			Scanner input = new Scanner(System.in);
			int userChoice = input.nextInt();
			try {
				if(board[userChoice -1].equals(""+userChoice)) { 
					board[userChoice -1] = oyuncuKarakteri;
					hamle = hamle + 1;
				}
				
			} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(hataMesasage);
					continue;
			}
			drawBoard(board);
			somebodyWin = kazananiBelirle(board, kazanan);
			if(somebodyWin == true) {
				System.out.println(userWin);
				writeLog("\tOyuncu kazandı.\\n");
				break;
			}else if(hamle == 9){
				System.out.println(DRAW);
				writeLog("\tBerabere.\\n");
			}
			
			while(true) {
				Random rand = new Random();
				int sayi = rand.nextInt(9);
				if(board[sayi].equals(""+(sayi+1))){
					board[sayi] = bilgisayarKarakteri;
					System.out.println(bilgisayarSecimMessage);
					hamle = hamle + 1;
					break;
					}
				
			} 
			drawBoard(board);
			
			somebodyWin = kazananiBelirle(board, kazanan);
			if(somebodyWin == true) {
				System.out.println("Oyun bitti...");
				System.out.println(pcWin);
				writeLog("\tBilgisayar kazandı.\\n");
				break;
			}
			else if(hamle == 9){
				System.out.println(DRAW);
				writeLog("\tBerabere.\\n");
			}
			
		}
	}

	private static void drawBoard(String[] board){
		System.out.println(araCizgi);
		System.out.println(dikeyCizgi + board[0] + dikeyCizgi + board[1] + dikeyCizgi + board[2] + dikeyCizgi);
		System.out.println(dikeyCizgi + board[3] + dikeyCizgi + board[4] + dikeyCizgi + board[5] + dikeyCizgi);
		System.out.println(dikeyCizgi + board[6] + dikeyCizgi + board[7] + dikeyCizgi + board[8] + dikeyCizgi);
		System.out.println(araCizgi);
		}

	private static boolean kazananiBelirle(String[] board, String kazanan) {
		
		for (int i = 0; i < 7; i += 3) {
			if ((board[i] + board[i + 1] + board[i + 2]).equals(kazanan)) {
				return true;
			}
		}

		for (int i = 0; i < 3; i++) {
			if ((board[i] + board[i + 3] + board[i + 6]).equals(kazanan)) {
				return true;
			}
		}

		if ((board[0] + board[4] + board[8]).equals(kazanan)
				|| (board[2] + board[4] + board[6]).equals(kazanan)) {
			return true;
		}

		return false;
	}
	
	
}

