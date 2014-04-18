package com.astrientlabs.trie;

import java.util.ArrayList;

public class TrieNode
{
    protected TrieKey trieKey;
    protected Object value;
    protected ArrayList<TrieNode> children;
    
    public TrieNode()
    {
        
    }
    
    
    public TrieNode(TrieKey trieKey, Object value)
    {
        this.trieKey = trieKey;
        this.value = value;
    }
    
    
    public TrieNode find(TrieKey trieKey)
    {
        TrieNode result = null;
        if ( children != null )
        {
            int max = 0;
            int matching = 0;

            for ( TrieNode trieNode : children )
            {
                if ( (matching = trieNode.trieKey.common(trieKey)) > max )
                {
                    result = trieNode;
                    max = matching;
                }
            }
        }
        
        
        return result;
    }
    
    public String toString()
    {
        return trieKey + "/" + value + " " + children;
    }
}
