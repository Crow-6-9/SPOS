import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Pass1 {
	
    MacroNameTable MNT[] = new MacroNameTable[5];
	MacroDefinitionTable MDT[] = new MacroDefinitionTable[20];
	String data,data1[]=null,str[]=null;
	
	public void pass1conversion() throws Exception {
	
		for(int i=0;i<5;i++)
			MNT[i] = new MacroNameTable();
		for(int i=0;i<20;i++)
			MDT[i] = new MacroDefinitionTable();
		int iMNT = 0 , iMDT = 0 , j = 0 , iarg = 0;
		
		File f = new File("input.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));
		File fi = new File("output_pass1.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(fi));
		data = br.readLine();
		
		do
		{
			data1 = data.split(" ");
			if(data1[0].equalsIgnoreCase("macro"))
			{
				MNT[iMNT].address = Integer.toString(iMDT);
				data = br.readLine();
				data1 = data.split(" ");
				MNT[iMNT].name = data1[0];
				MDT[iMDT++].def=data;
				data1 = data1[1].split(",");
				for(int i=0;i<data1.length;i++)
				{
					if(data1[i].contains("="))
					{
						str=data1[i].split("=");
						data1[i]=str[0];
					}
				}
				int i=0;
				while(i < data1.length)
				{
					MNT[iMNT].arg[i] = data1[i];
					i++;
					iarg=i;
				}
				data = br.readLine();
				do
				{
					data1 = data.split(" ");
					data = data1[0];
					data1 = data1[1].split(",");
					j = searchArgument(MNT , iarg , iMNT , data1[0]);
					if(j!=-1)
						data = data+" "+ j+",";
					else
						data = data+" "+ data1[0]+",";
					j = searchArgument(MNT , iarg , iMNT , data1[1]);
					if(j!=-1)
						data = data+ j;
					else
						data = data+ data1[1];
					MDT[iMDT++].def=data;
					data = br.readLine();
				}while(!data.equalsIgnoreCase("mend"));
				MDT[iMDT++].def=data;
				iMNT++;
			}
			else
				bw.write(data+"\n");
			data = br.readLine();
		}while(data!=null );
		bw.close();
		br.close();
	
		dispMDT(iMDT);
		dispMNT(iMNT);
		dispALA(iMNT);
	}
	void dispMNT(int iMNT)throws Exception
	{
		File mnt = new File("MNT.txt") ;
		BufferedWriter bi = new BufferedWriter(new FileWriter(mnt)); 
		System.out.println("========================Macro Name Table===========================");
		for(int i = 0;i < iMNT ; i++)
		{
			System.out.println(MNT[i].name+" "+MNT[i].address);
		    bi.write(MNT[i].name+" "+MNT[i].address+"\n");
		}
		bi.close();
	}
	void dispMDT(int iMDT) throws Exception{
			File mdt = new File("MDT.txt") ;
			BufferedWriter bi = new BufferedWriter(new FileWriter(mdt)); 
			System.out.println("=====================Macro Definition Table========================");
			for(int i = 0 ;i < iMDT ;i++)
			{
				System.out.println(MDT[i].def);
				bi.write(MDT[i].def+"\n");
			}
			bi.close();
	}
	void dispALA(int imnt)throws Exception{
		File ala = new File("ALA.txt") ;
		BufferedWriter bi = new BufferedWriter(new FileWriter(ala)); 
		System.out.println("========================Argument List Array===========================");
		for(int i = 0 ;i < imnt ;i++)
		{
			System.out.println("Macro Name :"+MNT[i].name+"\n"+MNT[i].arg[0]+"\n"+MNT[i].arg[1]);
			bi.write("\nMacro Name :"+MNT[i].name+"\n"+MNT[i].arg[0]+"\n"+MNT[i].arg[1]);
		}
		bi.close();
	}
	public static void main(String[] args) throws Exception{
		Pass1 obj=new Pass1();
		obj.pass1conversion();
	}

	private static int searchArgument(MacroNameTable[] mNT, int iarg, int iMNT, String string) 
	{
		int j=0;
		for(j = 0 ; j < iarg ; j++)
		{
			if(mNT[iMNT].arg[j].equalsIgnoreCase(string))
			{
				return j;
			}
		}
		return -1;
	}
}