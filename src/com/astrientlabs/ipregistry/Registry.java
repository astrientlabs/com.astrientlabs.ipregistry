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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Registry
{
	private boolean loading = false;

	public void populate(String... registry)
	{
		if ( loading ) return;

		try
		{
			loading = true;

			for (String url : registry)
			{
				try
				{ 
					load(url);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}			    
			}


			publish();
		}
		finally
		{
			loading = false;
		}
	}

	protected void load(String url) throws IOException
	{
		Logger logger = Logger.getGlobal();	
		InputStream is = null;
		try
		{
			URL u = new URL(url);
			
			logger.log(Level.INFO, "Opening " + url);
			//Implement disk caching here

			is = u.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String[] parts;
			String country;
			String ip;
			String s;
			while ((s = br.readLine()) != null)
			{		    
				if (s.startsWith("#"))
					continue;

				parts = s.split("\\|");
				if ( parts.length > 2 )
				{
					logger.log(Level.FINEST, s);
					
					country = parts[1];
					if ( country.length() == 2  )
					{
						ip = parts[3];
						if ( ip.indexOf('.') != -1 )
						{
							add(ip,country.toUpperCase().intern());
						}
					}
				}
			}
		}
		finally
		{
			try
			{
				is.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public abstract String search(String ipAddress);
	protected abstract void add(String ip, String country);
	protected abstract void publish();
}
