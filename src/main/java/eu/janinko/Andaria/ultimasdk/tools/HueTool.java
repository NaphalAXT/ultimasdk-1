
package eu.janinko.Andaria.ultimasdk.tools;

import eu.janinko.Andaria.ultimasdk.files.Hues;
import eu.janinko.Andaria.ultimasdk.files.graphics.Color;
import eu.janinko.Andaria.ultimasdk.files.hues.Hue;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public class HueTool {
 	private final Hues hues;

	public HueTool(Hues hues) {
		this.hues = hues;
	}

	private boolean compare(Hue f, Hue s) {
		for (int i = 0; i < 32; i++) {
			Color fc = f.getColor(i);
			Color sc = s.getColor(i);
			if(!fc.equals(sc)) return false;
		}
		return true;
	}

	public boolean areDifferent(Hues other){
		for(int i=1; i<=3000; i++){
			Hue f = hues.getHue(i);
			Hue s = other.getHue(i);
			if (!compare(f,s)){
				return true;
			}
		}
		return false;
	}

	public void printDifferences(Hues other){
		for(int i=1; i<=3000; i++){
			Hue f = hues.getHue(i);
			Hue s = other.getHue(i);
			if (!compare(f,s)){
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}

	public Hues copyHue(Hues from, int number){
		Hue h = from.getHue(number);
		hues.setHue(number, new Hue(h));
		return hues;
	}


}
