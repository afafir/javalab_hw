package ru.itis.html.generator;


@HtmlForm(method = "post", action = "/ne_users")
public class NeUser {
    @HtmlInput(type = "text", name = "ne_first_name", placeholder = "Имя")
    private String firstName;
    @HtmlInput(type = "text", name = "ne_last_name", placeholder = "Фамилия")
    private String lastName;
    @HtmlInput(type = "email", name = "ne_email", placeholder = "Email")
    private String email;
    @HtmlInput(type = "password", name = "ne_password", placeholder = "Пароль")
    private String password;
}
