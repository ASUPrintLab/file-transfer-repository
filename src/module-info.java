module fileTrans {
	exports application;
	exports controllers;
	exports application_v2;

	requires animatefx;
	requires gson;
	requires java.desktop;
	requires java.logging;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires joda.time;
	requires json.simple;
	requires java.sql;
	
	opens controllers;
}