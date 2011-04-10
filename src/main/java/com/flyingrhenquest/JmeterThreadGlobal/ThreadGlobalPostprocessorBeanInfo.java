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
 * UI stuff for ThreadGlobalPostprocessor
 */

package com.flyingrhenquest.JmeterThreadGlobal;

import java.beans.PropertyDescriptor;
import org.apache.jmeter.testbeans.BeanInfoSupport;

public class ThreadGlobalPostprocessorBeanInfo extends BeanInfoSupport {

  public ThreadGlobalPostprocessorBeanInfo() {
    super(ThreadGlobalPostprocessor.class);

    createPropertyGroup("Jmeter Variable ThreadGlobal is Bound To",
                        new String[] {
                          "mapName"
                        });

    PropertyDescriptor p;
    p = property("mapName");
    p.setValue(NOT_UNDEFINED, Boolean.TRUE);
    p.setValue(DEFAULT, "ThreadGlobal");
  }

}