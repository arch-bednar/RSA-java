import java.util.Scanner;
public class RSA{

    public static void main(String[] args){
	int choice;
	Scanner getch = new Scanner(System.in);
	Klucze1 makeKeys;
	Szyfr encrypt;
	Szyfr decrypt;
	boolean m=false,e=false,d=false;
	do{
	    menu();
	    choice = getch.nextInt();
	    switch(choice){
	    case 1 :
		//if(m==false){
		    makeKeys = new Klucze1();
		    m=true;
		    //}
		    //else
		    //makeKeys.Klucze1();
		System.out.println();
		System.out.println("Zapamiętaj te klucze!!!");
		System.out.println("//////////////////////////");
		System.out.println(makeKeys);
		System.out.println("//////////////////////////");
		System.out.println();
		break;
	    case 2 :
		//if(e==false){
		    encrypt = new Szyfr();
		    e=true;
		    //}
		encrypt.Zaszyfruj();
		break;
	    case 3 :
		//if(d==true){
		    decrypt = new Szyfr();
		    // d=true;
		    //}
		decrypt.Deszyfruj();
		break;
	    }
	}
	while(choice!=0);
    }
    private static void menu(){
	System.out.println("System szyfrowania danych RSA");
	System.out.println("(C)2020 Dawid Bednaczyk");
	System.out.println("Menu");
	System.out.println("===========");
	System.out.println("0. Koniec programu");
	System.out.println("1. Generowanie kluczy RSA");
	System.out.println("2. Szyfrowanie RSA");
	System.out.println("3. Deszyfrowanie RSA");
	System.out.println("Co chcesz zrobić? [0-3]: ");
    }
}
