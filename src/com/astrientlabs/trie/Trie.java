package com.astrientlabs.trie;

public interface Trie
{
    public void insert(TrieKey trieKey, Object value);
    public void insert(TrieNode trieNode);
    public Object find(Object value);
    public Object find(TrieKey trieKey);
}
