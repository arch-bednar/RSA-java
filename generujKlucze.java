import java.util.Scanner;
import java.util.Random;

public class generujKlucze{
    public String file;
    private long priv;
    private long pub;
    private long euler;
    private long n;
    private long wykPub;
    private long wykPriv;
    public long[] prywatny=new long[2];
    public long[] publiczny=new long[2];
    public rsa(String file){
	this.file=file;
	genPierwsze();
	euler();
	modul();
	wykladnikPub();
	this.wykPriv=wykladnikPriv();
	System.out.println("euler "+euler);
	System.out.println("modul "+n);
	System.out.println("priv i pub "+priv+" "+pub);
	System.out.println("wykladnik"+this.wykPub);
    }

    public rsa(){
	genPierwsze();
	this.priv=13;
	this.pub=11;
	euler();
	modul();
	wykladnikPub();
	this.wykPriv=wykladnikPriv();
	System.out.println("euler "+this.euler);
	System.out.println("modul "+this.n);
	System.out.println("priv i pub "+this.priv+" "+this.pub);
	System.out.println("wykladnik"+this.wykPub);
	System.out.println("wykladnik priv" + this.wykPriv);
	this.publiczny[0]=this.wykPub;
	this.publiczny[1]=this.n;
	this.prywatny[0]=this.wykPriv;
	this.prywatny[1]=this.n;

	System.out.println("Klucz publiczny["+publiczny[0]+","+publiczny[1]+"]");
	System.out.println("Klucz prywatny["+prywatny[0]+","+publiczny[1]+"]");
    }

    private void genPierwsze(){
	Scanner scan = new Scanner(System.in);
	int ile;
	System.out.println("Podaj z ilu liczb pierwszych(conajmniej 10) wygenerować klucze: ");
	ile=scan.nextInt();
	while ((ile<10) || (ile>10000)){
	    System.out.println("Podano zle dane");
	    System.out.println("Podaj z ilu liczb pierwszych(conajmniej 10) wygenerować klucze: ");
	    ile=scan.nextInt();
	}
	int[] pierwsze = new int[ile];
	int num=0;
	int x=2,y=2;
	boolean property;
	while(num < ile){
	    property=true;
	    y=2;
	    while(y*y<=x){
		if(x%y==0){
		    property=false;
		    break;
		}
		y++;
	    }
	    if(property==true){
		pierwsze[num]=x;
		num++;
		x++;
	    }
	    else{
		x++;
	    }
	}

	Random gen = new Random();
	do{
	    this.priv=pierwsze[gen.nextInt(ile)];
	    this.pub=pierwsze[gen.nextInt(ile)];
	}
	while(this.pub==this.priv);
    }

    private void euler(){
	this.euler=(priv-1)*(pub-1);
    }

    private void modul(){
	this.n=priv*pub;
    }
    
    private void wykladnikPub(){
	//System.out.println("wykladniuk");
	for(long loop=2; loop<this.n; loop++){
	    long loop1=loop;
	    long wyn=NWD(loop1, this.euler);
	    //System.out.println("w petlni wykladnika"+loop+" "+this.euler+"wynik "+wyn);
	    if(wyn==1){
		this.wykPub=loop;
		//System.out.println("siema "+this.wykladnik);
		break;
	    }
	}
    }
    private long NWD(long a, long b){
	long temp;
	while(b!=0){
	    temp=a%b;
	    a=b;
	    b=temp;
	}
	return a;
    }

    private long wykladnikPriv(){
	long d = 2;
	while((d*this.wykPub-1)%120 != 0){
	    d++;
	}
	return d;
    }

    public String toString(){
        return "Klucz publiczny: ["+this.publiczny[0]+","+this.publiczny[1]+"]"+\
	    "\nKlucz prywatny: ["+this.prywatny[0]+","+this.prywatny[1]+"]";
    }
}
