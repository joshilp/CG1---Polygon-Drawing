package client.testPages;

import java.util.Random;

import geometry.Vertex3D;
import polygon.Polygon;
import polygon.PolygonRenderer;
import windowing.drawable.Drawable;
import windowing.graphics.Color;

public class RandomPolygonTest {
	
	private final PolygonRenderer renderer;
	private final Drawable panel;
	
	static int seed = (int)(Math.random()*100000);
	
	public RandomPolygonTest(Drawable panel, PolygonRenderer renderer) {
		this.panel = panel;
		this.renderer = renderer;
		
		render();
	}
	
	private void render() 
	{
		//set random and seed
		Random rand = new Random();
		rand.setSeed(seed);
		
		for (int i = 0; i < 20; i++)
		{
			//create three points with 3 randomly generated (x,y) points
			Vertex3D p0 = new Vertex3D(randx(rand), randy(rand), 0, Color.WHITE);
			Vertex3D p1 = new Vertex3D(randx(rand), randy(rand), 0, Color.WHITE);
			Vertex3D p2 = new Vertex3D(randx(rand), randy(rand), 0, Color.WHITE);
			
			//draw
			Vertex3D[] points = {p0, p1, p2};
			Polygon poly = Polygon.make(points);
			renderer.drawPolygon(poly, panel);
		}
	}

	//gets random x value
	private int randx(Random rand)
	{
		int width = panel.getWidth();
		int random = (int) (rand.nextDouble() * width);
		return random;
	}
	
	//gets random y value
	private int randy(Random rand)
	{
		int height = panel.getHeight();
		int random = (int) (rand.nextDouble() * height);
		return random;
	}
}
