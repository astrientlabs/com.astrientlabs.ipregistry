package com.astrientlabs.trie;

import java.util.ArrayList;


public class ByteTrie implements Trie
{
    protected TrieNode root;
    
    
    public ByteTrie()
    {
        root = new TrieNode();
        root.children = new ArrayList<TrieNode>(2);
    }
    

    public Object find(Object value)
    {
        if ( value instanceof byte[] )
        {
            return find( new ByteTrieKey((byte[])value) );
        }
        else
        {
            return find( new ByteTrieKey(String.valueOf(value).getBytes()));
        }

    }
    
    public Object find(TrieKey trieKey)
    {
        
        TrieNode closest = root.find(trieKey);

        if ( closest == null )
        {
            return null;
        }
        else
        {
            TrieKey lastSearch = trieKey;
            trieKey = trieKey.subtract(closest.trieKey);

            
            TrieNode tempNode = closest;
            
            
            //attempt to find node closer to key in closest's children
            //change to do-while in iteration 2 
            while ( (closest = closest.find(trieKey)) != null )
            {
                tempNode = closest;
                lastSearch = trieKey;
                trieKey = trieKey.subtract(closest.trieKey);
            }
            
            trieKey = lastSearch;
            
            return tempNode.value;
        }
    }
    
    
    
    public void insert(TrieKey trieKey, Object value)
    {
        insert(new TrieNode(trieKey,value));
    }
    
    public void insert(TrieNode trieNode)
    {
        
        TrieNode match = root;
        TrieNode lastMatching = root;
        TrieKey trieKey = trieNode.trieKey;
        
        while (  (match = match.find(trieKey)) != null )
        {
            //match = match.find(trieKey);
            lastMatching = match;
            int common = match.trieKey.common(trieKey);
            
            if ( common == trieKey.dataLength() && match.trieKey.dataLength() == common )
            {
                //exact match
                match.value = trieNode.value;
                break;
            }
            else if ( common == trieKey.dataLength() )
            {
                //key is less than match; demote match
               
                TrieKey newKey = trieKey.intersection(match.trieKey);
                
                TrieNode matchCopy = new TrieNode();
                matchCopy.children = match.children;
                matchCopy.value = match.value;
                matchCopy.trieKey = match.trieKey.subtract(trieKey);
                
                match.trieKey = newKey;
                match.value = trieNode.value;
                match.children = new ArrayList<TrieNode>(1);
                match.children.add(matchCopy);
                
                break;
            }
            else if ( common < trieKey.dataLength() && common != match.trieKey.dataLength() )
            {
                //make the nodes peers
                TrieKey newKey = trieKey.intersection(match.trieKey);
               
                TrieNode newNode = new TrieNode();
                newNode.trieKey = trieKey.subtract(newKey);
                newNode.value = trieNode.value;
                

                
                TrieNode matchCopy = new TrieNode();
                matchCopy.children = match.children;
                matchCopy.value = match.value;
                matchCopy.trieKey = match.trieKey.subtract(newKey);
                
                match.trieKey = newKey;
                match.value = null;
                match.children = new ArrayList<TrieNode>(2);
                match.children.add(matchCopy);
                match.children.add(newNode);
                
                break;
            }
            else //if ( common == match.trieKey.dataLength() )
            {
                //continue searching in children
                trieKey = trieKey.subtract(match.trieKey);
            }            
        }
        
        if ( match == null )
        {
            TrieNode newNode = new TrieNode();
            newNode.trieKey = trieKey;
            newNode.value = trieNode.value;
            
            if ( lastMatching.children == null )
            {
                lastMatching.children = new ArrayList<TrieNode>(1);
            }
            
            lastMatching.children.add(newNode);
        }        
    }
}
