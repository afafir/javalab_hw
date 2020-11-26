package ru.javalab.hateoas.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;
import ru.javalab.hateoas.demo.controller.TaskController;
import ru.javalab.hateoas.demo.model.Status;
import ru.javalab.hateoas.demo.model.Task;

import javax.persistence.Column;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@RequiredArgsConstructor
public class TaskRepresentationModel implements RepresentationModelProcessor<EntityModel<Task>> {

    private final RepositoryEntityLinks links;

    @Override
    public EntityModel<Task> process(EntityModel<Task> model) {
        Task task = model.getContent();
        if (task != null && task.getStatus().equals(Status.NEW)) {
            model.add(linkTo(methodOn(TaskController.class)
                    .accept(null, task.getId())).withRel("accept"));
        }
        if (task!= null && task.getStatus().equals(Status.ON_EXECUTION)) {
            model.add(linkTo(methodOn(TaskController.class)
                    .confirm(task.getId(), null)).withRel("confirm"));
            model.add(linkTo(methodOn(TaskController.class)
                    .addProduct(null, task.getId())).withRel("addProduct"));
        }
        return model;
    }



}
