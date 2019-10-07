package practica;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Fichero {
	static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Genera el formulario y validaciones
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
		
		//Recoge los datos y los separa por comas
		String datos = nom + "," + sexe + "," + edat + "," + suspensos + "," + residencia + "," + ingresos;
		
		//Crea el archivo con los datos
		FileOutputStream archivo = new FileOutputStream("becadades.dat");
		try {
			DataOutputStream fos = new DataOutputStream(archivo);
			fos.writeUTF(datos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Lee los datos y los presenta
		FileInputStream entrada = new FileInputStream("becadades.dat");
		DataInputStream fis = new DataInputStream(entrada);
		String[] arrayDatos = new String[0];
		try {
			arrayDatos = fis.readUTF().split(",");
			System.out.println("\nDocumento");
			System.out.printf(
					"Nom y Cognom: %s\nSexe: %s\nEdat: %s\nNumero de suspensos del curs anterior:  %s\nResidencia familiar: %s\nIngressos anuals de la familia: %s\n",
					arrayDatos[0], arrayDatos[1], arrayDatos[2], arrayDatos[3], arrayDatos[4], arrayDatos[5]);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Genera backup
		FileOutputStream backup = new FileOutputStream("becadadesBK.dat");
		DataOutputStream fos = new DataOutputStream(backup);
		try {
			for (int i = 0; i < arrayDatos.length; i++) {
				fos.writeUTF(arrayDatos[i]);
			}
			fos.flush();
			fos.close();
			System.out.println("\nBackup creado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Valida que sea un integer
	private static int validacion() {
		while (!reader.hasNextInt()) {
			reader.next();
			System.out.println("No valido");
		}
		int numero = reader.nextInt();
		return numero;
	}
}
