package peptideGeneration;

//NAME              	Isabel Holtan
//LAST MODIFIED     	10 November 2017
//Class			Peptide Generation
//DESCRIPTION       	Generates a random sequence of proteins and
//			then divides into peptides and outputs to a file

import java.util.*;

public class PeptideGeneration
{
	public String randomSeq(String peptides, int length)
	{
		//Declares Variables
		Random rand =  new Random();
		String sequence = "";
		
		for(int j=0; j<length; j++)
		{
			sequence += peptides.charAt((rand.nextInt(peptides.length())));
		}
		
		return sequence;
	}
}
