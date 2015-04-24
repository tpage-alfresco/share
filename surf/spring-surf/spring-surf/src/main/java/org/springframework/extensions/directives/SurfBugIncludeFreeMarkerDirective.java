/**
 * Copyright (C) 2005-2009 Alfresco Software Limited.
 *
 * This file is part of the Spring Surf Extension project.
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
package org.springframework.extensions.directives;

import java.io.IOException;
import java.util.Map;

import org.springframework.extensions.surf.ModelObject;
import org.springframework.extensions.surf.RequestContext;
import org.springframework.extensions.surf.exception.RendererExecutionException;
import org.springframework.extensions.surf.render.RenderService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * <p>A FreeMarker directive that uses a <code>PresentationService</code> to render the component being debugged
 * 
 * @author David Draper
 */
public class SurfBugIncludeFreeMarkerDirective extends RenderServiceFreeMarkerDirective
{
    /**
     * <p>Instantiates a new <code>SurfBugIncludeFreeMarkerDirective</code>. The <code>RenderService</code> will be 
     * used by calling its <code>renderChromeInclude</code> method to generate the output. The directive name is only needed
     * for generating useful exception messages to assist debugging problems but an effort should be made to set it 
     * correctly</p>
     * 
     * @param directiveName The name of the directive represented by the instance of this class.
     * @param context A <code>RequestContext</code> required as an argument to the <code>RenderService</code> method.
     * @param renderService A <code>RenderService</code> used to generate the output of the directive by calling 
     * its <code>renderRegion</code> method.
     */
    public SurfBugIncludeFreeMarkerDirective(String directiveName, RequestContext context, ModelObject object, RenderService renderService)
    {
        super(directiveName, context, object, renderService);  
    }
    
    /**
     * <p>This method is declared by the <code>TemplateDirectiveModel</code> interface that the abstract
     * superclass <code>AbstractFreeMarkerDirective</code> implements. It retrieves any properties supplied
     * when invoking the directive and passes them onto the <code>PresentationService.renderChromeInclude</code>
     * method.
     */
    @SuppressWarnings({ "rawtypes" })
    public void execute(Environment env, 
                        Map params, 
                        TemplateModel[] loopVars, 
                        TemplateDirectiveBody body) throws TemplateException, IOException
    {        
        try
        {
            getRenderService().renderSurfBugInclude(getRequestContext());
        }
        catch (RendererExecutionException e)
        {
            throw new TemplateException(e, env);
        }        
    }
}
