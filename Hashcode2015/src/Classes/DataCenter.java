package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DataCenter {
	private int pools;
	private int numOfServers;
	private LinkedList<Server> servers;
	private int rows;
	private int slotPerRows;
	private int slotUnavailable;
	
	private int getPools() {
		return pools;
	}

	private void setPools(int pools) {
		this.pools = pools;
	}

	private int getNumOfServers() {
		return numOfServers;
	}

	private void setNumOfServers(int numOfServers) {
		this.numOfServers = numOfServers;
	}

	private int getRows() {
		return rows;
	}

	private void setRows(int rows) {
		this.rows = rows;
	}

	private int getSlotPerRows() {
		return slotPerRows;
	}

	private void setSlotPerRows(int slotPerRows) {
		this.slotPerRows = slotPerRows;
	}

	private int getSlotUnavailable() {
		return slotUnavailable;
	}

	private void setSlotUnavailable(int slotUnavailable) {
		this.slotUnavailable = slotUnavailable;
	}

	private FileReader fileConfigurazione,fileInput;
	private BufferedReader brConf,brInput;
	
	public DataCenter() throws Exception {
		servers = new LinkedList<Server>();
		System.err.println("Inizio Lettura");
		LeggiFileConfigurazione();
		LeggiFileInput();
		System.out.println("Fine Lettura");
		
		System.err.println("Stampa Server");
		stampaServer();
		System.err.println("Calcolo Score");
		CalcoloScore();
	}

	private void stampaServer() {
		// TODO Auto-generated method stub
		for (Server server : servers) {
			System.out.println(server.toString());
		}
	}

	private void LeggiFileConfigurazione() throws Exception {
		// TODO Auto-generated method stub
		File f=new File("conf.txt");
		System.out.println(f.getCanonicalPath());
		fileConfigurazione=new FileReader(f.getCanonicalPath());
		brConf =new BufferedReader(fileConfigurazione);
		

	    String line1;
	    line1=brConf.readLine();
	    System.out.println(line1);
	    String[] s1=line1.split(" ");
	    
	    setRows(Integer.parseInt(s1[0]));
	    setSlotPerRows(Integer.parseInt(s1[1]));
	    setSlotUnavailable(Integer.parseInt(s1[2]));
	    setPools(Integer.parseInt(s1[3]));
	    setNumOfServers(Integer.parseInt(s1[4]));
	    
	    line1=brConf.readLine();
	    int i=0;
	    while(true) {
	      line1=brConf.readLine();
	      if(line1==null)
		        break;
	      System.out.println(line1);
	      
	      s1=line1.split(" ");
	      Server server=new Server(i, Integer.parseInt(s1[0]),Integer.parseInt(s1[1]));	      
	      servers.add(server);
	      i++;
	    }
	}

	private void LeggiFileInput() throws Exception {
		// TODO Auto-generated method stub
		String line2;
		File f=new File("input.txt");
		System.out.println(f.getCanonicalPath());
		fileInput=new FileReader(f.getCanonicalPath());
		brInput =new BufferedReader(fileInput);
		
		
		
	    int i=0;
	    while(true) {
	    	line2=brInput.readLine();
	    	if(line2==null)
				break;
			String[] s2=line2.split(" ");
			
			System.out.println(line2);
			if (s2[0].contains("x")){
				servers.get(i).setDescr("not allocated");
				servers.get(i).setIdPool(-1);
				}
			else
				servers.get(i).setIdPool(Integer.parseInt(s2[2]));
			i++;

	    }
	}
	
	private void CalcoloScore() {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> Pools= new HashMap<Integer, Integer>();;
		for (Server serv : servers) {
			if (serv.getIdPool()>=0)
				if (Pools.get(serv.getIdPool())!=null)
					if(serv.getCapacity()<Pools.get(serv.getIdPool()))
						Pools.replace(serv.getIdPool(),serv.getCapacity());
					else ;
				else Pools.put(serv.getIdPool(),serv.getCapacity());
			//System.out.println(serv.getCapacity()<Pools.get(serv.getIdPool()));
		}
		System.out.println(Pools);
		
		System.out.println( Pools.values().toString());
		Collection<Integer> a=Pools.values();
		int min=Integer.MAX_VALUE;
		for (Integer integer : Pools.values()) {
			if(integer.intValue()<min)
				min=integer.intValue();
		}
		System.out.println("Score: "+min);
	}
	
}
