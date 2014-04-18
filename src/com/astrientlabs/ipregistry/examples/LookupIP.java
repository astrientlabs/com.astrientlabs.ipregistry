package com.astrientlabs.ipregistry.examples;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.astrientlabs.ipregistry.MemoryBackedRegistry;
import com.astrientlabs.ipregistry.Registry;

public class LookupIP 
{
	public static void main(String[] args)
	{
	    
		try
		{	 	    
			Logger.getGlobal().setLevel(Level.INFO);
			Registry registry = new MemoryBackedRegistry();
			registry.populate(
				"ftp://ftp.ripe.net/ripe/stats/delegated-ripencc-latest" //ripencc
				,"ftp://ftp.lacnic.net/pub/stats/lacnic/delegated-lacnic-latest" //lacnic
				,"ftp://ftp.apnic.net/pub/apnic/stats/apnic/delegated-apnic-latest" //apnic
				,"ftp://ftp.afrinic.net/pub/stats/afrinic/delegated-afrinic-latest"
				,"ftp://ftp.arin.net/pub/stats/arin/delegated-arin-extended-20140418" //,"ftp://ftp.arin.net/pub/stats/arin/delegated-arin-latest" //arin stopped providing file, see: https://drupal.org/node/2078071
			);
			
			System.out.println("217.208.23.45 = {0} (SE) = " +  registry.search("217.208.23.45"));
			System.out.println("195.96.96.111 = {0} (NL) = " +  registry.search("195.96.96.111"));
			System.out.println("81.16.128.23 = {0} (EU) = " +  registry.search("81.16.128.23"));
			System.out.println("62.4.160.87 = {0} (BE) = " +  registry.search("62.4.160.87"));
			System.out.println("217.48.32.5 = {0} (DE) = " +  registry.search("217.48.32.5"));
			System.out.println("81.50.140.32 = {0} (FR) = " +  registry.search("81.50.140.32"));
			System.out.println("81.70.12.45 = {0} (NL)) = " +  registry.search("81.70.12.45"));
			System.out.println("81.88.95.45 = {0} (RU)) = " +  registry.search("81.88.95.45"));
			System.out.println("216.218.234.67 = {0} (US)) = " +  registry.search("216.218.234.67"));
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.exit(0);
	}	
}
