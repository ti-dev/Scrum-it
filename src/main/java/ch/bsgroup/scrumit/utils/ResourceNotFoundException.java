package ch.bsgroup.scrumit.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long resourceId;
	private Integer resourceIntId;
	
	public ResourceNotFoundException(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public ResourceNotFoundException(Integer resourceIntId) {
		this.resourceIntId = resourceIntId;
	}

	public Integer getResourcIntId() {
		return resourceIntId;
	}
}