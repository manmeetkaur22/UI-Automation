package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	public static Iterator<User> readCSVFile(String filename) {

		File csvFile = new File(
				System.getProperty("user.dir") + File.separator + "testData" + File.separator + "loginData.csv");
		FileReader fileReader = null;
		CSVReader csvReader;
		String [] line;
		User userData;
		List<User> userList=null;
		try {
			fileReader = new FileReader(csvFile);
			csvReader = new CSVReader(fileReader);
			csvReader.readNext();//Skip first line from excel(column name)
			userList=new ArrayList<User>();
			while ((line=csvReader.readNext())!=null)
			{
				userData=new User(line[0],line[1]);
				userList.add(userData);
			}
			
		} catch (CsvValidationException | IOException e) {
			e.printStackTrace();
		}
		return userList.iterator();

	}

}
