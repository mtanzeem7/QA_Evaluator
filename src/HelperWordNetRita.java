import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

import rita.wordnet.RiWordnet;


public class HelperWordNetRita {
	
	RiWordnet wordnet;
	
	public HelperWordNetRita(){
		wordnet = new RiWordnet(null);
	}
	
	// Function To get PartOfSpeech of a given Word
	public String[] getPartsOfSpeech(String word){
		String[] partsofspeech = wordnet.getPos(word);
		return partsofspeech;
	}
	
	// Function To get AllSense of word
	public int[] getAllSenseID(String word, String pos){
		
		int[] ids = wordnet.getSenseIds(word,pos);
		return ids;
		
	}
	// Function get the Gloss of particular synset
	public String getGolss(int id){
		String gloss = wordnet.getGloss(id);
		return gloss;
	}
	
	// Function To get Particular Synset
	public String[] getParticularSynsets(int id){
		String[] synsets = wordnet.getSynset(id);
		return synsets;
	}
	
	// Function To get  Synsets of All senses
	public String[][] getAllSynsets(int ids[]) {
        String words[][]=new String[ids.length][];
		HashSet<String> allSynSet = new HashSet<String>();
		
		if(ids!=null)
		for (int i = 0; i < ids.length ; i++) {
			if(wordnet.getSynset(ids[i])!=null)
			words[i] = wordnet.getSynset(ids[i]);
			
			
		}
		String[] allSynsetArray = new String[allSynSet.size()]; 
		allSynSet.toArray(allSynsetArray);
		return words;

	}
	
	// Function To get  Synsets of All senses
		public String[] getSyn(int ids[]) {

			HashSet<String> allSynSet = new HashSet<String>();
			
			if(ids!=null)
			for (int i = 0; i < ids.length; i++) {
				
				String[] words = wordnet.getSynset(ids[i]);
				if(words!=null)
				for (int j = 0; j < words.length; j++) {
					allSynSet.add(words[j]);
				}
			}
			String[] allSynsetArray = new String[allSynSet.size()]; 
			allSynSet.toArray(allSynsetArray);
			return allSynsetArray;

		}
	
	// Function To get max of 10 Synonyms
	public String[] getAllSynonyms(String word, String pos){
		
		String[] synonyms = wordnet.getAllSynonyms(word, pos,10);
		return synonyms;
		
	}
	
	// Function To get All Hyponyms of a given Word
	public String[] getAllHyponyms(String word, String pos){
		
		String[] hyponyms = wordnet.getAllHyponyms(word, pos);
		return hyponyms;
	}

	// Function To get All Hypernyms of a given Word
	public String[] getAllHypernyms(String word, String pos) {

		String[] hypernyms = wordnet.getAllHypernyms(word, pos);
		return hypernyms;
	}
	
	// Function to get Distance between words
	public float getDistance(String word1, String word2, String pos){
		
		float dist = wordnet.getDistance(word1,word2,pos);
		return dist;
	}
	
	// Function to get Common Parent
	public String[] getCommonParent(String word1,String word2, String pos){
		
		String[] parents = wordnet.getCommonParents(word1, word2, pos);
		return parents;
	}
	// Function to get Best Part of Speech
	
	public String getBestPos(String word){
		String pos = wordnet.getBestPos(word);
		return pos;
	}
	// Check for Pos
	public boolean isNoun(String word){
		return wordnet.isNoun(word);
	}
	
	public boolean isVerb(String word){
		return wordnet.isVerb(word);
	}
	
	public boolean isAdjective(String word){
		return wordnet.isAdjective(word);
	}
	
	public boolean isAdverb(String word){
		return wordnet.isAdverb(word);
	}
	
	// All Similar Holds for adjectives
	public String[] getAllSimilar(int id){
		return wordnet.getSimilar(id);
	}
	
	// All Verb Group Holds for verb
	public String[] getAllVerb(int id){
		return wordnet.getVerbGroup(id);
	}
	
	// All Adverb Group Hold for Adverb
	public String[] getAllAdverb(int id){
		return wordnet.getDerivedTerms(id);
	}
	

}
