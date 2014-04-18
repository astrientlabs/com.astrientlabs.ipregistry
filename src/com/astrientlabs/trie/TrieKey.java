package com.astrientlabs.trie;

public interface TrieKey
{
    public int common(TrieKey trieKey);
    public TrieKey intersection(TrieKey trieKey);
    public TrieKey subtract(TrieKey trieKey);
    public int dataLength();
}
