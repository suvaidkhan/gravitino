/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.gravitino.storage;

// Properties for OSS.
public class OSSProperties {

  // The region of Aliyun OSS.
  public static final String GRAVITINO_OSS_REGION = "oss-region";
  // The endpoint of Aliyun OSS.
  public static final String GRAVITINO_OSS_ENDPOINT = "oss-endpoint";
  // The static access key ID used to access OSS data.
  public static final String GRAVITINO_OSS_ACCESS_KEY_ID = "oss-access-key-id";
  // The static access key secret used to access OSS data.
  public static final String GRAVITINO_OSS_ACCESS_KEY_SECRET = "oss-secret-access-key";

  // OSS role arn
  public static final String GRAVITINO_OSS_ROLE_ARN = "oss-role-arn";
  // OSS external id
  public static final String GRAVITINO_OSS_EXTERNAL_ID = "oss-external-id";

  private OSSProperties() {}
}
