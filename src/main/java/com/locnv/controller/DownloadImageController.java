package com.locnv.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

@WebServlet(urlPatterns = "/image") // ?fname=abc.png
public class DownloadImageController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String fileName = req.getParameter("fname");

		final String dir = "F:\\upload";

		File file = new File(dir + "/" + fileName);

		res.setContentType("image/jpeg");

		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), res.getOutputStream());
		}
	}
}
