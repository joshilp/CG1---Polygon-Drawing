Implemented the BresenhamLineRenderer, DDALineRenderer, and AntiAliasingLineRenderer.

The BresenhamLineRenderer makes use of simple math to calculate the line. The AntiAliasingLineRenderer was more complex. Using algebra to calculate the distance value, I calculted the area of the circle in a given pixel that a line would fill. Based on the percentage of the area, I would scale the color by that amount. I used the DDALineDrawer to draw the line, and would only put the new pixel in the given coordinate if the new value was whiter. This allows for any overlapping lines to continue adding to the given pixel coordinate.

I created the FilledPolygonRenderer by getting the points from the given chain function. Extractingt he points from the chains, I could verify which point was at the top, and which was to the left. Then I would check for different cases. If the top of the triangle was horizontal, the DDALineDrawer would draw from the top to the bottom. If the top wasn't flat, then I would start both left and right points from the top point, and work its way down to the bottom left and right points. If the bottom was not flat, I would change the slope for the side where the middle point is during the loop to continue drawing to the bottom. 

For test cases, I implemented the ParallelogramTest calculating points used to draw the parallelogram. I also implemented the RandomLineTest which created random lines by setting two random points and drawing lines between them.

I also implemented the StarburstPolygonTest, which creates teh circle by using the functionality of the StarburstLineTest, but modified for polygons. StarburstPolygonTest takes 3 points to create a polygon, and draws them in a circle using random colors

The MeshPolygonTest was also created by using a 3 dimensional array to store x,y coordinates of the whole mesh. at each grid point, we can store point values. Then in a nested loop, we can pick 3 points of the grid to use as points for the polygon. To add perturbation, if the parameter is activated, each point in the grid adds a random value to move each point.

The RandomPolygonTest was the simplest to create. Only had to pick three points and random x,y values, then draw the polygon.

I finish everything in the assignment. The only place I feel like I could improve is that the are a couple single pixels that don't get filled in the polygon tests. I am assuming that this is because of some sort of round-off error during the calculations.