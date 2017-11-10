public static String randomSeq(String peptides, int length)
	{
		//Declares Variables
		Random rand =  new Random();
		String sequence = "";
		
		for(int j=0; j<length; j++)
		{
			sequence += (char)(rand.nextInt(24)+65);
		}
		
		return sequence;
	}
