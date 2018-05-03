package main;

import java.io.BufferedReader;
import java.io.FileReader;

public class Label_Seq{
	
	public int [][]array= new int[7][15];
	public int []length=new int[7];
	
	private String file_path;
	public Label_Seq(String file_path) {
		
		this.file_path = file_path;
		Read();
	}
	
	
	public void Read() {
	
		int k=0;
		int j=0;
		
		
		
        try { 
            BufferedReader reader = new BufferedReader(new FileReader(this.file_path));
            
            String line;
            StringBuffer tt=new StringBuffer("");
            
           
            while((line=reader.readLine())!=null){ 
            	
            	
            	if(line.length()!=0 && line.charAt(0)=='[')
            	{
            		j=0;
            		
            		for(int i=0;i< line.length();i++) {
            			if(line.charAt(i)=='1'|| line.charAt(i)=='2' || line.charAt(i)=='3') {
            				array[k][j]=(int)line.charAt(i)-48;
            				//System.out.println((int)line.charAt(i)-48);
            				j++;
            				//System.out.println(line.charAt(i));
            			}
            			
            		}
            		length[k]=j;
            		k++;
            	
            		
            		
            	}
            	//char []ch=line.toCharArray();
            	
            	
                
                //if(line.length()!=0)
               // System.out.println(line.charAt(0));
                
                
            }
            for(int i=0;i<6;i++) {
            	for(int d=0;d<15;d++) {
            	}
            	System.out.println("");
            }
            for(int i=0;i<6;i++)
            
            reader.close();
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        
        
	}
	private char[] getType(char[] item) {
		// TODO Auto-generated method stub
		return null;
	}

/*
	public static void main(String[] args) {
		readfile read = new readfile("src/project4/b/diceSequences.txt");
		System.out.println("finish"); 
		
	}
	*/

	}



