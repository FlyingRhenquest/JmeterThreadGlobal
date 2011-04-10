/**
 * Copyright 2011 Bruce Ide
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This sampler takes a named ThreadGlobal variable and extracts
 * all the stuff in it into a jmeter array, which you can then
 * grovel over however you want to.
 */

package com.flyingrhenquest.JmeterThreadGlobal;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;
import org.apache.jmeter.samplers.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ThreadGlobalArraySampler extends AbstractSampler
  implements Sampler, TestBean {
  private static final long serialVersionUID = 240L;

  /**
   * Jmeter variable name where the threadglobal is stored
   */
  private String mapName;

  public String getMapName() {
    return mapName;
  }

  public void setMapName(String mapName) {
    this.mapName = mapName;
  }

  public SampleResult sample(Entry e) {
    SampleResult retval = new SampleResult();
    JMeterVariables vars = JMeterContextService.getContext().getVariables();
    Map<String, String> map = (Map<String,String>) vars.getObject(mapName);
    if (null == map) {
      retval.setSuccessful(false);
      retval.setSampleLabel("ERROR: No threadglobal named " + mapName);
    } else {
      int i = 1; // jmeter arrays start at 1
      Set<Map.Entry<String,String>> entries = map.entrySet();
      for (Map.Entry<String,String> entry : entries) {
        vars.put(mapName + "_" + Integer.toString(i), entry.getValue());
        i++;
      }
      retval.setSuccessful(true);
      retval.setSampleLabel("Wrote " + Integer.toString(i - 1) + " entries to " + mapName + " array");
      vars.put(mapName + "_" + "#", Integer.toString(i-1));
    }
    return retval;
  }

}