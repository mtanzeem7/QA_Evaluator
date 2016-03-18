import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.util.InvalidFormatException;


public class Algorithm {
	
	HelperOpenNLP objNLP;
	HelperWordNetRita objRita;
	//Solution modelanswer;
	//String tempstr,tempstr2;
	public Algorithm(){
		objNLP=new HelperOpenNLP();
		objRita=new HelperWordNetRita();
		//modelanswer=new Solution();
		//tempstr="Kernel is the up of ten operating system. It connects applications to the actual processing of data. It also manages all communications between software and hardware components to ensure usability and reliability.";
	    //tempstr2="Virtual memory is a memory management technique for letting processes execute outside of memory. This is very useful especially is an executing program cannot fit in the physical memory.";
	}
	
	
	
	
	
	public String[] tag(String tok[]) throws IOException{
		String tag[]=new String[tok.length];
		
		for(int i=0;i<tok.length;i++)
			tag[i]=objRita.getBestPos(tok[i]);
		
		/*String Tag[]=objNLP.POSTag(tok);
		String tag[]=new String[Tag.length];
		
		for(int i=0;i<Tag.length;i++){
			
			
			if(Tag[i].charAt(0)=='N')
				tag[i]="n";
			else if(Tag[i].charAt(0)=='V')
				tag[i]="v";
			else if(Tag[i].charAt(0)=='J')
				tag[i]="a";
			else if(Tag[i].charAt(0)=='R')
				tag[i]="r";
			else 
				tag[i]="s";
		}
		*/
		
		return tag;
	}
	

	public String[] getTok(String s){
		String[] str,tok;
		str=objNLP.Tokenize(s);
		//tok=stopWords(str);
		tok=str;        //when stopwods are not removed
		
		return tok;
	}
	
	
	public int[][] getSense(String s[],String pos[]){
		
		int sen[][]=new int[s.length][];
		for(int i=0;i<s.length;i++){
			if(pos[i]!=null)
			sen[i]=objRita.getAllSenseID(s[i], pos[i]);
		}
		
		return sen;
	}
	
	public String[][] getSyn(String tok[],int id[][]){
		String syn[][]=new String[tok.length][];
		
		for(int i=0;i<tok.length;i++){
			if(id[i]!=null)
			syn[i]=objRita.getSyn(id[i]);
		}
		
		return syn;
	}
	
	public String[] stopWords(String words[]){
		String[] tok;
		int k=0;
	    ArrayList<String> wordsList = new ArrayList<String>();
	    Set<String> stopWordsSet = new HashSet<String>();
	    stopWordsSet.add("is");
	    stopWordsSet.add("the");
	    stopWordsSet.add("a");
	    stopWordsSet.add("of");

	    for(String word : words)
	    {
	        String wordCompare = word.toUpperCase();
	        if(!stopWordsSet.contains(word))
	        {
	            wordsList.add(word);
	            //tok[k]=word;
	            k++;
	        }
	    }
	    
	    
	    tok = wordsList.toArray(new String[wordsList.size()]);

	    for (String str : tok){
	        System.out.print(str+" ");
	    }
	
	    return tok;
	}

	public int[][] sim(String tok1[],String tok2[],String syn1[][],String syn2[][]){
		int sim[][]=new int[tok1.length][tok2.length];
		
		System.out.println("sim matrx "+tok1.length+" "+tok2.length);
		
		
		for(int i=0;i<tok1.length;i++){
			for(int j=0;j<tok2.length;j++){
				if(tok1[i].equals(tok2[j])){
					sim[i][j]=1;
				
				}
				else if(syn1!=null && syn2!=null)
				sim[i][j]=match(syn1[i],syn2[j]);
				else
					sim[i][j]=0;
			}
		}
		
		
		return sim;
	}
	
	public static int match(String s1[],String s2[]){
		
		if(s1!=null && s2!=null)
		for(int i=0;i<s1.length;i++)
			for(int j=0;j<s2.length;j++)
				if(s1[i].equals(s2[j]))
					return 1;
		
		return 0;
	}

	public float dice(int sim[][]){
		int in=0;
		float x,y;
		x=sim.length;
		y=sim[0].length;
		
		for(int i=0;i<x;i++)
			for(int j=0;j<y;j++)
				if(sim[i][j]==1){
					in++;
				break;
				}
		
		return (2*in)/(x+y);
		
	}
	
}
