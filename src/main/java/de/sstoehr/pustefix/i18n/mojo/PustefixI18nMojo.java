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

import de.sstoehr.pustefix.i18n.convert.Converter;
import de.sstoehr.pustefix.i18n.input.PoReader;
import de.sstoehr.pustefix.i18n.input.Reader;
import de.sstoehr.pustefix.i18n.output.PustefixXmlWriter;
import de.sstoehr.pustefix.i18n.output.Writer;

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

        Reader reader = new PoReader();
        Writer writer = new PustefixXmlWriter();

        Converter converter = new Converter(reader, writer);

        for (Locale locale : locales) {
            de.sstoehr.pustefix.i18n.model.Locale l = new de.sstoehr.pustefix.i18n.model.Locale(locale.getLocale());

            converter.addLocale(l, locale.getPoFile(), outputFile);
        }

        converter.convert();
    }

}
