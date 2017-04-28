/*
 * Copyright 2017 Lavender Flowerdew
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

package org.flowerdew.lavender.tibetan.wylie;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class WylieConversions {
  public static Map<String,String> DEFAULT_LIGATURES = mappings("ligatures");
  public static Map<String,String> DEFAULT_PUNCTUATION = mappings("punctuation");
  public static Map<String,String> DEFAULT_CHARACTERS = mappings("characters");

  private WylieConversions() {}

  public static String toUchen(final String extWylie) {
    final List<Map<String,String>> defaultMappings = new ArrayList<Map<String,String>>();
    Collections.addAll(defaultMappings, DEFAULT_LIGATURES, DEFAULT_PUNCTUATION, DEFAULT_CHARACTERS);

    final String uchen = process(defaultMappings, extWylie);

    return uchen;
  }

  private static String process(final List<Map<String,String>> mappings, final String wylie) {
    String result = wylie;
    for (Map<String,String> map : mappings) {
      result = process(map, result);
    }
    return result;
  }

  private static String process(final Map<String,String> mappings, final String wylie) {
    String result = wylie;
    // slow method will change to matcher and stringbuffer
    String[] keys = mappings.keySet().toArray(new String[0]);
    Arrays.sort(keys,getLengthComparator());
    for (String key : keys) {
      String regex = key.replaceAll("([-\\[\\]{}()*+?.,^$|#\\s])", "\\\\$1");
      String replacement = mappings.get(key);
      //System.out.println(regex + "    " + replacement);
      result = result.replaceAll(regex, replacement);
    }

    return result;
  }

  private static Map<String,String> mappings(final String name) {
    SortedMap map = Collections.synchronizedSortedMap(new TreeMap<String,String>());

    InputStream inputStream = null;
    try {
      inputStream = WylieConversions.class.getResourceAsStream("/uchen/" + name + ".properties");
      Properties properties = new Properties();
      properties.load(inputStream);

      Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
        map.put(key, value);
			}
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try { inputStream.close(); } catch (Exception e) { e.printStackTrace(); }
      }
      String[] keys = (String[])map.keySet().toArray(new String[0]);
      Arrays.sort(keys,getLengthComparator());
      //for (String key : keys) {
      //  System.out.println(key);
      //}
    }

    return map;
  }

  private static Comparator<String> getLengthComparator() {
    return new Comparator<String>() {
      public int compare(String str1, String str2) {
        int v = Integer.compare(str1.length(), str2.length());
        if (v < 0) {
          return Math.abs(v);
        } else if (v > 0) {
          return -v;
        } else {
          return 0;
        }
      }

      public boolean equals(Object obj) {
        return this.equals(obj);
      }
    };
  }
}
