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
package gov.nyc.doitt.gis.geoclient.service.search.policy;

import static org.junit.Assert.*; 
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;

public class DefaultSimilarNamesPolicyTest
{
	private DefaultSimilarNamesPolicy policy;

	@Before
	public void setUp() throws Exception
	{
		this.policy = new DefaultSimilarNamesPolicy();
	}

	@Test
	public void testIsSimilarName()
	{
		assertTrue(policy.isSimilarName("BRO", "BROADWAY"));
		assertTrue(policy.isSimilarName("BRODWAY", "BRODWAY"));
		assertTrue(policy.isSimilarName("BRODWAY", "BROADWAY"));
		assertTrue(policy.isSimilarName("BROAD", "BROADWAY"));
		assertTrue(policy.isSimilarName("BRADWAY", "BROADWAY"));
		assertTrue(policy.isSimilarName("ROADWAY", "BROADWAY"));
		assertTrue(policy.isSimilarName("100 ST", "WEST 100 STREET"));
		assertTrue(policy.isSimilarName("100 ST", "EAST 100 STREET"));
		assertTrue(policy.isSimilarName("WEST 100 AVE", "WEST 100 STREET"));
		assertTrue(policy.isSimilarName("EasTeRN PKWY", "EASTERN PARKWAY"));
		assertTrue(policy.isSimilarName("BROAD STREET", "BROADWAY"));
		assertTrue(policy.isSimilarName("BRODWAY", "BROAD STREET"));
		assertTrue(policy.isSimilarName("BRODWAY", "BROWN BOULEVARD"));
		assertFalse(policy.isSimilarName("BRODWAY", "WB BRIDGE APPROACH"));
	}

	@Test
	public void testClean()
	{
		assertThat(policy.clean(""), equalTo(""));
		assertThat(policy.clean(" "), equalTo(" "));
		assertThat(policy.clean("a"), equalTo("A"));
		assertThat(policy.clean("a "), equalTo("A"));
		assertThat(policy.clean(" a "), equalTo("A"));
		assertThat(policy.clean(" ave a "), equalTo("A"));
		assertThat(policy.clean("St Marks"), equalTo("MARKS"));
		// TODO more tests
	}

}
