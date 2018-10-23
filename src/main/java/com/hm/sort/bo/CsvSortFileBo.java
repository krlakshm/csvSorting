package com.hm.sort.bo;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hm.sort.utill.CommonUtill;

public class CsvSortFileBo {

	CommonUtill commonUtill = new CommonUtill();

	public void sortCsvFile(String[] args){

		if( args != null && args.length >= 4){
			Map<Integer, Integer>  sortedCsvMap = getDependentCsvSortData(args[2], args[3]);
			List<String> csvFileSortedData = sortMainCsvDataBasedOnAnotherCsv(args[0], args[1], sortedCsvMap);

			for(String fileContent : csvFileSortedData) {
				System.out.println(fileContent);
			}

		}else{
			throw new IllegalArgumentException("Need minmum 4 arguments to sort the csv file, arguments should be like './file1.csv b ./file2.csv d' ");
		}
	}

	private Map<Integer, Integer> getDependentCsvSortData(String fileName, String columnName) {
		List<String> csvFileContents = commonUtill.getCsvFileContent(fileName);
		Map<Integer, Integer> sortedCsvMap = new HashMap<Integer, Integer>();
		if(!csvFileContents.isEmpty()) {
			String csvHeaderColumn = csvFileContents.remove(0);
			int columnNamePositionToBeSort = getColumnNamePositionToBeSort(csvHeaderColumn, columnName);
			Integer index = 0;
			for(String column : csvFileContents) {
				String[] csvFilecolumns = column.split(",");
				sortedCsvMap.put(Integer.valueOf(csvFilecolumns[columnNamePositionToBeSort]), index++);
			}
		}
		return sortedCsvMap;
	}

	private List<String> sortMainCsvDataBasedOnAnotherCsv(String fileName, String columnName, final Map<Integer, Integer> sortedCsvMap ) {
		List<String> csvFileContentsToBeSort = commonUtill.getCsvFileContent(fileName);
		String csvHeader = csvFileContentsToBeSort.remove(0);
		final int columnNamePositionToBeSort = getColumnNamePositionToBeSort(csvHeader, columnName);
		Collections.sort(csvFileContentsToBeSort, new Comparator<String>() {
			public int compare(String leftcolumn, String rightcoloumn) {
				Integer leftIndex = getIndex(leftcolumn, columnNamePositionToBeSort, sortedCsvMap);
				Integer rightIndex = getIndex(rightcoloumn, columnNamePositionToBeSort, sortedCsvMap);
				if (leftIndex == null) {
					return 1;
				}
				if (rightIndex == null) {
					return -1;
				}
				return Integer.compare(leftIndex, rightIndex);
			}
		});
		csvFileContentsToBeSort.add(0, csvHeader);
		return csvFileContentsToBeSort;
	}

	private int getColumnNamePositionToBeSort(String csvHeader, String columnName) {
		String[] csvHeaderColumns = csvHeader.split(",");
		int columnPosition =0;
		Map<String, Integer> csvHeaderMap = new HashMap<String, Integer>();
		for(String column: csvHeaderColumns){
			csvHeaderMap.put(column, columnPosition++);
		}
		if(csvHeaderMap.get(columnName) != null){
			return csvHeaderMap.get(columnName);
		} else {
			throw new NullPointerException("Column name not found: "+ columnName);
		}
	}

	private Integer getIndex(String csvColumn, int columnNamePositionToBeSort, final Map<Integer, Integer> sortedCsvMap ){
		String[] csvColumns = csvColumn.split(",");
		Integer index = sortedCsvMap.get(Integer.valueOf(csvColumns[columnNamePositionToBeSort]));
		return index;
	}
}
