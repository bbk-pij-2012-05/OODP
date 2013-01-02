class Point{
	double x;
	double y;


void distanceTo(Point p){
	double dist = Math.sqrt(((x - p.x)*(x - p.x)) + ((y - p.y)*(y - p.y)));
	System.console().println("The distance is " + dist);
}
	
void distanceToOrigin(){
	Point originPoint = new Point();
	originPoint.x = 0;
	originPoint.y = 0;
	distanceTo(originPoint);
}
	
void moveTo(double xPoint, double yPoint){
	x = xPoint;
	y = yPoint;
}

void moveTo(Point p){
	x=p.x;
	y=p.y;
}

Point clone(){
	Point p = new Point();
	p.x = x;
	p.y = y;
	return p;
}

Point opposite(){
	Point p = new Point();
	p.x = (x * -1);
	p.y = (y * -1);
	return p;
}

}


Point thePoint = new Point();
thePoint.x = 10;
thePoint.y = 15;

System.console().println("This point is " + thePoint.x + ", " + thePoint.y);

System.console().println("So you want to know distance? From where?");
System.console().println("Whats the x-coord?");
Point distPoint = new Point();
distPoint.x = System.console().readLine();
System.console().println("Whats the y-coord?");
distPoint.y = System.console().readLine();
thePoint.distanceTo(distPoint);

System.console().println("So you want to know distance from the origin?");
thePoint.distanceToOrigin();

System.console().println("Let's move the point to a new point. Where do you want x?");
double newX = Double.parseDouble(System.console().readLine());
System.console().println("Where do you want y?");
double newY = Double.parseDouble(System.console().readLine());
thePoint.moveTo(newX, newY);
System.console().println("This point moved to " + thePoint.x + ", " + thePoint.y);
System.console().println("Let's move the point again.");
Point movePoint = new Point();
movePoint.x = 5;
movePoint.y= 6;
thePoint.moveTo(movePoint);
System.console().println("This point is now moved to point " + thePoint.x + ", " + thePoint.y);
System.console().println("Lets clone.");
Point practice = thePoint.clone();
System.console().println("The point is " + practice.x + ", " + practice.x);
System.console().println("Lets make it opposite.");
practice = thePoint.opposite();
System.console().println("The opposite point is " + practice.x + ", " + practice.x);