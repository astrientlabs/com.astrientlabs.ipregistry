package com.astrientlabs.trie;

public class ByteTrieKey implements TrieKey
{
    protected byte[] data;
    
    public ByteTrieKey(String value)
    {
        this(value.getBytes());
    }
    
    
    public ByteTrieKey(byte[] data)
    {
        this.data = data;
    }

    public int common(TrieKey trieKey)
    {
        int common = 0;
        if ( trieKey instanceof ByteTrieKey )
        {
            ByteTrieKey comp = (ByteTrieKey)trieKey;
            if ( data != null && comp.data != null )
            {
                for (;common < data.length && common < comp.data.length; common++)
                {
                    if ( data[common] != comp.data[common] ) break;
                }               
            }
        }
        
        return common;
    }
    
    
    public TrieKey intersection(TrieKey trieKey)
    {
        byte[] result = new byte[0];
        
        if ( trieKey instanceof ByteTrieKey )
        {
            int common = common(trieKey);
            if ( common > 0 )
            {    
                result = new byte[common];
                System.arraycopy(data,0,result,0,common);
            } 
        }
        

        return new ByteTrieKey(result);
    }
    
    
    public TrieKey subtract(TrieKey trieKey)
    {
        byte[] result = new byte[0];
        
        if ( trieKey instanceof ByteTrieKey )
        {
            int common = common(trieKey);
            if ( common > 0 )
            {    
                result = new byte[data.length-common];
                System.arraycopy(data,common,result,0,data.length-common);
            } 
        }
        

        return new ByteTrieKey(result);
    }
    
    public int dataLength()
    {
        return data.length;
    }
    
    public boolean equals(Object trieKey)
    {
        if ( trieKey instanceof ByteTrieKey )
        {
            ByteTrieKey comp = (ByteTrieKey)trieKey;
            if ( comp.dataLength() == data.length )
            {
                if ( comp.common(this) == data.length )
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    public String toString()
    {
        return new String(data);
    }
}
