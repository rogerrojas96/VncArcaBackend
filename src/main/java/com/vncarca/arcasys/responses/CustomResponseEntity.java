/**
* Created by Roy Morocho.
* User: steve
* Date: 11 jun 2022
* Time: 17:14:17
*/
package com.vncarca.arcasys.responses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomResponseEntity extends HttpEntity {

	private HttpStatus status;
	private String message;
	private Object responseObj;

	/**
	 * @param status
	 * @param message
	 * @param responseObj
	 */
	public CustomResponseEntity(HttpStatus status, String message, Object responseObj) {
		this.status = status;
		this.message = message;
		this.responseObj = responseObj;
	}

	public CustomResponseEntity(HttpStatus status, String message) {

		this.status = status;
		this.message = message;
	}

	public ResponseEntity<Object> response() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (responseObj == null) {
				map.put("message", message);
			} else {
				map.put("message", message);
				map.put("data", responseObj);
			}

			return new ResponseEntity<Object>(map, status);
		} catch (Exception e) {
			map.clear();
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
			map.put("message", e.getMessage());
			map.put("data", null);
			return new ResponseEntity<Object>(map, status);
		}
	}

}
