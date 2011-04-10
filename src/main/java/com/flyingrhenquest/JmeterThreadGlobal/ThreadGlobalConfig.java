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
 * ThreadGlobalConfig implements a config element that binds a global
 * hashmap to a jmeter variable for later use.
 */

package com.flyingrhenquest.JmeterThreadGlobal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.jmeter.config.ConfigTestElement;
import org.apache.jmeter.testelement.TestListener;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jmeter.engine.util.NoThreadClone;
import org.apache.jmeter.testbeans.TestBean;
import org.apache.jmeter.engine.event.LoopIterationEvent;

public class ThreadGlobalConfig extends ConfigTestElement
  implements TestBean, TestListener, NoThreadClone {
  private static final long serialVersionUID = 240L;

  /**
   * Name to bind to in vars
   */

  private String mapName;
  private Map<String,String> map;

  public String getMapName() {
    return mapName;
  }

  public void setMapName(String mapName) {
    this.mapName = mapName;
  }
  
  /**
   * @inheritdoc
   */

  public void testStarted() {
    map = Collections.synchronizedMap(new HashMap<String,String>());
    JMeterVariables vars = JMeterContextService.getContext().getVariables();
    vars.putObject(mapName, map);
 }

  /**
   * @inheritdoc
   */

  public void testStarted(String host) {
    testStarted();
  }

  /**
   * @inheritdoc
   */

  public void testEnded() {
  }

  /**
   * @inheritdoc
   */

  public void testEnded(String host) {
  }

  /**
   * @inheritdoc
   */
 
  public void testIterationStart(LoopIterationEvent event) {
  }
}