package com.proficiency.excercise.modal;

import java.util.ArrayList;
import java.util.List;

public class ListData {

	private String title;
	private ArrayList<Row> rows = new ArrayList<Row>();

	/**
	 * 
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return The rows
	 */
	public ArrayList<Row> getRows() {
		return rows;
	}

	/**
	 * 
	 * @param rows
	 *            The rows
	 */
	public void setRows(ArrayList<Row> rows) {
		this.rows = rows;
	}

}