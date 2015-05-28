import java.util.Random;

public class Test {
	public static void main (String[] args){
		Random alea=new Random() ;
		
		for(int i=0;i<50;i++){
			int x=alea.nextInt(3)-1;
			Terminal.ecrireIntln(x);
		}

	}
}
