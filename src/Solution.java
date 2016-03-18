import java.io.IOException;


public class Solution {
	
	Sentence sentence[],s;
	String sen[];
	
	Algorithm alg;
	HelperOpenNLP nlp;
	public Solution(String text) throws IOException{
		alg=new Algorithm();
		nlp=new HelperOpenNLP();
		sen=nlp.SentenceDetect(text);
		sentence=new Sentence[sen.length];
		
		System.out.println("sen "+sen.length);
		for(int i=0;i<sen.length;i++)
			sentence[i]=new Sentence(sen[i]);
		
		
		
	}
	
	public void print(){
		if(sentence!=null)
		for(int i=0;i<sentence.length;i++)
			if(sentence[i]!=null)
			sentence[i].print();
		
		
	}
	
	
}
