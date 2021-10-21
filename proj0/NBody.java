public class NBody{
	/**
	 * return the radius of the universe in the given file
	 */

	public static double readRadius(String dataFileName){
		In in = new In(dataFileName);
		in.readInt();
		return in.readDouble();
	}
	/**
	 * return an array of Planets corresponding to the planets in the given file
	 */
	 public static Planet[] readPlanets(String planetFileName){
	 	In in = new In(planetFileName);;
	 	int number = in.readInt();
	 	Planet[] pp = new Planet[number];

	 	in.readDouble();
	 	for(int i=0;i<number;i++){
	 		pp[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
	 	}
	 	return pp;
	 }

	 public static void main(String[] args){
	 	double T = Double.parseDouble(args[0]);
	 	double dt = Double.parseDouble(args[1]);
	 	String filename = args[2];

	 	Planet[] pps = readPlanets(filename);
	 	double rr = readRadius(filename);

	 	StdDraw.enableDoubleBuffering();

	 	StdDraw.setScale(-rr,rr);
	 	StdDraw.clear();

	 	double tt = 0;
	 	while(tt<=T){
	 		double xForces[] = new double[pps.length];
	 		double yForces[] = new double[pps.length];
	 		for(int i = 0; i < pps.length; i++){
	 			xForces[i] = pps[i].calcNetForceExertedByX(pps);
	 			yForces[i] = pps[i].calcNetForceExertedByY(pps);
	 		}

	 		for(int i = 0; i< pps.length;i++){
	 			pps[i].update(dt,xForces[i],yForces[i]);
	 		}

	 		StdDraw.picture(0,0,"images/starfield.jpg");

	 		for(int i = 0; i < pps.length; i++){
	 			pps[i].draw();
	 		}

	 		StdDraw.show();
	 		StdDraw.pause(10);
	 		tt = tt + dt;
	 	}

	 	//print the universe
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
   		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
   		}
	 }	
}