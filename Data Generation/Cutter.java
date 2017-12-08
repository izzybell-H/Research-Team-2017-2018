	public void cutter (String sequence, int maxLength, int numPep)  throws IOException
	{
		//Initializes Variables
		File file = new File("C:/Users/Isabel/OneDrive/FASTA.txt");
		PrintWriter out = new PrintWriter(file);
		Random rand = new Random();
		boolean uniqueCut = false, isExcess=true;
		int last = 0, next = 0, excess=0, take=0, add=0, shiftSpace=0, shiftAmount=0, excessTemp=0, length=0, k=0; 
		int cutLocation[] = new int [numPep-1];
		String peptides[] = new String [numPep];
		
		//Generates random places to "cut" the protein at
		for(int j=0; j<numPep-1; j++)
		{
			cutLocation[j] = rand.nextInt(sequence.length()-1)+1;
		}
		
		//Sorts and fixes any repeated numbers by generating a new random digit. Repeats until all unique
		while(!uniqueCut)
		{
			uniqueCut=true;
			Arrays.sort(cutLocation);
			
			//Checks every cut location to be unique
			for(int j=0; j<numPep-2; j++)
			{
				if(cutLocation[j]==cutLocation[j+1])
				{
					cutLocation[j] = rand.nextInt(sequence.length()-1)+1;
					uniqueCut=false;
				}
			}
		}
		
		//Cut up protein sequence into peptides and generated cut locations
		next=cutLocation[0];
		for(int j = 0; j< numPep-1; j++)
		{
			peptides[j]= sequence.substring(last, next+last);
			last+=next;
			if(j+1<numPep-1)
			{
				next = cutLocation[j+1]-cutLocation[j];
			}
		}
		peptides[numPep-1]=sequence.substring(last, sequence.length());
		
		//Determines if each element is to long and shifts any excess onto the next element
		for(int j=0; j<numPep-1; j++)
		{
			if(peptides[j].length()>maxLength)
			{
				peptides[j+1] = (peptides[j].substring(maxLength, peptides[j].length()) + peptides[j+1]);
				peptides[j]=peptides[j].substring(0,maxLength);
			}
		}

		//Determines if last element is to long and randomly shifts its burden onto other peptides
		excess = peptides[numPep-1].length()-maxLength;
		excessTemp=excess;
		while(isExcess)
		{
			isExcess = false;
			if(excess>0)
			{
				isExcess=true;
				for(int j=0; j<numPep-2; j++)
				{
					if(peptides[j].length()<maxLength && peptides[j+1].length()>2)
					{
						take= peptides[j+1].length()-1;
						add = maxLength-peptides[j].length();
						
						if(take>add)
						{
							shiftSpace = add;
						}
						else if (add>take || add == take)
						{
							shiftSpace = take;
						}
						shiftAmount = rand.nextInt(shiftSpace-1)+1;
						peptides[j] = peptides[j] + peptides[j+1].substring(0, shiftAmount);
						peptides[j+1] = peptides[j+1].substring(shiftAmount, peptides[j+1].length());
						excess-=shiftAmount;
					}
				}
			}
		}
		if(excessTemp>0)
		{
			length = peptides[numPep-1].length();
			peptides[numPep-2]+= peptides[numPep-1].substring(0,excessTemp);
			peptides[numPep-1] = peptides[numPep-1].substring(excessTemp,length);
		}
				
		//Outputs the peptides to a FASTA file
		for(int j =0; j< numPep; j++)
		{
			out.println(">Seq"+k+"_ProtienRed");
			out.println(peptides[j]);
			k++;
		}
		out.close();
	}
