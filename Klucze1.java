import java.util.Scanner;
import java.util.Random;

public class Klucze1{
    public long priv;
    public long pub;
    public long euler;
    public long n;
    public long wykPub;
    public long wykPriv;
    public long[] prywatny=new long[2];
    public long[] publiczny=new long[2];

    public Klucze1(){
	genPierwsze();
	euler();
	modul();
	wykladnikPub();
	System.out.println("pierwsze: "+priv+" "+pub);
	System.out.println("euler i modul: "+euler+" "+n);
	this.publiczny[0]=this.wykPub;
	this.publiczny[1]=this.n;
	this.prywatny[0]=this.wykPriv;
	this.prywatny[1]=this.n;
    }

    private void genPierwsze(){
	Scanner scan = new Scanner(System.in);
	int ile;
	System.out.println("##################################");
	System.out.print("Podaj z ilu liczb pierwszych(conajmniej 10, niewięcej niż 100) wygenerować klucze: ");
	ile=scan.nextInt();
	while ((ile<10) || (ile>100)){
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
	for(long x1: pierwsze) System.out.println(x1);
    }

    private void euler(){
	this.euler=(this.priv-1)*(this.pub-1);
    }

    private void modul(){
	this.n=this.priv*this.pub;
    }

    private void wykladnikPub(){
	long e;
	for(e=3; NWD(e,this.euler)!=1; e+=2){
	    System.out.println("e: "+e);
	    this.wykPub=e;
	    this.wykPriv=wykladnikPriv(e, euler);
	}
	System.out.println("priv i pub: "+wykPriv+" "+wykPub);
    }

    private long wykladnikPriv(long a, long b){
	long a0,n0,p0,p1,q,r,t;
	
	p0 = 0; p1 = 1; a0 = a; n0 = n;
	q  = n0 / a0;
	r  = n0 % a0;
	while(r > 0)
	    {
		t = p0 - q * p1;
		if(t >= 0)
		    t = t % n;
		else
		    t = n - ((-t) % n);
		p0 = p1; p1 = t;
		n0 = a0; a0 = r;
		q  = n0 / a0;
		r  = n0 % a0;
  }
  return p1;
    }

    private long NWD(long a, long b){
	long temp;
	while(b!=0){
	    temp=b;
	    b=a%b;
	    a=temp;
	}
	return a;
    }

    public String toString(){
        return "Klucz publiczny: ["+this.publiczny[0]+","+this.publiczny[1]+"]"+System.lineSeparator()+"Klucz prywatny: ["+this.prywatny[0]+","+this.prywatny[1]+"]";
    }
}
