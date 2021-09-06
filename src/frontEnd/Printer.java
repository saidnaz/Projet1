package frontEnd;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Printer  implements Printable {
	public List<String> lines;

	public Printer(String path){
		lines = new ArrayList<String>();
		BufferedReader fluxEntree=null;
		String line;
		try {
			fluxEntree = new BufferedReader(new FileReader(path));

			line = fluxEntree.readLine();
			while(line!=null){
				lines.add(line);
				line= fluxEntree.readLine();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
	throws PrinterException {
		if (pageIndex > 0) {
		return NO_SUCH_PAGE;
		}
		for(int i=0; i<lines.size(); i++){
		graphics.drawString(lines.get(i), 40, 20+i*20);
		}
		return PAGE_EXISTS;


}
}
