/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onehippo.forge.plugins.example;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.basic.Label;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.hippoecm.frontend.service.render.ListViewPlugin;

/**
 * @version "$Id$"
 */
public class FieldsPluginExample extends ListViewPlugin implements IHeaderContributor {

    private static final long serialVersionUID = 1L;


    public FieldsPluginExample(final IPluginContext context, final IPluginConfig config) {
        super(context, config);
        add(new Label("pluginTitle"));
    }


}
