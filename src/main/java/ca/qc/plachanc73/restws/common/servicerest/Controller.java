package ca.qc.plachanc73.restws.common.servicerest;

import org.springframework.http.converter.HttpMessageNotReadableException;

public interface Controller {

	public static final String PATH_SERVICE = "/service";

	void handle(HttpMessageNotReadableException e);
}