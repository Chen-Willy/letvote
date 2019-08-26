public class Fourrun extends Thread {
	static Client Data = new Client();
	static String P;
    static int KEY;
    static String Name;
	static int Y;
    static int N;

	public Fourrun(String P, int KEY) {
		this.P = P;
		this.KEY = KEY;

	}

	public void run() {
        
        while(true){
            Data.Csend(P, KEY);
            Data.Ccatchy();
			Name = Data.name;
			Y = Data.acnum;
			N = Data.denum;
			System.out.println(Name +" "+ Y + " " + N);
            
            try{
                this.sleep(5000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
		

	}

}