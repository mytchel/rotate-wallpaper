import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class ImageProperties {
    
    public static int imageBrightness(BufferedImage image, int pointsToTake) {
	int red = 0;
	int green = 0;
	int blue = 0;

	int points = 0;
		
	int yint = image.getHeight() / pointsToTake;
	int xint = image.getWidth() / pointsToTake;

	if (yint == 0) yint = 1;
	if (xint == 0) xint = 1;
	
	for (int y = 0; y < image.getHeight(); y += yint) {
	    for (int x = 0; x < image.getWidth(); x += xint) {
		int p = image.getRGB(x, y);
		Color c = new Color(p);
		points++;
		red += c.getRed();
		green += c.getGreen();
		blue += c.getBlue();
	    }
	}

	red /= points;
	green /= points;
	blue /= points;
	
	int amp = red + green + blue;
	return amp;
    }

    public static int imageContrast(BufferedImage image, int points) {
	int min = 0;
	int max = 0;

	int yint = image.getHeight() / points;
	int xint = image.getWidth() / points;

	if (yint == 0) yint = 1;
	if (xint == 0) xint = 1;
	
	for (int y = 0; y < image.getHeight(); y += yint) {
	    for (int x = 0; x < image.getWidth(); x += xint) {
		int p = image.getRGB(x, y);
		if (p > max) max = p;
		if (p < min) min = p;
	    }
	}

	return (max - min);
    }

    public static void main(String[] args) throws Exception {
	if (args.length < 2) {
	    System.out.println("usage: java ImageProperties -[b/c] file [points]");
	}

	File file = new File(args[1]);
	BufferedImage image = ImageIO.read(file);

	String option = args[0];
	
	int result = -1;
	if (option.equals("-b") || option.equals("--brightness")) {
	    int points = 20;
	    if (args.length == 4) {
		points = (int) Math.sqrt(Integer.parseInt(args[2]));
	    }
	    result = imageBrightness(image, points);
	} else if (option.equals("-c") || option.equals("--contrast")) {
	    int points = 40;
	    if (args.length == 4) {
		points = (int) Math.sqrt(Integer.parseInt(args[2]));
	    }
	    result = imageContrast(image, points);
	}
	
		
	System.out.println(result);
    }
}
