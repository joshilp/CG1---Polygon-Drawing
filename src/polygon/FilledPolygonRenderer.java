package polygon;

import geometry.Vertex3D;
import line.DDALineRenderer;
import line.LineRenderer;
import windowing.drawable.Drawable;
import windowing.graphics.Color;

public class FilledPolygonRenderer implements PolygonRenderer {

	private FilledPolygonRenderer() {}
	
	@Override
	public void drawPolygon(Polygon polygon, Drawable drawable, Shader vertexShader) {
		
		LineRenderer DDAdrawer = DDALineRenderer.make();
		
		//ensure point are clockwise
		polygon = Polygon.makeEnsuringClockwise(polygon.get(0),polygon.get(1),polygon.get(2));
		
		//for checking chains
		Chain LChain = polygon.leftChain();
		Chain RChain = polygon.rightChain();
		
		//get points from chain
		Vertex3D p0 = RChain.vertices.get(0);
		Vertex3D p1 = LChain.vertices.get(1);
		Vertex3D p2 = RChain.vertices.get(1);
		
		//checking chains
		int lengthL = LChain.numVertices;		
		int lengthR = RChain.numVertices;
		
		//Initialize variables
		int p0_x = p0.getIntX();
		int p0_y = p0.getIntY();
		
		int p1_x = p1.getIntX();
		int p1_y = p1.getIntY();
		
		int p2_x = p2.getIntX();
		int p2_y = p2.getIntY();
		
		double dx_left = p0_x - p1_x;
		double dy_left = p0_y - p1_y;
		double m_left = dx_left/dy_left;
		
		double dx_right = p2_x - p0_x;
		double dy_right = p2_y - p0_y;
		double m_right = dx_right/dy_right;
		
		double dx_low = p1_x - p2_x;
		double dy_low = p1_y - p2_y;
		double m_low = dx_low/dy_low; 
		
		double y_middle = Math.max(p1_y, p2_y);
		double y_bottom = Math.min(p1_y, p2_y);
		
		double fx_left = p0_x;
		double fx_right = p0_x;
		
		int xleft = p0_x;
		int xright = p0_x;
		
		
		//if left is flat, first point on left is at top
		if (dy_left == 0)
		{
			fx_left = p1_x;
		}
		
		//if right is flat, first point on right is at top
		if (dy_right == 0)
		{
			fx_right = p2_x;
		}
		
		//get random color
		Color color = Color.random();
		
		for (int y = p0_y; y > y_bottom; y--)
		{
			//round left and right to int
			xleft = (int)Math.round(fx_left);
			xright = (int)Math.round(fx_right);
			
			
			//check if right side is greater than left side
			//ignores drawing last pixel on right
			if (xleft <= xright-1)
			{
				draw(xleft, xright-1, y, drawable, color, DDAdrawer);
			}
			
			//if middle point exists, and if above middle, adjust left and right slopes
			if (y > y_middle)
			{
				fx_left -= m_left;
				fx_right -= m_right;
			}
			
			//if left side is middle point, adjust its side with new slope value
			if (y <= y_middle && p1_y > p2_y)
			{
				fx_left -= m_low;
				fx_right -= m_right;
			}
			
			//if right is middle point, adjust its side with new slope value
			if (y <= y_middle && p1_y < p2_y)
			{
				fx_left -= m_left;
				fx_right -= m_low;	
			}
		}	
	}
	
	public static PolygonRenderer make() {
		return new FilledPolygonRenderer();
	}
	
	//draws the polygon
	private void draw(int xleft, int xright, int y, Drawable drawable, Color color, LineRenderer DDAdrawer)
	{
		Vertex3D v3d_xleft = new Vertex3D(xleft, y, 0, color);
		Vertex3D v3d_xright = new Vertex3D(xright, y, 0, color);
		DDAdrawer.drawLine(v3d_xleft, v3d_xright, drawable);
	}
	
}