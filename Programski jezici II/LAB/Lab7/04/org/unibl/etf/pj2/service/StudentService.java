package org.unibl.etf.pj2.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.unibl.etf.pj2.model.Student;

public class StudentService {

	private static final String STUDENT_STORE_PATH = "students";
	private static final String GRADES_PATH = "grades.txt";

	public static boolean createStudentFile(Student student) {
		File destinationFolder = new File(STUDENT_STORE_PATH);
		boolean folderExists = destinationFolder.exists();
		if (!folderExists) {
			folderExists = destinationFolder.mkdir();
		}

		if (folderExists) {
			try {
				PrintWriter pw = new PrintWriter(new File(STUDENT_STORE_PATH + File.separator + student.getIndex()));
				pw.println(student);
				pw.close();
				return true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void updateData() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(GRADES_PATH));
		String s;
		while ((s = in.readLine()) != null) {
			String[] params = s.split(" ");
			try {
				createStudentFile(new Student(
						params[0], 
						Double.parseDouble(params[1]), 
						Double.parseDouble(params[2]),
						Double.parseDouble(params[3]), 
						Double.parseDouble(params[4])));
			} catch (NumberFormatException | IndexOutOfBoundsException ex) {
				ex.printStackTrace();
			}
		}
		in.close();
	}
}
