import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
/*
 * 
 * @author Isaac Mahon 20063321
 */


public class ComponentImage {
	
	Picture picture;
	String save;
	int height;
	int width;
	List<Integer> ids;
	/*
	 * Constructor for the image. Should pass the location of the image file, to be used
	 * for the rest of the program.
	 */
	public ComponentImage (String fileLocation) 
	{
		picture = new Picture(fileLocation);
		save = fileLocation;
		ids = new ArrayList<Integer>();
		width = picture.width();
		height = picture.height();
		
	}
	
	/*
	 * Main method with a rudimentary menu system
	 */
	public static void main(String[] args)
	{
		StdOut.println("Enter the full directory path of the picture.");
		String input = StdIn.readString();
		int option = 0;
		while (true)
		{
			StdOut.println("Enter the option you wish to use.");
			StdOut.println("0. Binarise image");
			StdOut.println("1. Count components");
			option = StdIn.readInt();
			if (option == 0)
			{
				ComponentImage test = new ComponentImage(input);
				test.binaryComponentImage();
			}
			else if (option == 1)
			{
				ComponentImage test = new ComponentImage(input);
				StdOut.println(test.countComponents());
			}
			else
			{
				StdOut.println("Enter a valid option");
			}
		}
	}
	
	/*
	 * Counts the number of Objects in the picture
	 * Commented out code in the method are previous attempts at the method that failed
	 */
	public int countComponents()
	{
		Picture source = binaryComponentImage();
		WeightedQuickUnion union = new WeightedQuickUnion((width*height));
		for (int i = 0; i < width; i++)
		{
			for (int j =  0; j < height; j++)
			{
				if (j+1 < height )
				{
					if (picture.get(i, j).equals(picture.get(i, j+1)))
					{
						union.union(linear(i, j), linear(i, j+1));
					}
				}
				if (i+1 < width )
				{
					if (picture.get(i, j).equals(picture.get(i+1, j)))
					{
						union.union(linear(i, j), linear(i+1, j));
					}
				}
			}
		}
		/*WeightedQuickUnion u = new WeightedQuickUnion(width*height);
		for (int i = 0; i < width-1; i++)
		{
			System.out.println("i="+(i-1));
			for (int j = 0; j < height-1; j++)
			{
				//System.out.println("j="+(j-1));
				if (i == 0 & j == 0) continue;
				if (j!=0)
				{
					if (i!=0)
					{
						if (source.get(i,j) == source.get(i-1, j-1))
						{
							u.union((width*j)+i, (width*(j-1)+i-1));
						}
					}
					if (source.get(i, j) == source.get(i, j-1))
					{
						u.union(((width*j)+i), ((width*(j-1))+i));
					}
					if (source.get(i, j) == source.get(i+1, j-1))
					{
						u.union((width*j)+i, (width*(j-1)+i+1));
					}
				}
				if(i!=0)
				{
					if (j != 895)
					{
						if(source.get(i, j) == source.get(i-1, j-1))
						{
							u.union((width*j)+i, (width*(j-1)+i-1));
						}
					}
					if(source.get(i, j) == source.get(i-1, j))
					{
						u.union((width*j)+i, (width*(j)+i-1));
					}
					if(source.get(i, j) == source.get(i-1, j+1))
					{
						u.union((width*j)+i, (width*(j+1)+i-1));
					}
				}
				if (i < width -1)
				{
					if (j != height-1)
					{
						if(source.get(i, j) == source.get(i+1, j+1))
						{
							u.union((width*j)+i, ((width*j+1)+i+1));
						}
					}
					if(source.get(i, j) == source.get(i+1, j))
					{
						u.union((width*j)+i, ((width*j)+i+1));
					}
					if(source.get(i, j) == source.get(i+1, j-1))
					{
						u.union((width*j)+i, ((width*j-1)+i+1));
					}
				}
				if (j < height -1)
				{
					if(source.get(i, j) == source.get(i+1, j+1))
					{
						u.union((width*j)+i, ((width*j+1)+i+1));
					}
					if(source.get(i, j) == source.get(i, j+1))
					{
						u.union((width*j)+i, ((width*j)+i+1));
					}
					if (i!=0)
					{
						if(source.get(i, j) == source.get(i-1, j+1))
						{
							u.union((width*j)+i, ((width*j-1)+i+1));
						}
					}
				}
			}
		}
		/**
		for (int i = 1; i < width -2; i++)
		{
			for (int j = 1; i < height -2; j++)
			{
				if (i > 0)
				{
					if ((source.get(i, j) == (source.get(i-1, j))))		
					{
						u.union((width*j)+i, (width*j)+i-1);
					}
				}
				if (i > 0 && j > 0)
				{
					if ((source.get(i, j) == (source.get(i-1, j-1))))		
					{
						u.union((width*j)+i, (width*(j-1)+i-1));
					}
				}
				if ( j > 0)
				{


					{
						u.union((width*j)+i, (width*(j-1)+i));
					}
				}
				if (i < width - 1 && j > 0)
				{
					if ((source.get(i, j) == (source.get(i+1, j-1))))		
					{
						u.union((width*j)+i, (width*(j-1)+i+1));
					}
				}
				if (i < width -1)
				{
					if ((source.get(i, j) == (source.get(i+1, j))))		
					{
						u.union((width*j)+i, (width*(j)+i+1));
					}
				}
				if (i < width -1 && j < height -1)
				{
					if ((source.get(i, j) == (source.get(i+1, j+1))))		
					{
						u.union((width*j)+i, (width*(j+1)+i+1));
					}
				}
				if (j < height -1)
				{
					if ((source.get(i, j) == (source.get(i, j+1))))		
					{
						u.union((width*j)+i, (width*(j+1)+i));
					}
				}
				if ( i > 0 && j < height -1)
				{
					if ((source.get(i, j) == (source.get(i-1, j+1))))		
					{
						u.union((width*j)+i, (width*(j+1)+i-1));
					}
				}
				
			}
		}	
		**/

		return union.count()-1;
	}
	
	public int linear (int x, int y)
	{
		return (y*(width)+x);
	}
	/*
	 * Returns a picture of the original picture in a binary grayscale
	 */
	public Picture binaryComponentImage()
	{
		Luminance colour = new Luminance();
		int threshold = 128;
		for ( int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				double lum = colour.lum(picture.get(i,j));
				picture.set(i,j, lum >= threshold ? Color.white : Color.black);
				
				/*if (colour.lum(picture.get(i,j)) > 128)
				{
					picture.set(i,j, new Color((0)));
				}
				else
				{
					picture.set(i,j, new Color(255));
				}*/
				//picture.set(i, j, new Color((int) colour.lum(picture.get(i, j))));
				//picture.set(i, j, ( colour.toGray((picture.get(i, j)))));
			}
		}
		picture.show();
			 
		return picture;
	}
	

	/*
	 * Returns a picture of the original image with randomly assigned colours
	 * for each component
	 */
	public Picture colourComponentImage()
	{
		Picture source = binaryComponentImage();
		WeightedQuickUnion union = new WeightedQuickUnion((width*height));
		Random rand = new Random();
		int r = rand.nextInt(255);
		int g = rand.nextInt(255);
		int b = rand.nextInt(255);
		Color c = new Color(r, g, b);
		picture.set(0,0,c);
		for (int i = 0; i < width; i++)
		{
			for (int j =  0; j < height; j++)
			{
				if (j+1 < height )
				{
					if (picture.get(i, j).equals(picture.get(i, j+1)))
					{
						union.union(linear(i, j), linear(i, j+1));
						if (ids.contains(union.find(linear(i,j))))
						{
						}
						else
						{
							ids.add(union.find(linear(i,j)));
						}
						//matchColour(i,j,i,j+1);
					}
					/*else
					{
						picture.set(i, j+1, assignRandom());
					}*/
				}
				if (i+1 < width )
				{
					if (picture.get(i, j).equals(picture.get(i+1, j)))
					{
						union.union(linear(i, j), linear(i+1, j));
						if (ids.contains(union.find(linear(i,j))))
						{
						}
						else
						{
							ids.add(union.find(linear(i,j)));
						}
						//matchColour(i,j,i+1,j);
					}
					/*else
					{
						picture.set(i+1, j, assignRandom());
					}*/
				}
			}
		}
		for(int x = 0; x < ids.size(); x++)
		{
			for (int a = 0; a < width*height; a++)
			{
				if (union.find(a) == ids.get(x))
				{
					picture.set((a%width), (int) (a/width), new Color(r/ids.get(x), b/ids.get(x),g/ids.get(x)));
				}
			}
		}
		picture.save("C:"+File.separator+"Users"+File.separator+"Isaac"+File.separator+"Desktop"+File.separator+"Programing"+File.separator+"ConnectorStarter"+File.separator+"images"+File.separator+"color.png");
		picture.show();
		return picture;
	}
		
	/*
	 * Returns a picture of the original image with red line boxes drawn around each component
	 */
	public Picture identifiedComponentImage()
	{
		return null;
	}
	
	public void matchColour(int a, int b,int x, int y)
	{
		picture.set(x, y, picture.get(a,b ));
	}
	
	public Color assignRandom()
	{
		return null;
	}

}
