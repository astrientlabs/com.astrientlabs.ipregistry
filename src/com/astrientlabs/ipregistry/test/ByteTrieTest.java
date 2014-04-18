package com.astrientlabs.ipregistry.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.astrientlabs.trie.ByteTrie;
import com.astrientlabs.trie.ByteTrieKey;

public class ByteTrieTest {

	@Test
	public void test() {
		
		
        ByteTrie trie = new ByteTrie();

        trie.insert(new ByteTrieKey("01010001"),"DE");
        trie.insert(new ByteTrieKey("01010011"),"OL");
        trie.insert(new ByteTrieKey("11110001"),"LA");
        trie.insert(new ByteTrieKey("11010001"),"NYC");
        trie.insert(new ByteTrieKey("01011001"),"KI");
        trie.insert(new ByteTrieKey("01000001"),"AS");
        trie.insert(new ByteTrieKey("01110001"),"UU");
        
        assertEquals("ByteTrieKey 11010001 == NYC", "NYC", trie.find("11010001"));
        
        trie = new ByteTrie();
        trie.insert(new ByteTrieKey("has"),"jk");
        trie.insert(new ByteTrieKey("hasnt"),"wk");
        trie.insert(new ByteTrieKey("hippo"),"534k");
        trie.insert(new ByteTrieKey("got"),".ok");
        trie.insert(new ByteTrieKey("goner"),"cok");
        trie.insert(new ByteTrieKey("gomezer"),"rok");
        trie.insert(new ByteTrieKey("giving"),"qok");
        trie.insert(new ByteTrieKey("gome"),"oek");
        trie.insert(new ByteTrieKey("gomez"),"otk");

        assertEquals("ByteTrieKey gomezz == otk", "otk", trie.find("gomezz"));
	}

}
