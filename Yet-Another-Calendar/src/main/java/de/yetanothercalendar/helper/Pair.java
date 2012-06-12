package de.yetanothercalendar.helper;

public class Pair<A, B> {
	private A a;
	private B b;

	public Pair(A a, B b) {
		super();
		this.a = a;
		this.b = b;
	}

	public Pair() {
		super();
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}
