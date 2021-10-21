public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double Gra = 6.67e-11;

	//constructor to initialize 
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	//copy constructor
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/**
	 calculate the numbers
	 **/
	public double calcDistance(Planet p){
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p){
		return Gra*p.mass*this.mass/this.calcDistance(p)/this.calcDistance(p);
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p)*(p.xxPos - this.xxPos)/this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p)*(p.yyPos - this.yyPos)/this.calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] p){
		double net = 0;
		int ii = 0;
		while(ii<p.length){
			if(!this.equals(p[ii])){
				net = net + this.calcForceExertedByX(p[ii]);
			}
			ii=ii+1;
		}
		return net;
	}

	public double calcNetForceExertedByY(Planet[] p){
		double net = 0;
		int ii = 0;
		while(ii<p.length){
			if(!this.equals(p[ii])){
				net = net + this.calcForceExertedByY(p[ii]);
			}
			ii=ii+1;
		}
		return net;
	}

	public void update(double dt, double fX, double fY){
		double ax = fX/this.mass;
		double ay = fY/this.mass;
		this.xxVel = this.xxVel + dt*ax;
		this.yyVel = this.yyVel + dt*ay;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}

	public void draw() {
	 	StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}
}