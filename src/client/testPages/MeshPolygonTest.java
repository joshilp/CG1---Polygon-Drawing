package client.testPages;

import java.util.Random;

import geometry.Vertex3D;
import polygon.Polygon;
import polygon.PolygonRenderer;
import windowing.drawable.Drawable;
import windowing.graphics.Color;

public class MeshPolygonTest {
	
	public static int NO_PERTURBATION = 0;
	public static int USE_PERTURBATION = 1;
	private final PolygonRenderer renderer;
	private final Drawable panel;
	private int perturbation;
	
	static long seed = (int)(Math.random()*100000);
	
	public MeshPolygonTest(Drawable panel, PolygonRenderer renderer, int perturbation ) {
		this.panel = panel;
		this.renderer = renderer;
		this.perturbation = perturbation;
		
		render();
	}
	
	
	private void render() {
		
		//Get heigh, width, border, and dimensions of the triangles
		int height = panel.getHeight();
		int width = panel.getWidth();
		int border = (height/10)/2;
		int tridim = height/10;
		int tris = (width-(border*2))/tridim;
		
		//generate and set seed for randomness
		Random rand = new Random();
		rand.setSeed(seed);
		
		//initialize variables
		double rand_x = 0.0;
		double rand_y = 0.0;
		
		int x = border;
		int y = height - border;

		//set up 3 dimension array to store all tris, and coords
		int grid[][][] = new int[tris+1][tris+1][2];
		
		//iterate through to set grid
		for (int row = 0; row < tris+1; row++)
		{
			for (int col = 0; col < tris+1; col++)
			{
				//set randomness if perturbation is on
				rand_x = ((rand.nextDouble()*2-1)*(border/2)) * perturbation;
				rand_y = ((rand.nextDouble()*2-1)*(border/2)) * perturbation;
				
				//set grid with x and y values, and optional perturbations randomness
				grid[row][col][0] = (int) (x + rand_x);
				grid[row][col][1] = (int) (y + rand_y);
				
				x += tridim ;
			}
			
			x = border;
			y -= tridim;
		}
		
		
		//draw points
		for (int row = 0; row < tris; row++)
		{
			for (int col = 0; col < tris; col++)
			{
				Vertex3D p0 = new Vertex3D(grid[row][col][0], grid[row][col][1], 0, Color.WHITE);
				Vertex3D p1 = new Vertex3D(grid[row+1][col][0], grid[row+1][col][1], 0, Color.WHITE);
				Vertex3D p2 = new Vertex3D(grid[row][col+1][0], grid[row][col+1][1], 0, Color.WHITE);

				Vertex3D[] points = {p0, p1, p2};
				Polygon poly = Polygon.make(points);
				renderer.drawPolygon(poly, panel);
				
				Vertex3D p0_2 = new Vertex3D(grid[row][col+1][0], grid[row][col+1][1], 0, Color.WHITE);
				Vertex3D p1_2 = new Vertex3D(grid[row+1][col][0], grid[row+1][col][1], 0, Color.WHITE);
				Vertex3D p2_2 = new Vertex3D(grid[row+1][col+1][0], grid[row+1][col+1][1], 0, Color.WHITE);
				
				Vertex3D[] points2 = {p0_2, p1_2, p2_2};
				Polygon poly2 = Polygon.make(points2);
				renderer.drawPolygon(poly2, panel);
			}	
		}
	}
	
}
