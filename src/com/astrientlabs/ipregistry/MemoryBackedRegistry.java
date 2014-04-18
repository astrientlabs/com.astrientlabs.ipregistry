/*
 * Copyright (C) 2005 Astrient Labs, LLC Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance 
 * with the License. You may obtain a copy of the License at 
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Astrient Labs, LLC. 
 * www.astrientlabs.com 
 * rashid@astrientlabs.com
 * Rashid Mayes 2005
 */
package com.astrientlabs.ipregistry;

import com.astrientlabs.trie.ByteTrie;
import com.astrientlabs.trie.ByteTrieKey;
import com.astrientlabs.trie.Trie;


public class MemoryBackedRegistry extends Registry
{
	private Trie tempDatabase = new ByteTrie();
	private Trie database = new ByteTrie();
	
	public String search(String ipAddress) 
	{
		return String.valueOf(database.find(new ByteTrieKey(new BitSequence(ipAddress).toString())));
	}
	
	public void add(String ip, String country)
	{
		tempDatabase.insert(new ByteTrieKey(new BitSequence(ip).toString()),country);
	}
	
	public void publish()
	{
		database = tempDatabase;
		tempDatabase = new ByteTrie();
	}
}
