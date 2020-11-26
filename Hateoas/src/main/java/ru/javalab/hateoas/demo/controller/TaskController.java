package ru.javalab.hateoas.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.javalab.hateoas.demo.dto.TaskCreateDto;
import ru.javalab.hateoas.demo.model.Product;
import ru.javalab.hateoas.demo.model.Task;
import ru.javalab.hateoas.demo.model.user.Volunteer;
import ru.javalab.hateoas.demo.service.TaskService;


@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @RequestMapping(value = "/task/{task-id}/addProduct/{product-id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> addProduct(@PathVariable("product-id") Long productId, @PathVariable("task-id") Long taskId) {
        return ResponseEntity.ok(
                EntityModel.of(taskService.addProductToTask(taskId, productId)));
    }

    @RequestMapping(value = "/task/{task-id}/accept/{volunteer-id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> accept(@PathVariable("volunteer-id") Long volunteerId, @PathVariable("task-id") Long taskId) {
        return ResponseEntity.ok(
                EntityModel.of(taskService.acceptTask(volunteerId, taskId)));
    }

    @RequestMapping(value = "/task/{task-id}/confirm/{volunteer-id}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> confirm(@PathVariable("task-id") Long taskId, @PathVariable("volunteer-id")  Long volunteerId) {
        return ResponseEntity.ok(
                EntityModel.of(taskService.confirmTask(volunteerId, taskId)));
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST )
    public @ResponseBody
    ResponseEntity<?> create(@RequestBody TaskCreateDto taskCreateDto,  PersistentEntityResourceAssembler resourceAssembler) {
        return ResponseEntity.ok(
                resourceAssembler.toFullResource(taskService.createTask(taskCreateDto.getConsumerId())));
    }



}
