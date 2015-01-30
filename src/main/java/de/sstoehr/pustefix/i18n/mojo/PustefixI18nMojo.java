package de.sstoehr.pustefix.i18n.mojo;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mojo(name = "convert", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, requiresProject = true)
public class PustefixI18nMojo extends AbstractMojo {

    private static final Logger LOG = LoggerFactory.getLogger(PustefixI18nMojo.class);

    @Parameter(required = true)
    private List<Locale> locales;

    @Parameter(required = true, defaultValue = "${project.build.directory}/${project.build.finalName}/txt/meta/i18n.xml")
    private File outputFile;

    @Component
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }

}
