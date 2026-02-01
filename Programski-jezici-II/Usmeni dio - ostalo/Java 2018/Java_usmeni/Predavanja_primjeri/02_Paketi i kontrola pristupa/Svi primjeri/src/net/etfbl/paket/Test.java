package net.etfbl.paket;
import net.etfbl.canvas.ETFCanvas;

public class Test {

	public static void main(String[] args) {
		ETFCanvas etfCanvas = new ETFCanvas(500, 500);
		etfCanvas.drawCircle(350, 350, 50, ETFCanvas.COLOR_RED);
		etfCanvas.drawRect(30, 10, 200, 250, ETFCanvas.COLOR_BLUE);
		etfCanvas.drawRect(50, 30, 220, 270, ETFCanvas.COLOR_RED);
		etfCanvas.drawRect(70, 50, 240, 290, ETFCanvas.COLOR_GREEN);
		
	}

}
