package practica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Fichero {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Nom i Cognom:");
		String nom = reader.nextLine();
		String sexe;
		System.out.println("Sexe (H | M):");
		do {
			sexe = reader.nextLine();
			if (!sexe.equalsIgnoreCase("H") && !sexe.equalsIgnoreCase("M")) {
				System.out.println("Ingresa una opcion valida");
			}
		} while (!(sexe.equalsIgnoreCase("H") || sexe.equalsIgnoreCase("M")));
		System.out.println("Edat (20 | 60):");
		int edat;
		do {
			edat = validacion();
			if (edat < 20 || edat > 60) {
				System.out.println("Introduce un numero valido");
			}
		} while (edat < 20 || edat > 60);
		System.out.println("Numero de suspensos del curs anterior (0 | 4):");
		int suspensos;
		do {
			suspensos = validacion();
			if (suspensos < 0 || suspensos > 4) {
				System.out.println("Introduce un numero valido");
			}
		} while (suspensos < 0 || suspensos > 4);
		System.out.println("Residència familiar (SI | NO):");
		String residencia;
		reader.nextLine();
		do {
			residencia = reader.nextLine();
			if (!residencia.equalsIgnoreCase("SI") && !residencia.equalsIgnoreCase("NO")) {
				System.out.println("Ingresa una opcion valida");
			}
		} while (!(residencia.equalsIgnoreCase("SI") || residencia.equalsIgnoreCase("NO")));
		System.out.println("Ingressos anuals de la família:");
		int ingresos = validacion();

		byte[] data = (nom + sexe + edat + suspensos + residencia + ingresos).getBytes();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("becadades.dat");
			fos.write(data, 0, data.length);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File inputFile = new File("becadades.dat");
		data = new byte[(int) inputFile.length()];
		FileInputStream fis = new FileInputStream(inputFile);
		try {
			fis.read(data, 0, data.length);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int validacion() {
		while (!reader.hasNextInt()) {
			reader.next();
			System.out.println("No valido");
		}
		int numero = reader.nextInt();
		return numero;
	}
}
