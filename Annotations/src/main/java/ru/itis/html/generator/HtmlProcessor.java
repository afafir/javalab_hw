package ru.itis.html.generator;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

/**
 * 30.10.2020
 * 46. Annotations
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.html.generator.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотацией HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                Set<HtmlInputDto> fields = element
                        .getEnclosedElements()
                        .stream()
                        .filter(x -> ((Element) x).getKind().isField())
                        .filter(x -> ((Element) x).getAnnotation(HtmlInput.class) != null)
                        .map(x -> ((Element) x).getAnnotation(HtmlInput.class))
                        .map(x -> new HtmlInputDto(x.type(), x.name(), x.placeholder()))
                        .collect(Collectors.toSet());
                processFTLtoHTML(out, annotation.method(), annotation.action(), fields);

            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        return true;
    }



    private void processFTLtoHTML(Path out, String method, String action, Set<HtmlInputDto> fields) throws IOException, TemplateException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
        Configuration cfg = new Configuration(new Version("2.3.23"));
        cfg.setClassForTemplateLoading(HtmlProcessor.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        Template template = cfg.getTemplate("super.ftl");
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("action", action);
        templateData.put("method", method);
        templateData.put("htmlInputs", fields);
        template.process(templateData, writer);
        writer.close();
    }
}
