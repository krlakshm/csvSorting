package com.hm.sort.utill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CommonUtill {

	public File getFileToSort(String fileName) {
		File file = null;
		try {
			ClassLoader classLoader = new CommonUtill().getClass().getClassLoader();
			file = new File(classLoader.getResource(fileName).getFile());
		}catch (NullPointerException np) {
			throw new NullPointerException("Not a valid file: "+fileName);
		}
		return file;
	}

	@SuppressWarnings("resource")
	public List<String> getCsvFileContent(String fileName) {
		List<String> lstCsvContent = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(getFileToSort(fileName)));
			String csvCoulmn = br.readLine();
			if(csvCoulmn == null) {
				throw new NullPointerException("File does not have any data.");
			}
			while (csvCoulmn != null) {
				lstCsvContent.add(csvCoulmn);
				csvCoulmn = br.readLine();
			}
		} catch (FileNotFoundException fe) {
			System.out.println("File not found "+ fe.getMessage());
		} catch (IOException e) {
			System.out.println("Error while reading the file "+e.getMessage());
		}
		return lstCsvContent;
	}
}
