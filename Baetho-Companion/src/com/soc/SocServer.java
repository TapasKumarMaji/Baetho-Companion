package com.soc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocServer {

	public static void main(String[] args) 
	{
		try 
		{
			ServerSocket ss=new ServerSocket(8080);
			System.out.println("S : Server is waiting for client request");
			while(true)																						
			{
				Socket s=ss.accept();
				
				//Sending data to client side.
				OutputStreamWriter os= new OutputStreamWriter(s.getOutputStream());
				PrintWriter out= new PrintWriter(os,true);													
				BufferedReader br1= new BufferedReader(new FileReader(new File("class.xml")));				
				String line=br1.readLine();
				while(line!=null)																								
				{
					out.println(line.trim());
					line=br1.readLine();
				}																							
					

				
				
				//Getting data from client side.
				BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
				String st=br.readLine();	
				String end=Character.toString(st.charAt(0))+"/"+st.substring(1);														
				System.out.println(st);
				while(true)																												
				{
					String str1=br.readLine();
					if(str1==null) break;
					if(str1.equals(end+st))
					{System.out.println(end);
						break; }
					System.out.println(str1);
				}			
				br.close();
				out.close();
				s.close();
				ss.close();
		}}
		catch(Exception e)
		{
			System.out.println(e.toString());
		} 
	}
 }
