
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class PieChartExample {
	public static void main(String[] args) {
		int a, b,c,d;
		// Create a simple Bar chart
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		System.out.println("Podaj wartosci dla kandydatów");
		 Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
	    a = odczyt.nextInt(); 
		dataset.setValue(a, "Głosy", "Jane");
		b = odczyt.nextInt(); 
		dataset.setValue(b, "Głosy", "Tom");
		a = odczyt.nextInt(); 
		dataset.setValue(a, "Głosy", "Jill");
		c = odczyt.nextInt(); 
		dataset.setValue(c, "Głosy", "John");
		c = odczyt.nextInt();
		dataset.setValue(c, "Głosy", "Fred");
		JFreeChart chart = ChartFactory.createBarChart3D("Głosowanie na kandydatów",
		"Kandydaci", "Głosy", dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBackgroundPaint(Color.gray); // zmiana koloru tla
		chart.getTitle().setPaint(Color.black); // kolor tytulu
		CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
		p.setBackgroundPaint(Color.pink); //zmiana koloru tla wykresu
		p.setRangeGridlinePaint(Color.black); // Modify the colour of the plot gridlines
		try {
		ChartUtilities.saveChartAsJPEG(new File("C:\\Users\\wykres.jpg"), chart, 500, 300);
		} catch (IOException e) {
		System.err.println("Problem z utworzeniem wykresu.");
		}
		}
}
