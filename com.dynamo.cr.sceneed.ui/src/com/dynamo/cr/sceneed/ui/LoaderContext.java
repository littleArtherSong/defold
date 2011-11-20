package com.dynamo.cr.sceneed.ui;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import com.dynamo.cr.sceneed.core.ILogger;
import com.dynamo.cr.sceneed.core.INodeTypeRegistry;
import com.dynamo.cr.sceneed.core.ISceneView.INodeLoader;
import com.dynamo.cr.sceneed.core.Node;
import com.google.inject.Inject;

public class LoaderContext implements
com.dynamo.cr.sceneed.core.ISceneView.ILoaderContext {

    private final IContainer contentRoot;
    private final INodeTypeRegistry nodeTypeRegistry;
    private final ILogger logger;

    @Inject
    public LoaderContext(IContainer contentRoot, INodeTypeRegistry nodeTypeRegistry, ILogger logger) {
        this.contentRoot = contentRoot;
        this.nodeTypeRegistry = nodeTypeRegistry;
        this.logger = logger;
    }

    @Override
    public void logException(Throwable exception) {
        this.logger.logException(exception);
    }

    @Override
    public Node loadNode(String path) throws IOException, CoreException {
        IFile file = this.contentRoot.getFile(new Path(path));
        if (file.exists()) {
            return loadNode(file.getFileExtension(), file.getContents());
        }
        return null;
    }

    @Override
    public Node loadNode(String extension, InputStream contents)
            throws IOException, CoreException {
        INodeLoader loader = this.nodeTypeRegistry.getLoader(extension);
        if (loader != null) {
            return loader.load(this, extension, contents);
        }
        return null;
    }

    @Override
    public INodeTypeRegistry getNodeTypeRegistry() {
        return this.nodeTypeRegistry;
    }

}
