package com.algaworks.algamoney.api.repository.launch;

import java.util.List;

import com.algaworks.algamoney.api.model.Launch;
import com.algaworks.algamoney.api.repository.filter.LaunchFilter;

public interface LaunchRepositoryQuery {
	
	public List<Launch> filter(LaunchFilter launchFilter);
}
