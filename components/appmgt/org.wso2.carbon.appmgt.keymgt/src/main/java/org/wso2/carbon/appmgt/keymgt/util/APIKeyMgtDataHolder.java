/*
*Copyright (c) 2005-2010, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*WSO2 Inc. licenses this file to you under the Apache License,
*Version 2.0 (the "License"); you may not use this file except
*in compliance with the License.
*You may obtain a copy of the License at
*
*http://www.apache.org/licenses/LICENSE-2.0
*
*Unless required by applicable law or agreed to in writing,
*software distributed under the License is distributed on an
*"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*KIND, either express or implied.  See the License for the
*specific language governing permissions and limitations
*under the License.
*/

package org.wso2.carbon.appmgt.keymgt.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.appmgt.impl.AppMConstants;
import org.wso2.carbon.appmgt.impl.AppManagerConfigurationService;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;

public class APIKeyMgtDataHolder {

    private static RegistryService registryService;
    private static RealmService realmService;
    private static AppManagerConfigurationService amConfigService;
    private static Boolean isJWTCacheEnabledKeyMgt = true;
    private static Boolean isKeyCacheEnabledKeyMgt = true;
    private static Boolean isThriftServerEnabled = true;
    private static final Log log = LogFactory.getLog(APIKeyMgtDataHolder.class);

    public static Boolean getJWTCacheEnabledKeyMgt() {
        return isJWTCacheEnabledKeyMgt;
    }

    public static void setJWTCacheEnabledKeyMgt(Boolean JWTCacheEnabledKeyMgt) {
        isJWTCacheEnabledKeyMgt = JWTCacheEnabledKeyMgt;
    }

    public static Boolean getKeyCacheEnabledKeyMgt() {
        return isKeyCacheEnabledKeyMgt;
    }

    public static void setKeyCacheEnabledKeyMgt(Boolean keyCacheEnabledKeyMgt) {
        isKeyCacheEnabledKeyMgt = keyCacheEnabledKeyMgt;
    }


    public static AppManagerConfigurationService getAmConfigService() {
        return amConfigService;
    }

    public static void setAmConfigService(AppManagerConfigurationService amConfigService) {
        APIKeyMgtDataHolder.amConfigService = amConfigService;
    }

    public static RegistryService getRegistryService() {
        return registryService;
    }

    public static void setRegistryService(RegistryService registryService) {
        APIKeyMgtDataHolder.registryService = registryService;
    }

    public static RealmService getRealmService() {
        return realmService;
    }

    public static void setRealmService(RealmService realmService) {
        APIKeyMgtDataHolder.realmService = realmService;
    }

    public static Boolean getThriftServerEnabled() {
        return isThriftServerEnabled;
    }

    public static void setThriftServerEnabled(Boolean thriftServerEnabled) {
        isThriftServerEnabled = thriftServerEnabled;
    }

    public static void initData() {
        try {
            APIKeyMgtDataHolder.isJWTCacheEnabledKeyMgt = getInitValues(AppMConstants.API_KEY_MANAGER_ENABLE_JWT_CACHE);
            APIKeyMgtDataHolder.isKeyCacheEnabledKeyMgt = getInitValues(AppMConstants.API_KEY_MANAGER_ENABLE_VALIDATION_INFO_CACHE);
            APIKeyMgtDataHolder.isThriftServerEnabled = getInitValues(AppMConstants.API_KEY_MANAGER_ENABLE_THRIFT_SERVER);
        } catch (Exception e) {
            log.error("Error occur while initializing WebApp KeyMgt Data Holder.Default configuration will be used." + e.toString());
        }
    }

    private static boolean getInitValues(String constVal) {
        String val = getAmConfigService().getAPIManagerConfiguration().getFirstProperty(constVal);
        if (val != null) {
            return Boolean.parseBoolean(val);
        }
        return false;
    }
}
