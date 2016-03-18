import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.Span;

public class HelperOpenNLP {
	
	String PosString;
	static Set<String> nounPhrases = new HashSet<>();
	ArrayList<String> nounphrases,verbphrases,adjectivephrases;
	public HelperOpenNLP() {
		nounphrases = new ArrayList<String>();
		verbphrases = new ArrayList<String>();
		adjectivephrases = new ArrayList<String>();
	}

	// 1.Function Sentence Detector
	public String[] SentenceDetect(String Str) {
		InputStream is;
		String sentences[];
		try {
			is = new FileInputStream("model/en-sent.bin");
			SentenceModel model;
			try {
				model = new SentenceModel(is);
				SentenceDetectorME sdetector = new SentenceDetectorME(model);
				sentences = sdetector.sentDetect(Str);
				is.close();
				return sentences;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 2.Function Tokenizer
	public String[] Tokenize(String str) {
		InputStream is;
		String tokens[];
		try {
			is = new FileInputStream("model/en-token.bin");
			TokenizerModel model;
			try {
				model = new TokenizerModel(is);
				Tokenizer tokenizer = new TokenizerME(model);
				tokens = tokenizer.tokenize(str);
				is.close();
				return tokens;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 3.Function NameEnityFinder
	public Span[] findName(String str[]) {
		InputStream is;
		Span names[];
		try {
			is = new FileInputStream("model/en-ner-person.bin");
			TokenNameFinderModel model;
			try {
				model = new TokenNameFinderModel(is);
				is.close();
				NameFinderME nameFinder = new NameFinderME(model);
				names = nameFinder.find(str);
				return names;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 4.Function POS Tagger
	public String[] POSTag(String str[]) throws IOException {
		POSModel model = new POSModelLoader().load(new File(
				"model/en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);

		perfMon.start();
		String[] tags = tagger.tag(str);
		//System.out.println(whitespaceTokenizerLine[0]);
		POSSample possample = new POSSample(str, tags);
		PosString = possample.toString();
		System.out.println(possample);

		perfMon.incrementCounter();
		perfMon.stopAndPrintFinalResult();
		
		
		return tags;
		
	}
	
	public String getPosStringFormat(){
		return PosString;
	}
	//
	public  void getPhrases(opennlp.tools.parser.Parse p) {
		
	    if (p.getType().equals("NP")) { //NP=noun phrase
	         nounphrases.add(p.getCoveredText());
	    }else if (p.getType().equals("VP")) { //NP=noun phrase
	         verbphrases.add(p.getCoveredText());
	    }else if (p.getType().equals("ADJP")) { //NP=noun phrase
	    	adjectivephrases.add(p.getCoveredText());
	    }
	    for (opennlp.tools.parser.Parse child : p.getChildren())
	         getPhrases(child);
	}
	
	// 5. Function Parser
	public  String Parse(String sentence) throws InvalidFormatException, IOException {
		
		InputStream is = new FileInputStream("model/en-parser-chunking.bin");
	 
		ParserModel model = new ParserModel(is);
	 
		Parser parser = ParserFactory.create(model);
		opennlp.tools.parser.Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
		String str = topParses.toString();
		System.out.println("--------------------");
		nounphrases.clear();
		verbphrases.clear();
		adjectivephrases.clear();
		//StringBuffer sb = new StringBuffer(sentence.length() * 4);
		for (opennlp.tools.parser.Parse p : topParses)
		{
			getPhrases(p);
			p.show();		
		}
		is.close();
		for (String s : nounphrases)
		    System.out.println(s);
		System.out.println("-------------------------------------------------");
		for (String s : verbphrases)
		    System.out.println(s);
		System.out.println("-------------------------------------------------");
		for (String s : adjectivephrases)
		    System.out.println(s);
		System.out.println("-------------------------------------------------");
		return str;
	 
	}

}
