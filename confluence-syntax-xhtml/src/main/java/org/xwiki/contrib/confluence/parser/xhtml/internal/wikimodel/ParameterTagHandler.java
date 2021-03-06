/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.confluence.parser.xhtml.internal.wikimodel;

import org.xwiki.contrib.confluence.parser.xhtml.internal.wikimodel.MacroTagHandler.ConfluenceMacro;
import org.xwiki.rendering.wikimodel.WikiParameter;
import org.xwiki.rendering.wikimodel.xhtml.handler.TagHandler;
import org.xwiki.rendering.wikimodel.xhtml.impl.TagContext;

/**
 * Handles parameters.
 * <p>
 * Example:
 * <p>
 * {@code
 * <ac:parameter ac:name="title">State default macro</ac:parameter>
 * }
 *
 * @version $Id$
 * @since 9.0
 */
public class ParameterTagHandler extends TagHandler implements ConfluenceTagHandler
{
    public ParameterTagHandler()
    {
        super(true);
    }

    @Override
    protected void begin(TagContext context)
    {
        setAccumulateContent(true);
    }

    @Override
    protected void end(TagContext context)
    {
        ConfluenceMacro macro = (ConfluenceMacro) context.getTagStack().getStackParameter(CONFLUENCE_CONTAINER);

        if (macro != null) {
            WikiParameter nameParameter = context.getParams().getParameter("ac:name");

            if (nameParameter != null) {
                String value = context.getContent();

                macro.parameters = macro.parameters.setParameter(nameParameter.getValue(), value);
            }
        }
    }
}
