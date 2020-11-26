package ru.javalab.hateoas.demo.url;

public interface Urls {
    String API = "api";
    String SERVICE_NAME = "cohelp";
    String ROOT = "/" + API + "/"  + SERVICE_NAME;

    interface TaskUrls{
        String PART = "/task";
        String FULL = ROOT + PART;

        interface addProduct{
            String PART = "/add";
            String FULL = TaskUrls.FULL + PART;
        }

        interface acceptTask{
            String PART = "/acceptTask";
            String FULL = TaskUrls.FULL + PART;
        }

        interface confirmTask{
            String PART = "/confirmTask";
            String FULL = TaskUrls.FULL + PART;
        }

        interface createTask{
            String PART = "/createTask";
            String FULL = TaskUrls.FULL + PART;
        }
    }
}
