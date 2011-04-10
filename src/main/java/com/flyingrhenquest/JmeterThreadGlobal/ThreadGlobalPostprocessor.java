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
 * This is a thread global postprocessor. It takes a variable name
 * which must be the same as the one named in ThreadGlobalConfig
 * and writes the result data (As a String) to the JMeterThread.pack
 * thread ID for this thread in that map. That means that each thread
 * can safely use this postprocessor without overwriting data in it.
 */

package com.flyingrhenquest.JmeterThreadGlobal;

import java.util.Map;

import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;

public class ThreadGlobalPostprocessor extends AbstractTestElement
  implements PostProcessor, TestBean {
  public static final long serialVersionUID = 240L;
  private static final String THREAD_ID = "JMeterThread.pack";

  /**
   * Jmeter variable name the hashmap is bound to.
   */

  private String mapName;

  public String getMapName() {
    return mapName;
  }

  public void setMapName(String mapName) { 
    this.mapName = mapName;
  }

  public void process() {
    JMeterVariables vars = JMeterContextService.getContext().getVariables();
    String threadId = vars.getObject(THREAD_ID).toString();
    SampleResult previousResult = JMeterContextService.getContext().getPreviousResult();
    Map<String,String> map = (Map<String,String>) vars.getObject(mapName);
    map.put(threadId, previousResult.getResponseDataAsString());
  }

}