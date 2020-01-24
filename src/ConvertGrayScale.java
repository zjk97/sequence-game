import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

public class ConvertGrayScale {
	
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	static void convert(String s) {
		try {

            File input = new File(s);
            BufferedImage image = ImageIO.read(input);


            Image result = GrayFilter.createDisabledImage(image);

            File output = new File("greyCards/" + s);
            ImageIO.write(toBufferedImage(result), "png", output);

        }  catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
    	String[] c = {"AS", "AH", "AD", "AC",
				"2S", "2H", "2D", "2C",
				"3S", "3H", "3D", "3C",
				"4S", "4H", "4D", "4C",
				"5S", "5H", "5D", "5C",
				"6S", "6H", "6D", "6C",
				"7S", "7H", "7D", "7C",
				"8S", "8H", "8D", "8C",
				"9S", "9H", "9D", "9C",
				"10S", "10H", "10D", "10C",
				"JS", "JH", "JD", "JC",
				"QS", "QH", "QD", "QC",
				"KS", "KH", "KD", "KC"};
    	
    	for(int i=0; i<52; i++) {
				convert(c[i] + ".png");
		}
    }
}