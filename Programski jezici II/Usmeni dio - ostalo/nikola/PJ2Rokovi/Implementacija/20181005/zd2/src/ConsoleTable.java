package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.*;
import java.util.stream.Collectors;
import java.lang.reflect.*;

public class ConsoleTable {
	private BiFunction<Object, ArrayList<Field>, String> linePreparationFunction;
	private ArrayList<Field> allFields;
	private int columnWidth;
	private ArrayList<Object> objects;
	
	public ConsoleTable(ArrayList<Field> allFields, BiFunction<Object, ArrayList<Field>, String> linePreparationFunction, int columnWidth, ArrayList<Object> objects) {
		this.linePreparationFunction = linePreparationFunction;
		this.columnWidth = columnWidth;
		this.allFields = allFields;
		this.objects = objects;
	}
	
	public <T>void print(Predicate<T> predicate, Class<T> type) throws Exception {
		if(predicate == null) {
			
			String header = "";
			String headerBreak = "";
			
			header += "Redni. br";
			int lineNum = columnWidth - header.length();
			while(lineNum > 0){
				header += " ";
				lineNum--;
			}
			//lineNum++;
			header += Main.EMPTY_STRING;
			for(Field field : allFields){
				String fieldName = field.getName();
				int fillingLength = columnWidth - fieldName.length();
				while(fillingLength > 0){
					fieldName += " ";
					fillingLength--;
				}
				header += fieldName;
				header += Main.EMPTY_STRING;
				headerBreak += Main.BREAK_SEGMENT + Main.BREAK_SEGMENT;
			}
			
			System.out.println(header + "\n" + headerBreak);

			for(Object obj : objects) {
				String toPrint = "";
				toPrint += Integer.valueOf(lineNum++).toString();
				int fillingLength = columnWidth - toPrint.length();
				while(fillingLength > 0){
					toPrint += " ";
					fillingLength--;
				}
				toPrint += Main.EMPTY_STRING;
				toPrint += linePreparationFunction.apply(obj, allFields);
				System.out.println(toPrint);
			}
		} else {
			ArrayList<Object> filteredObjects= new ArrayList<>(Arrays.asList(objects.stream().filter(t -> t.getClass().equals(type)).toArray()));
			String header = "";
			String headerBreak = "";
			
			header += "Redni. br";
			int lineNum = columnWidth - header.length();
			while(lineNum > 0){
				header += " ";
				lineNum--;
			}
			header += Main.EMPTY_STRING;
			for(Field field : allFields){
				if(Arrays.asList(type.getDeclaredFields()).contains(field)) {
				String fieldName = field.getName();
				int fillingLength = columnWidth - fieldName.length();
				while(fillingLength > 0){
					fieldName += " ";
					fillingLength--;
				}
				header += fieldName;
				header += Main.EMPTY_STRING;
				headerBreak += Main.BREAK_SEGMENT + Main.BREAK_SEGMENT;
				}
			}
			
			System.out.println(header + "\n" + headerBreak);

			for(Object obj : filteredObjects) {
				if(predicate.test(((T)obj))){
				String toPrint = "";
				toPrint += Integer.valueOf(lineNum++).toString();
				int fillingLength = columnWidth - toPrint.length();
				while(fillingLength > 0){
					toPrint += " ";
					fillingLength--;
				}
				toPrint += Main.EMPTY_STRING;
				toPrint += linePreparationFunction.apply(obj, new ArrayList<Field>(Arrays.asList(allFields.stream().filter(t -> t.getDeclaringClass().equals(type)).toArray(Field[]::new))));;
				System.out.println(toPrint);
				}
			}
		}
	}
}