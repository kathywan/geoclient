/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gov.nyc.doitt.gis.geoclient.service.search.task;

import static org.junit.Assert.*; 
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import gov.nyc.doitt.gis.geoclient.parser.LocationTokens;
import gov.nyc.doitt.gis.geoclient.service.search.Fixtures;
import gov.nyc.doitt.gis.geoclient.service.search.SearchResult;
import gov.nyc.doitt.gis.geoclient.service.search.policy.SearchPolicy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SearchTaskFactoryTest
{
	@InjectMocks
	private SearchTaskFactory searchTaskFactory;
	
	@Mock
	private InitialSearchTaskBuilder initialSearchTaskBuilder;
	
	@Mock
	private SpawnedSearchTaskBuilder spawnedSearchTaskBuilder;

	private SearchPolicy searchPolicy;
	
	private LocationTokens locationTokens;

	private List<SearchTask> expectedTasks;
	
	@Before
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		locationTokens = new Fixtures().locationTokens;
		searchPolicy = new SearchPolicy();
		expectedTasks = new ArrayList<>();
	}

	@Test
	public void testBuildInitialSearchTasks()
	{
		Mockito.when(initialSearchTaskBuilder.getSearchTasks(searchPolicy, locationTokens)).thenReturn(expectedTasks);
		List<SearchTask> result = searchTaskFactory.buildInitialSearchTasks(searchPolicy, locationTokens);
		assertThat(result, sameInstance(expectedTasks));
	}

	@Test
	public void testBuildSubsearchTasks()
	{
		SearchResult searchResult = new SearchResult(searchPolicy, locationTokens);
		Mockito.when(spawnedSearchTaskBuilder.getSearchTasks(searchResult)).thenReturn(expectedTasks);
		List<SearchTask> result = searchTaskFactory.buildSubsearchTasks(searchResult);
		assertThat(result, sameInstance(expectedTasks));
	}

}
