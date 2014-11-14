/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.gearpump.services

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.apache.gearpump.cluster.TestUtil
import org.apache.gearpump.cluster.TestUtil.MiniCluster
import org.apache.gearpump.streaming.StreamingTestUtil


object RestTestUtil {

  val startRestServices:RestTest = new RestTest().startRestServices

  class RestTest {
    val miniCluster:MiniCluster = TestUtil.startMiniCluster
    val appId = 0
    val master = miniCluster.mockMaster
    StreamingTestUtil.startAppMaster(miniCluster, appId)

    def startRestServices = {
      implicit val system = ActorSystem("Rest", TestUtil.TEST_CONFIG)
      RestServices.start(master)
      this
    }

    def shutdown = {
      miniCluster.shutDown
    }
  }

}
