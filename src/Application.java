import java.io.IOException;


public class Application {
	
	
	public static void main(String args[]) throws IOException{
		Sentence s1;
		Sentence s2;
		Solution t1,t2;
		int sim[][][];
		float dice[];
		Algorithm alg =new Algorithm();
		HelperWordNetRita rita=new HelperWordNetRita();
		
		
		
		t1=new Solution("cat is a animal. dog is animal. ");
		t2=new Solution("dog is a good animal . cat is a dear beast.");
		t1.print();
		t2.print();
		
		System.out.println("len "+t1.sen.length+"  "+t2.sen.length);
		
		sim=new int[t1.sen.length*t2.sen.length][][];
		
		int k=-1;
			for(int i=0;i<t1.sen.length;i++)
				for(int j=0;j<t2.sen.length;j++)
		sim[++k]=alg.sim(t1.sentence[i].tokens, t2.sentence[j].tokens, t1.sentence[i].syn, t2.sentence[j].syn);
			
			//sim=alg.sim(s1.tokens, s2.tokens, s1.syn, s2.syn);
		for(int i=0;i<sim.length;i++){
			System.out.println();
		print(sim[i]);
		
		}
		//System.out.println("len "+s1.tokens.length);
		
		dice=new float[sim.length];
		
		for(int i=0;i<sim.length;i++){
		dice[i]=alg.dice(sim[i]);
		System.out.println("\nSim "+i+" "+dice[i]);
		}
		//for(int i=0;i<s1.tokens.length;i++){
		//sen[0]=rita.getAllSenseID("operate", "v");
		//Sentence.print(sen[0]);
		//}
		//alg.step1();
		//alg.print();
	}
	
	
	
	public static void print(String s){
		System.out.println(s);
		
	}
	
	public static void print(int s[][]){
		for(int i=0;i<s.length;i++)
		{
			System.out.println();
			for(int j=0;j<s[i].length;j++)
				System.out.print(s[i][j]+" ");
		}
	}
	
	public static void print(String s[]){
		
		for(int i=0;i<s.length;i++)
		System.out.print(s[i]+" ");
		
		System.out.println();
		
	}

}
