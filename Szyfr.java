import java.io.File;
import java.io.IOException;
//import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
public class Szyfr{
    public void Zaszyfruj(){
	try{
	    long wykladnik, modul;
	    Scanner partOfKey = new Scanner(System.in);
	    System.out.println();
	    System.out.print("Podaj wykladnik: ");
	    wykladnik = partOfKey.nextLong();
	    System.out.println();
	    System.out.print("Podaj modul: ");
	    modul = partOfKey.nextLong();
	    
	    File myObj;
	    Scanner takeVar = new Scanner(System.in);
	    System.out.print("Podaj lokalizaję pliku: ");
	    String f = takeVar.nextLine();
	    myObj = new File(f);
	    Scanner takeLine = new Scanner(myObj);
	    long l;

	    File myFile;
	    System.out.println("Podaj lokalizację pliku wyjściowego: ");
	    f = takeVar.nextLine();
	    myFile = new File(f);
	    //Scanner takeEnc = new Scanner(myFile);
	    
	    if(myFile.createNewFile()){
		System.out.println("File created: "+myFile.getName());
	    }
	    else{
		System.out.println("File already exists.");
		return;
	    }

	    FileWriter saveInFile = new FileWriter(f);
	    
	    while(takeLine.hasNextLine()){
		String ln = takeLine.nextLine();
		char[] line = ln.toCharArray();
		for(char x: line){
		    l=encrypt_RSA(x, wykladnik, modul);
		    saveInFile.write(Long.toString(l));
		    saveInFile.write('\n');
		    //System.out.println(x+" "+ wykladnik+" "+ modul);
		}
		char nl=10;
		l=encrypt_RSA(nl, wykladnik, modul);
		saveInFile.write(Long.toString(l));
		saveInFile.write('\n');
	    }
	    saveInFile.close();
	    takeLine.close();
	    System.out.println("Zaszyfrowano!!!");
	    return;
	}
	catch (IOException e){
	    System.out.println("An error occured. File doesn't exist or something is wrong!");
	    e.printStackTrace();
	}
    }

    private long encrypt_RSA(char letter, long w, long m){
	int ascii = (int)letter;
	//System.out.println("obecna litera: "+ascii + " " + letter);
	long pot, wyn,q;
	pot=ascii;
	wyn=1;
	for(q=w; q>0; q=q/2){
	    //System.out.println("konwersja "+q+" "+wyn+" "+pot);
	    if(q % 2 == 1) wyn = (wyn * pot) % m;
	    pot = (pot * pot) % m;
	    //System.out.println(pot);
	}
	System.out.println("wyn "+wyn);
	return wyn;
    }

    public void Deszyfruj(){
	try{
	    long wykladnik, modul;
	    Scanner partOfKey = new Scanner(System.in);
	    System.out.println();
	    System.out.print("Podaj wykladnik: ");
	    wykladnik = partOfKey.nextLong();
	    System.out.println();
	    System.out.print("Podaj modul: ");
	    modul = partOfKey.nextLong();

	    Scanner file = new Scanner(System.in);
	    System.out.println();
	    System.out.print("Podaj lokalizację pliku: ");
	    String f = file.nextLine();
	    File myFile = new File(f);

	    //Scanner file2 = new Scanner(System.in);
	    System.out.println();
	    System.out.print("Podaj lokalizację pliku wyjściowego do utworzenia: ");
	    String f1 = file.nextLine();
	    File createdFile = new File(f1);
	    if(createdFile.createNewFile()){
		System.out.println("Plik zostal utworzony!");
	    }
	    else{
		System.out.println("Plik istnieje!!!");
		return;
	    }

	    FileWriter newFile = new FileWriter(f1);
	    partOfKey = new Scanner(myFile);
	    //newFile.write("siema");
	    long l;
	    char letter;
	    while(partOfKey.hasNextLine()){
		String line = partOfKey.nextLine();
		l = decrypt_RSA(Long.parseLong(line), wykladnik, modul);
		letter = (char)l;
		//System.out.println("litera"+ letter);
		newFile.write(letter);
	    }
	    newFile.close();
	    partOfKey.close();
	    System.out.println("Odszyfrowano!!!");
	}
	catch (IOException e){
	    System.out.println("An error occured. File doesn't exist or something is wrong!");
	    e.printStackTrace();
	}
    }

    private long decrypt_RSA(long a, long w, long m){
	long pot, wyn,q;
	pot=a;
	wyn=1;
	for(q=w; q>0; q=q/2){
	    //System.out.println("konwersja "+q+" "+wyn+" "+pot);
	    if(q % 2 == 1) wyn = (wyn * pot) % m;
	    pot = (pot * pot) % m;
	    //System.out.println(pot);
	}
	//System.out.println("wyn "+wyn);
	return wyn;
    }
}
