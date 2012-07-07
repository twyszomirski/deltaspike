/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.test.core.api.message;

import org.apache.deltaspike.core.api.message.MessageResolver;
import org.apache.deltaspike.core.util.PropertyFileUtils;

import javax.enterprise.inject.Typed;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Typed()
public class TestMessageResolver implements MessageResolver
{
    private static final long serialVersionUID = -7977480064291950923L;

    protected TestMessageResolver()
    {
    }

    @Override
    public String getMessage(String messageBundleName, Locale locale, String messageTemplate)
    {
        if ( messageTemplate.startsWith("{") && messageTemplate.endsWith("}"))
        {
            messageTemplate = messageTemplate.substring(1, messageTemplate.length() - 1);
            try
            {
                ResourceBundle resolvedBundle = PropertyFileUtils
                        .getResourceBundle(TestMessages.class.getName(), locale);
                return resolvedBundle.getString(messageTemplate);
            }
            catch (MissingResourceException e)
            {
                return messageTemplate;
            }
        }

        return messageTemplate;
    }
}
