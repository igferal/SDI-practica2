package com.sdi.business.impl;

import com.sdi.business.ApplicationService;
import com.sdi.business.impl.classes.ApplicationSave;
import com.sdi.model.Application;

public class SimpleApplicationService implements ApplicationService {

	@Override
	public boolean save(Application application) {
		return ApplicationSave.save(application);
	}

}
