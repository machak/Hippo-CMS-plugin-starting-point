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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.IAutoCompleteRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.string.Strings;
import org.hippoecm.frontend.editor.plugins.field.PropertyFieldPlugin;
import org.hippoecm.frontend.plugin.IPluginContext;
import org.hippoecm.frontend.plugin.config.IPluginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class AutoCompleteField extends PropertyFieldPlugin {
    private static final Logger log = LoggerFactory.getLogger(AutoCompleteField.class);
    private static final long serialVersionUID = 1L;

    private AutoCompleteBehavior<String> behavior;
    private final IAutoCompleteRenderer<String> renderer;
    private final AutoCompleteSettings settings;


    public AutoCompleteField(IPluginContext context, IPluginConfig config) {
        super(context, config);
        renderer = new StringAutocompleteRenderer();
        settings = new AutoCompleteSettings();
        behavior = newAutoCompleteBehavior(renderer, settings);
        super.add(behavior);
    }

    private AutoCompleteBehavior<String> newAutoCompleteBehavior(final IAutoCompleteRenderer<String> renderer, final AutoCompleteSettings settings) {
        return new AutoCompleteBehavior<String>(renderer, settings) {
            private static final long serialVersionUID = 1L;

            @Override
            protected Iterator<String> getChoices(final String input) {
                return AutoCompleteField.this.getChoices(input);
            }
        };
    }

    private Iterator<String> getChoices(final String input) {

        final Collection<String> items = new HashSet<>();
        final IModel<Node> model = getModel();
        final Node object = model.getObject();
        try {
            final PropertyIterator properties = object.getProperties();
            while (properties.hasNext()) {
                final Property property = properties.nextProperty();
                final String name = property.getName();
                if (!Strings.isEmpty(input) && name.startsWith(input)) {
                    items.add(name);
                }
                /*if (!property.isMultiple()) {
                    if (property.getType() == PropertyType.STRING) {


                    }
                }*/
            }
        } catch (RepositoryException e) {
            log.error("Error parsing properties", e);
        }
        return items.iterator();
    }


}
