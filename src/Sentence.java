import java.io.IOException;


public class Sentence {
	
	public String sentence;
	public String tokens[];
	public String pos[];
	public int sense[][];
	public String syn[][];
	public String parsetree;
	
	Algorithm alg=new Algorithm();
	HelperWordNetRita rita=new HelperWordNetRita();
	//Word w;
	
	public Sentence(String s) throws IOException{
		
		sentence=s;
		tokens=alg.getTok(s);
		
		pos=alg.tag(tokens);
		
		sense=alg.getSense(tokens, pos);
		
		syn=alg.getSyn(tokens,sense);
		
		
	}

	public void print(){
		System.out.println(sentence);
		print(tokens);
		print(pos);
		
		for(int i=0;i<tokens.length;i++)
			if(sense[i]!=null)
		print(sense[i]);
		
		for(int i=0;i<syn.length;i++)
			if(syn[i]!=null)
			print(syn[i]);
		
	}
	
public static void print(String s[]) throws NullPointerException{
		
		for(int i=0;i<s.length;i++){
			if(s[i]!=null)
		System.out.print(s[i]+" ");
		}
		System.out.println();
		
	}

public static void print(int s[]){
	
	if(s!=null)
	for(int i=0;i<s.length;i++)
	System.out.print(s[i]+" ");
	
	System.out.println();
	
}
	
	
}
