package ru.stqa.pft.sandbox;

public class HomeTask1 {
	public static void main(String[] args) {
		System.out.println("My home task 1");

		Point p1 = new Point(3,5);
    Point p2 = new Point(6,9);
			System.out.println("Расстояние между двумя точками (" + p1.x + ";" + p1.y + ") и (" + p2.x + ";" + p2.y + ") = " + p1.distance(p2));
	}
}