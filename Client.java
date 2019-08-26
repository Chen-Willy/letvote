import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    
    public void Csend(String name,int content){

        
    }

//    public void Ccatchy(){
//
//        try{
//
//           
//            System.out.println("connected...(Ccatchy)");
//
//            BufferedReader serverInput = new BufferedReader(new InputStreamReader(cSock.getInputStream()));
//
//            String name = serverInput.readLine();
//            int acnum = Integer.valueOf(serverInput.readLine()) ;
//            int denum = Integer.valueOf(serverInput.readLine()) ;
//
//            cSock.close();
//            serverInput.close();
//
//            this.name = name;
//            this.acnum = acnum;
//            this.denum = denum;
//            
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
    	 String name;
         int acnum;
         int denum;
         Socket cSock;
         Scanner scanner = new Scanner(System.in);
    	try {

            cSock = new Socket("127.0.0.1", 8000);
            System.out.println("connected...(Csend)");
            PrintStream writer;
            writer = new PrintStream(cSock.getOutputStream(), true);
            name = scanner.nextLine();
            acnum = scanner.nextInt();
            denum = scanner.nextInt();
			writer.println(name);
			writer.println(acnum);
			writer.println(denum);
			
            DataOutputStream serverOutput = new DataOutputStream(cSock.getOutputStream());
            serverOutput.writeBytes(name + "\n");
//            serverOutput.writeBytes( Integer.toString(content)+'\n');
            //serverOutput.writeInt(content);


        } 
        catch (IOException e) {
            e.printStackTrace();
        }   
    }
}