package ru.javalab.hateoas.demo.config;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.javalab.hateoas.demo.controller.TaskController;
import ru.javalab.hateoas.demo.model.user.Consumer;

@Component
public class RootRepresentationModel implements RepresentationModelProcessor<RepositoryLinksResource> {



    //мне не нравится дефолтный пост, поэтому добавил кастомный пост на создание сущности
    @Override
    public RepositoryLinksResource process(RepositoryLinksResource model) {
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TaskController.class).create(null, null)).withRel("createTask"));
        return model;
    }
}
