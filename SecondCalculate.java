import java.util.Scanner;

public class SecondCalculate {
	static Client Data = new Client();
	static Scanner YA = new Scanner(System.in);

	public static void main(String[] args) {
		String Name;
		int Y;
		int N;
		
		String P = "Are you fine"; // ¦W¦r
		int L = 0; // start

        Fourrun fourrun = new Fourrun(P, 4);
        fourrun.start();

		while (L == 0) {
			int KEY = YA.nextInt();

			new Thread(new ClientReceiveThread(P, KEY)).start();
			Data.Ccatchy();
			Name = Data.name;
			Y = Data.acnum;
			N = Data.denum;
			System.out.println(Name +" "+ Y + " " + N);
		}

	}

}